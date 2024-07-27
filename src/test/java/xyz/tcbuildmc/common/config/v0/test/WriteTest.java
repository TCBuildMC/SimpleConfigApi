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

        SimpleConfigApi.getInstance().write(TestConfig.class, content, new File("run", "config.json"), DefaultParsers.gson());
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

    @Test
    public void toml4jWrite() {
        Map<String, Object> properties = new LinkedHashMap<>();

        properties.put("message", "hello world!");

        TestConfig content = new TestConfig("zh_cn",
                18,
                false,
                Arrays.asList("creeper", "steve", "minecraft", "jvav"),
                properties);

        System.out.println(SimpleConfigApi.getInstance().write(TestConfig.class, content, DefaultParsers.toml4j(true)));
    }

    @Test
    public void snakeYamlWrite() {
        Map<String, Object> properties = new LinkedHashMap<>();

        properties.put("message", "hello world!");

        TestConfig content = new TestConfig("zh_cn",
                18,
                false,
                Arrays.asList("creeper", "steve", "minecraft", "jvav"),
                properties);

        System.out.println(SimpleConfigApi.getInstance().write(TestConfig.class, content, DefaultParsers.snakeYaml()));
    }

    @Test
    public void fastjsonWrite() {
        Map<String, Object> properties = new LinkedHashMap<>();

        properties.put("message", "hello world!");

        TestConfig content = new TestConfig("zh_cn",
                18,
                false,
                Arrays.asList("creeper", "steve", "minecraft", "jvav"),
                properties);

        SimpleConfigApi.getInstance().write(TestConfig.class, content, new File("run", "config.json"), DefaultParsers.fastjson());
    }
}
