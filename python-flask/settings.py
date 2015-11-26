# Copyright (c) 2016 Acadine Technologies
# All rights reserved.

import os
from dotenv import load_dotenv

dotenv_path = os.path.join(os.path.dirname(__file__), '.env')
load_dotenv(dotenv_path)

class Settings:
    def __init__(self, configs):
        self.__settings__ = configs

    def __getattr__(self, attr):
        return self.__settings__[attr]

settings = Settings({
    'debug': True if os.environ.get('DEBUG') == 'True' else False,
    'host': os.environ.get('HOST'),
    'port': int(os.environ.get('PORT')),
})

