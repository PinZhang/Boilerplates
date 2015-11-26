local luaunit = require('luaunit')

TestUtil = {}
    function TestUtil:testUtilAdd ()
        local util = require('util')
        luaunit.assertEquals(util.add(1, 2), 3)
    end

    function TestUtil:testUtilAdd2 ()
        local util = require('util')
        luaunit.assertEquals(util.add(3, 2), 5)
    end
