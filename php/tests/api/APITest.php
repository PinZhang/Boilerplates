<?php
/**
 * @copyright Copyright (C) 2015 Acadine Technologies
 */

class APITest extends LocalWebTestCase
{
    public function testSubmit()
    {
        $this->client->post('submit');
        $this->assertEquals('bingo', $this->client->response->getBody());
    }
}
