package no.hb.cart.shopping.service;

import no.hb.cart.shopping.exception.BadRequestEception;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShoppingCartCheckoutServiceImplTest {

    @Autowired
    private ShoppingCartCheckoutService checkoutService;

    @Test
    @Sql({"/static/sql/insert_data.sql"})
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCalculateForNormalPrice() {
        List<String> requestItems = Arrays.asList("001", "002", "003", "004");
        Integer totalPrice = checkoutService.calculateTotalPriceForCheckout(requestItems);

        assertEquals(totalPrice, 260);
    }

    @Test
    @Sql({"/static/sql/insert_data.sql"})
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCalculateWithDiscountedPrice() {
        List<String> requestItems = Arrays.asList("001", "002", "003", "004", "001", "002", "003","001", "002");
        Integer totalPrice = checkoutService.calculateTotalPriceForCheckout(requestItems);

        assertEquals(totalPrice, 530);
    }

    @Test
    @Sql({"/static/sql/insert_data.sql"})
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void testCalculateWithDiscountForImnvalidInput() {
        List<String> requestItems = Arrays.asList("001", "002", "abcdefgh", "004", "anyInput", "002", "003","001", "002");
        Exception exception = assertThrows(BadRequestEception.class, () -> {
            checkoutService.calculateTotalPriceForCheckout(requestItems);
        });

        String expectedMessage = "Supplied list of items contains one or more invalid elements.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));


    }

}