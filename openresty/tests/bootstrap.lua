local luaunit = require('luaunit')

-- phpunit use global `print` to print testing message, we
-- need to redirect the message to `ngx.say`
local _print = print
local _outputs = {}
print = function (s)
    table.insert(_outputs, s or '')
end

-- include all test cases
local tests_root = debug.getinfo(1, "S").source:sub(2):match("(.*/)")
local p = io.popen('find "'..tests_root..'" -type f -name test_*.lua')
for file in p:lines() do
    ngx.say('require test file: '..file)
    dofile(file)
end

-- run tests
exit_code = luaunit.LuaUnit.run()

-- change status code to 500 if there were tests failed
ngx.status = exit_code == 0 and 200 or 500
ngx.say(table.concat(_outputs, '\n'))

