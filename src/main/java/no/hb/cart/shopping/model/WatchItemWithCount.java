package no.hb.cart.shopping.model;

import lombok.*;
import no.hb.cart.shopping.db.entity.WatchCatalog;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WatchItemWithCount {
    private Integer itemId;
    private String watchId;
    private String watchName;
    private String watchDescription;
    private Integer unitPrice;
    private Integer discountId;
    private String discountDescription;
    private Integer discountItemCount;
    private Integer discountPrice;
    private Integer occurencesInRequest;

    public static WatchItemWithCount fromWatchCatalog(WatchCatalog watchCatalog, Integer occurrences) {
        return WatchItemWithCount.builder()
                .itemId(watchCatalog.getItemId())
                .watchId(watchCatalog.getWatchId())
                .watchName(watchCatalog.getWatchName())
                .watchDescription(watchCatalog.getWatchDescription())
                .unitPrice(watchCatalog.getUnitPrice())
                .discountId(watchCatalog.getWatchCatalogDiscount() != null ? watchCatalog.getWatchCatalogDiscount().getDiscountId() : null)
                .discountDescription(watchCatalog.getWatchCatalogDiscount() != null ? watchCatalog.getWatchCatalogDiscount().getDiscountDescription() : null)
                .discountItemCount(watchCatalog.getWatchCatalogDiscount() != null ? watchCatalog.getWatchCatalogDiscount().getDiscountItemCount(): null)
                .discountPrice(watchCatalog.getWatchCatalogDiscount() != null ? watchCatalog.getWatchCatalogDiscount().getDiscountPrice(): null)
                .occurencesInRequest(occurrences)
                .build();
    }

    public Integer calculatePrice() {
        
        if (discountItemCount != null && occurencesInRequest >= discountItemCount) {
            return calcualteDiscountedPrice(occurencesInRequest, unitPrice, discountItemCount, discountPrice);
        }
        return occurencesInRequest * unitPrice;
    }

    private Integer calcualteDiscountedPrice(Integer totalItemsCount, Integer itemOriginalPrice,Integer itemCountForDiscount, Integer discountedPrice) {
        if (totalItemsCount%(itemCountForDiscount) == 0) {
            return discountPrice * (totalItemsCount / itemCountForDiscount);
        } else {
            int partialFullPrice = (totalItemsCount%(itemCountForDiscount)) * itemOriginalPrice;

            int partialDiscountedPrice = (totalItemsCount/(itemCountForDiscount)) * discountedPrice;

            return partialFullPrice + partialDiscountedPrice;
        }
    }
}
