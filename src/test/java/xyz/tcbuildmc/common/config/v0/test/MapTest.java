package xyz.tcbuildmc.common.config.v0.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.model.ConfigObject;

import java.util.Arrays;
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

    @Test
    public void pathWalkerTest() {
        ConfigObject object = new ConfigObject();

        Map<String, Object> properties = new LinkedHashMap<>();
        Map<String, Object> ban = new LinkedHashMap<>();

        ban.put("tel", 114514);
        ban.put("qq", 1919810);
        properties.put("awa", Arrays.asList("MC", "Java", "Windows", "Server", ban));
        object.set("properties", properties);

        System.out.println((int) object.getByPath("properties.awa.5.tel"));
    }
}
