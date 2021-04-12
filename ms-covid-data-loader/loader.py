import os
from rabbitmq_client import RabbitmqClient
from csv import DictReader
from rx import from_
import requests
import tempfile

def process_row(rabbitmq, row):
    print("{0}\t{1}".format(row['iso_code'], row['date']))
    rabbitmq.publish(row)

def download_csv(url, filename):
    results = requests.get(url)
    with open(filename, 'wb') as f:
        f.write(results.content)

if __name__ == "__main__":
    # sccc = SpringCloudConfigClient('ms-covid-data-loader')
    rabbitmqHostname = os.getenv('RABBITMQ_HOSTNAME')  or 'localhost'
    rabbitmqPort = os.getenv('RABBITMQ_PORT')  or '5672'
    rabbitmqLogin = os.getenv('RABBITMQ_LOGIN')  or 'guest'
    rabbitmqPassword = os.getenv('RABBITMQ_PWD')  or 'guest'
    csvUrl = os.getenv('CSV_URL')  or 'https://raw.githubusercontent.com/owid/covid-19-data/master/public/data/owid-covid-data.csv'

    tempFile = tempfile.NamedTemporaryFile()
    try:
        download_csv(csvUrl, tempFile.name)
        rabbitmq = RabbitmqClient(rabbitmqHostname, rabbitmqPort, rabbitmqLogin, rabbitmqPassword, 'covid19')
        from_(DictReader(open(tempFile.name, 'r'))).subscribe( lambda row: process_row(rabbitmq, row))
    finally:
        tempFile.close()

    rabbitmq.cleanup()



