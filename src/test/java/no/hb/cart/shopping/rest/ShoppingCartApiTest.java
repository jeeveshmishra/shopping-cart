package no.hb.cart.shopping.rest;

import no.hb.cart.shopping.contract.CheckoutResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShoppingCartApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    public void testController() throws Exception {

        ResponseEntity<CheckoutResponse> response = restTemplate.postForEntity(
                new URL("http://localhost:" + port + "/checkout").toString(), new ArrayList<String>(),  CheckoutResponse.class);
        assertEquals(new CheckoutResponse(0).getPrice(), response.getBody().getPrice());

    }
}