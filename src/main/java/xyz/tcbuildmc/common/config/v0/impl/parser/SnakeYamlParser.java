package xyz.tcbuildmc.common.config.v0.impl.parser;

import org.yaml.snakeyaml.Yaml;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

import java.util.function.Function;

public class SnakeYamlParser implements Parser {
    private final Yaml yaml;

    public SnakeYamlParser(Yaml yaml) {
        this.yaml = yaml;
    }

    public SnakeYamlParser() {
        this(new Yaml());
    }

    @Override
    public <T> Function<String, T> serialize(Class<T> clazz) {
        return this.yaml::load;
    }

    @Override
    public <T> Function<T, String> deserialize(Class<T> clazz) {
        return this.yaml::dump;
    }
}
