package no.hb.cart.shopping.db.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "WATCH_CATALOG_DISCOUNT")
public class WatchCatalogDiscount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer discountId;

    @OneToOne
    @JoinColumn(name="ITEM_ID")
    @JsonBackReference
    private WatchCatalog watchCatalog;

    @Column(name = "DISCOUNT_DESCRIPTION")
    private String discountDescription;

    @Column(name = "DISCOUNT_ITEM_COUNT")
    private Integer discountItemCount;

    @Column(name = "DISCOUNT_PRICE")
    private Integer discountPrice;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
}
