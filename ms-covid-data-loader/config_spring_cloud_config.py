from config.spring import ConfigClient

class SpringCloudConfigClient(object):

    def __init__(self, appName):
        self._configDisabled = False
        # defaults (env vars): https://config-client.amenezes.net/docs/1.-overview/
        # CONFIGSERVER_ADDRESS=http://localhost:8888
        # BRANCH=master
        # PROFILE=development
        # APP_NAME=
        # CONFIG_FAIL_FAST=True
        try:
            self._config = ConfigClient(
                app_name=appName,
                fail_fast=False
            )
            self._config.get_config()
        except ConnectionError as ex:
            self._configDisabled = True
            print("config server unavalable")


    def property(self, property):
        if self._configDisabled == True:
            return None
        return self._config[property]