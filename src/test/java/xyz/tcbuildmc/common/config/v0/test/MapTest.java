package xyz.tcbuildmc.common.config.v0.test;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapTest {
    @Test
    public void testMerge() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("114", 514);

        System.out.println(map);

        map.merge("114", 515, (oldValue, newValue) -> newValue);

        System.out.println(map);
    }
}
