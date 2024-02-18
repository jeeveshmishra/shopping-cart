package no.hb.cart.shopping.db.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "WATCH_CATALOG")
public class WatchCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @Column(name = "WATCH_ID")
    private String watchId;

    @Column(name = "WATCH_NAME")
    private String watchName;

    @Column(name = "WATCH_DESCRIPTION")
    private String watchDescription;

    @Column(name = "UNIT_PRICE")
    private Integer unitPrice;

    @OneToOne(mappedBy = "watchCatalog")
    @JsonManagedReference
    private WatchCatalogDiscount watchCatalogDiscount;

}
