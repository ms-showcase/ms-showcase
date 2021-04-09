import json

import pika

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
        self._channel.queue_declare(queue=self._queue)

    def publish(self, data):
        jsondata = json.dumps(data)
        self._channel.basic_publish(exchange='',
                              routing_key='covid19',
                              body=jsondata)

    def cleanup(self):
        self._connection.close()