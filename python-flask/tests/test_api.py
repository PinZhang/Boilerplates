# Copyright (c) 2016 Acadine Technologies
# All rights reserved.

import unittest
from app import app

class APITestCase(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_index(self):
        with app.test_client() as client:
            resp = client.get('/')
            self.assertEqual('Hello, world!', resp.data, msg='unexpected index response')

