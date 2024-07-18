package xyz.tcbuildmc.common.config.v0.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;

import java.io.File;
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

        String json = SimpleConfigApi.getInstance().write(TestConfig.class, content, DefaultParsers.gson());
        System.out.println(json);
    }

    @Test
    public void janksonWrite() {
        Map<String, Object> properties = new LinkedHashMap<>();

        properties.put("message", "hello world!");

        TestConfig content = new TestConfig("zh_cn",
                18,
                false,
                Arrays.asList("creeper", "steve", "minecraft", "jvav"),
                properties);

        SimpleConfigApi.getInstance().write(TestConfig.class, content, new File("run", "config.json5"), DefaultParsers.jankson());
    }
}
