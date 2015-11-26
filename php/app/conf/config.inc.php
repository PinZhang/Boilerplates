<?php
/**
 * @copyright Copyright (C) 2015 Acadine Technologies
 */

$dotenv_file = '.env';
if (!empty($_ENV['DOTENV_FILE'])) {
    $dotenv_file = $_ENV['DOTENV_FILE'];
}

$docenv = new \Dotenv\Dotenv(PROJECT_ROOT, $dotenv_file);
$docenv->load();

function get_config()
{
    static $config;

    if (is_null($config)) {
        $config = array(
            'debug'     => $_ENV['DEBUG'],
            'mode'      => $_ENV['MODE'],
        );
    }

    return $config;
}
