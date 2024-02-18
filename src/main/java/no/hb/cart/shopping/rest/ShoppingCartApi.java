package no.hb.cart.shopping.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import no.hb.cart.shopping.db.repo.WatchCatalogRepository;
import no.hb.cart.shopping.exception.BadRequestEception;
import no.hb.cart.shopping.service.ShoppingCartCheckoutService;
import no.hb.cart.shopping.utils.ValidationsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static no.hb.cart.shopping.utils.ContractLogger.logRequest;
import static no.hb.cart.shopping.utils.ContractLogger.logResponse;

@RestController
@Slf4j
public class ShoppingCartApi {
    @Autowired
    private ShoppingCartCheckoutService checkoutService;

    @Autowired
    WatchCatalogRepository watchCatalogRepository;

    @GetMapping(value = "/shop/checkout/cart", produces = "application/json")
    @Operation(
            summary = "This method takes list of watch ids like : 001, 002, 003 etc.. " +
                    "and applies discounts if any as per business rule then calculates and returns total cost.",
            description = "As an input parameter this method takes list of watch ids like : 001, 002, 003 etc..",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Integer.class))),
                     @ApiResponse(
                            description = "application failure due to invalid inputs",
                            responseCode = "500",
                            content = @Content)

            })
    public ResponseEntity<Integer> fetchTotalCostForCart(@RequestParam(value = "items[]") String[] items) {

        logRequest(HttpMethod.GET, "fetchTotalCostForCart", items);

        Integer totalCost = checkoutService.calculateTotalPriceForCheckout(items);

        logResponse(HttpMethod.GET, HttpStatus.OK, "fetchTotalCostForCart", String.format("Total cost of shopping cart is: %d", totalCost));

        return ResponseEntity.ok(totalCost);
    }


}
