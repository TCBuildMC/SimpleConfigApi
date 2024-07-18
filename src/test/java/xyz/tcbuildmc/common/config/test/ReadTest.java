package xyz.tcbuildmc.common.config.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfig;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;

public class ReadTest {
    @Test
    public void gsonRead0() {
        String json = "{\"lang\": \"en_us\", \"time\": 114514}";

        TestConfig instance = SimpleConfig.getInstance().read(TestConfig.class, json, DefaultParsers.gson());
        System.out.println(instance);
    }
}
