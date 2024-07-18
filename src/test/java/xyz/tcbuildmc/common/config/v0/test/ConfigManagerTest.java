package xyz.tcbuildmc.common.config.v0.test;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;
import xyz.tcbuildmc.common.config.v0.api.util.ConfigManager;
import xyz.tcbuildmc.common.config.v0.impl.util.FileConfigManager;

import java.io.File;
import java.io.IOException;

public class ConfigManagerTest {
    @Test
    public void loadConfig() {
        FileConfigManager<TestConfig> manager = ConfigManager.ofFile(TestConfig.class, DefaultParsers.jankson(), new File("run", "config.json5"));

        manager.load();
        TestConfig config = manager.getContent();

        if (config == null) {
            throw new NullPointerException();
        }
        System.out.println(config);

        manager.save();
    }

    @Test
    public void saveEditedConfig() throws IOException {
        FileUtils.copyFile(new File("run", "config.json5"), new File("run", "config-copied.json5"));
        FileConfigManager<TestConfig> manager = ConfigManager.ofFile(TestConfig.class, DefaultParsers.jankson(), new File("run", "config-copied.json5"));

        manager.load();
        TestConfig config = manager.getContent();

        if (config == null) {
            throw new NullPointerException();
        }

        config.setTime(config.getTime() - 1);
        manager.setContent(config);
        manager.save();
    }
}
