local luaunit = require('luaunit')

function testEndpointInfo ()
    info = ngx.location.capture('/info')
    luaunit.assertEquals(info.body, 'Bingo! OpenResty Boilerplate')
end
