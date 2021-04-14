from config.spring import ConfigClient

class SpringCloudConfigClient(object):

    def __init__(self):
        self._configDisabled = False
        # defaults (env vars): https://config-client.amenezes.net/docs/1.-overview/
        # CONFIGSERVER_ADDRESS=http://localhost:8888
        # BRANCH=master
        # PROFILE=development
        # APP_NAME=
        # CONFIG_FAIL_FAST=True
        try:
            self._config = ConfigClient()
            self._config.get_config()
        except ConnectionError as ex:
            self._configDisabled = True
            print("config server unavailable")


    def property(self, property):
        if self._configDisabled:
            return None
        return self._config.get_attribute(property)