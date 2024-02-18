package no.hb.cart.shopping.db.repo;

import no.hb.cart.shopping.db.entity.WatchCatalogDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WatchCatalogDiscountRepository extends JpaRepository<WatchCatalogDiscount, Integer> {
    @Query(value = "SELECT * FROM WATCH_CATALOG_DISCOUNT WHERE ITEM_ID = :itemId AND IS_ACTIVE = true", nativeQuery = true)
    public WatchCatalogDiscount findDiscountByItemId(@Param("itemId") Integer itemId);
}
