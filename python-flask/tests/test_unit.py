# Copyright (c) 2016 Acadine Technologies
# All rights reserved.

import unittest

class UnitTestCase(unittest.TestCase):
    def setUp(self):
        pass

    def tearDown(self):
        pass

    def test_add(self):
        self.assertEqual(2, 1 + 1)

