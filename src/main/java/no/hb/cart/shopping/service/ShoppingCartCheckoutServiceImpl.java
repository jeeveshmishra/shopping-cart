package no.hb.cart.shopping.service;

import lombok.extern.slf4j.Slf4j;
import no.hb.cart.shopping.db.entity.WatchCatalog;
import no.hb.cart.shopping.db.repo.WatchCatalogDiscountRepository;
import no.hb.cart.shopping.db.repo.WatchCatalogRepository;
import no.hb.cart.shopping.exception.BadRequestEception;
import no.hb.cart.shopping.model.WatchItemWithCount;
import no.hb.cart.shopping.utils.ValidationsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ShoppingCartCheckoutServiceImpl implements ShoppingCartCheckoutService {

    @Autowired
    WatchCatalogRepository watchCatalogRepository;

    @Autowired
    WatchCatalogDiscountRepository watchCatalogDiscountRepository;



    @Override
    public Integer calculateTotalPriceForCheckout(List<String> items) {
        validate(items);
        Set<WatchItemWithCount> watchItemsWithCount = new HashSet<>();
        items.forEach(item -> {
            WatchCatalog watchCatalogItem = watchCatalogRepository.findItemByWatchId(item);
            watchItemsWithCount.add(
                    WatchItemWithCount
                            .fromWatchCatalog(
                                    watchCatalogItem,
                                    Collections.frequency(items, item)
                            )
            );
        });

        return calculateTotalPrice(watchItemsWithCount);

    }

    private Integer calculateTotalPrice(Set<WatchItemWithCount> watchItemsWithCount) {
        Integer totalPrice = 0;
        for(WatchItemWithCount watchItemWithCount : watchItemsWithCount)
            totalPrice += watchItemWithCount.calculatePrice();

        return totalPrice;
    }

    private void validate(List<String> items) {


        List<String> existingWatchIds = watchCatalogRepository.findAllWatchIds();

        boolean isValid = ValidationsUtil.validate(items, existingWatchIds);

        if (!isValid) {
            log.error("Bad or Invalid elements within the input");
            throw new BadRequestEception("Supplied list of items contains one or more invalid elements.");
        }

    }
}
