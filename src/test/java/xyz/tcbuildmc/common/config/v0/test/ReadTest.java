package xyz.tcbuildmc.common.config.v0.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;

import java.io.File;

public class ReadTest {
    @Test
    public void gsonRead() {
        File file = new File("run", "config.json");

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, file, DefaultParsers.gson());
        System.out.println(instance);
    }

    @Test
    public void janksonRead() {
        File file = new File("run", "config.json5");

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, file, DefaultParsers.jankson());
        System.out.println(instance);
    }

    @Test
    public void toml4jRead() {
        File file = new File("run", "config.toml");

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, file, DefaultParsers.toml4j());
        System.out.println(instance);
    }

    @Test
    public void snakeYamlRead() {
        File file = new File("run", "config.yml");

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, file, DefaultParsers.snakeYaml());
        System.out.println(instance);
    }

    @Test
    public void fastjsonRead() {
        File file = new File("run", "config.json");

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, file, DefaultParsers.fastjson());
        System.out.println(instance);
    }
}
