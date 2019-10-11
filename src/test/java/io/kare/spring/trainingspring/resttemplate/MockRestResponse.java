package io.kare.spring.trainingspring.resttemplate;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


/**
 * @author karellen
 */
public class MockRestResponse {

    @Test
    public void test() {
        // ReflectionTestUtils.setField(client, "host", "http://localhost");
        RestTemplate restTemplate = new RestTemplate();
        MockRestServiceServer mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();

        mockServer.expect(MockRestRequestMatchers.anything())
                .andRespond(MockRestResponseCreators
                        .withSuccess(new ClassPathResource("mock.json"), MediaType.APPLICATION_JSON));
    }
}
