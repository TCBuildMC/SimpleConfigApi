package xyz.tcbuildmc.common.config.v0.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;

import java.io.File;

public class ReadTest {
    @Test
    public void gsonRead() {
        String json = "{\"lang\": \"en_us\", \"time\": 114514}";

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, json, DefaultParsers.gson());
        System.out.println(instance);
    }

    @Test
    public void janksonRead() {
        File file = new File("run", "config.json5");

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, file, DefaultParsers.jankson());
        System.out.println(instance);
    }
}
