package com.example.demo

import org.flywaydb.core.Flyway
import org.skyscreamer.jsonassert.JSONAssert
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Shared
import spock.lang.Specification

import javax.sql.DataSource

@SpringBootTest(classes = TestApplication.class)
@AutoConfigureMockMvc
public class DemoApplicationSpec extends Specification {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataSource dataSource;

    @SpringBean
    private RemoteFetcher mockedRemoteFetcher = Mock();

    def setup() {
        assert mockMvc != null
        assert dataSource != null
    }

    @Shared
    boolean userSetupDone = false;

    def "Test /user/{id}"() {
        setup:
        // Only setup MySQL fixture once
        if (!userSetupDone) {
            userSetupDone = true;
            Flyway flyway = Flyway.configure().dataSource(dataSource).load()
            flyway.clean()
            flyway.migrate()
        }

        when:
        def resultActions = getOrPost(url, method, param)
        def response = resultActions.andReturn().getResponse()

        then:
        response.getStatus() == status
        if (jsonContent instanceof String) {
            JSONAssert.assertEquals(jsonContent, response.getContentAsString(), false)
        }
        mockedRemoteFetcher.fetch(_) >> "MockedName"

        where:
        url         | method | param             || status || jsonContent
        "/user/1"   | "GET"  | null              || 200    || '{"id":1,"name":"Pin"}'
        "/user/2"   | "GET"  | null              || 404    || _
        "/user/add" | "POST" | '{"name": "Foo"}' || 200    || _
        "/user/2"   | "GET"  | null              || 200    || '{"name":"MockedName", "id":2}'
    }

    ResultActions getOrPost(String url, String method, String param) {
        MockHttpServletRequestBuilder builder = null;

        if (method == "GET") {
            builder = MockMvcRequestBuilders.get(url)
        } else {
            builder = MockMvcRequestBuilders.post(url)
                    .content(param)
                    .contentType(MediaType.APPLICATION_JSON)
        }

        mockMvc.perform(builder).andDo(MockMvcResultHandlers.print())
    }
}