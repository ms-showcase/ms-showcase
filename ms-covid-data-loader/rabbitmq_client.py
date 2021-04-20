import json
import pika
from py_zipkin.zipkin import zipkin_client_span
from py_zipkin.request_helpers import create_http_headers

class RabbitmqClient(object):

    def __init__(self, hostname, port, username, pwd, queue):
        self._hostname = hostname
        self._port = port
        self._username = username
        self._pwd = pwd
        self._queue = queue
        self.setup()

    def setup(self):
        if self._hostname:
            credentials = pika.PlainCredentials(self._username, self._pwd)
            self._connection = pika.BlockingConnection(pika.ConnectionParameters(self._hostname, self._port, '/', credentials))
        else:
            self._connection = pika.BlockingConnection(pika.ConnectionParameters())
        self._channel = self._connection.channel()

    @zipkin_client_span(service_name='ms-covid-data-loader', span_name='publish_amqp')
    def publish(self, data):
        headers = create_http_headers()
        jsondata = json.dumps(data)
        self._channel.basic_publish(
            exchange='',
            routing_key='covid19',
            body=jsondata,
            properties=pika.BasicProperties(
                headers=headers
            ),
        )

    def cleanup(self):
        self._connection.close()