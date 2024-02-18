package no.hb.cart.shopping.utils;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationsUtil {

    public static Boolean validate(List<String> items, List<String> existingWatchIds) {
        List<String> differences = items.stream()
                .filter(element -> !existingWatchIds.contains(element))
                .collect(Collectors.toList());
        return differences.isEmpty();
    }

}
