package xyz.tcbuildmc.common.config.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfig;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriteTest {
    @Test
    public void gsonWrite() {
        Map<String, Object> properties = new LinkedHashMap<>();

        properties.put("message", "hello world!");

        TestConfig content = new TestConfig("zh_cn",
                18,
                false,
                Arrays.asList("creeper", "steve", "minecraft", "jvav"),
                properties);

        String json = SimpleConfig.getInstance().write(TestConfig.class, content, DefaultParsers.gson());
        System.out.println(json);
    }
}
