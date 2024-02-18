package no.hb.cart.shopping.db.repo;

import no.hb.cart.shopping.db.entity.WatchCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WatchCatalogRepository extends JpaRepository<WatchCatalog, Integer> {
    @Query(value = "SELECT * FROM WATCH_CATALOG WHERE WATCH_ID = :watchId", nativeQuery = true)
    public WatchCatalog findItemByWatchId(@Param("watchId") String watchId);

    @Query(value = "SELECT WATCH_ID FROM WATCH_CATALOG ", nativeQuery = true)
    public List<String> findAllWatchIds();

}
