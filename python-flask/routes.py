# Copyright (c) 2016 Acadine Technologies
# All rights reserved.

from app import app

@app.route('/')
def hello_world():
    return 'Hello, world!'

