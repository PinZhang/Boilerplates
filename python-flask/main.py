# Copyright (c) 2016 Acadine Technologies
# All rights reserved.

from app import app
from settings import settings

if __name__ == '__main__':
    app.run(debug=settings.debug, host=settings.host, port=settings.port)

