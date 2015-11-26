<?php
/**
 * @copyright Copyright (C) 2015 Acadine Technologies
 */

$app->post('submit', function () use ($app) {
    $app->response->setBody('bingo');
});
