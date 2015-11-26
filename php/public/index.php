<?php
/**
 * @copyright Copyright (C) 2015 Acadine Technologies
 */

define('PROJECT_ROOT', realpath(__DIR__.'/..'));
define('VENDOR_ROOT', realpath(__DIR__.'/..'));

require_once VENDOR_ROOT.'/vendor/autoload.php';
require_once PROJECT_ROOT.'/app/conf/config.inc.php';

$config = get_config();
$app = new \Slim\Slim(
    array(
      'version' => '0.1',
      'debug' => $config['debug'],
      'mode' => $config['mode'],
    )
);

require_once PROJECT_ROOT.'/app/Route.php';

$app->run();
