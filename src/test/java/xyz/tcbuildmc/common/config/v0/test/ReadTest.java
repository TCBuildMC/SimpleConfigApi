package xyz.tcbuildmc.common.config.v0.test;

import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;

public class ReadTest {
    @Test
    public void gsonRead0() {
        String json = "{\"lang\": \"en_us\", \"time\": 114514}";

        TestConfig instance = SimpleConfigApi.getInstance().read(TestConfig.class, json, DefaultParsers.gson());
        System.out.println(instance);
    }
}
