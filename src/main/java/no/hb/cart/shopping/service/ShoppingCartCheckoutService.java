package no.hb.cart.shopping.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ShoppingCartCheckoutService {
    public Integer calculateTotalPriceForCheckout(String[] items);
    public Integer calculateTotalPriceForCheckout(List<String> items);
}
