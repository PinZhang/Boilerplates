<?php
/**
 * @copyright Copyright (C) 2015 Acadine Technologies
 */

// Settings to make all errors more obvious during testing
error_reporting(E_ALL);
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
date_default_timezone_set('UTC');

use There4\Slim\Test\WebTestCase;

define('PROJECT_ROOT', realpath(__DIR__.'/..'));
define('VENDOR_ROOT', realpath(__DIR__.'/..'));

require_once VENDOR_ROOT.'/vendor/autoload.php';

// Use a separate env file instead of `.env`, to make sure testing won't be run by mistake
$_ENV['DOTENV_FILE'] = '.env.testing';

require_once PROJECT_ROOT.'/app/conf/config.inc.php';

$config = get_config();

// Make sure the testing only be run on testing server
if ($config['mode'] != 'testing') {
    trigger_error('Only testing mode is allowed when running phpunit!', E_USER_ERROR);
}

// Initialize our own copy of the slim application
class LocalWebTestCase extends WebTestCase
{
    public function getSlimInstance()
    {
        $app = new \Slim\Slim(array(
          'version' => '0.1',
          'debug' => false,
          'mode' => 'testing',
        ));

        // Include our core application file
        require PROJECT_ROOT.'/app/Route.php';

        return $app;
    }
};
