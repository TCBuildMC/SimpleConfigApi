package xyz.tcbuildmc.common.config.v0.api.parser;

import blue.endless.jankson.Jankson;
import com.google.gson.Gson;
import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;
import xyz.tcbuildmc.common.config.v0.impl.parser.*;

/**
 * The default config parsers of {@code simple-config-v0}.
 * <p>
 * For now, the library just supports Gson, Jankson, Toml4j.
 *
 * @since 1.0.0
 */
public interface DefaultParsers {
    /**
     * Get Gson config parser to parse.
     *
     * @return a {@link Parser} using Gson
     */
    @Contract(" -> new")
    @NotNull
    static Parser gson() {
        return new GsonParser();
    }

    /**
     * Get Gson config parser to parse.
     *
     * @param gson a {@link Gson} instance
     * @return a {@link Parser} using Gson
     * @since 1.0.2
     */
    @Contract(value = "_ -> new", pure = true)
    @NotNull
    static Parser gson(Gson gson) {
        return new GsonParser(gson);
    }

    /**
     * Get Jankson config parser to parse.
     *
     * @return a {@link Parser} using Jankson
     * @since 1.0.2
     */
    @Contract(value = " -> new", pure = true)
    @NotNull
    static Parser jankson() {
        return new JanksonParser();
    }

    /**
     * Get Jankson config parser to parse.
     *
     * @param jankson a {@link Jankson} instance
     * @return a {@link Parser} using Jankson
     * @since 1.0.2
     */
    @Contract(value = "_ -> new", pure = true)
    @NotNull
    static Parser jankson(Jankson jankson) {
        return new JanksonParser(jankson);
    }

    /**
     * Get Toml4j config parser to parse.
     *
     * @param commented if you want to parse comments
     * @return a {@link Parser} using Toml4j
     * @since 1.2.2
     */
    @Contract("_ -> new")
    @NotNull
    static Parser toml4j(boolean commented) {
        if (commented) {
            return new CommentToml4jParser();
        }

        return new Toml4jParser();
    }

    /**
     * Get Toml4j config parser to parse.
     *
     * @param toml a {@link Toml} instance
     * @param tomlWriter a {@link TomlWriter} instance
     * @param commented if you want to parse comments
     * @return a {@link Parser} using Toml4j
     * @since 1.2.2
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    @NotNull
    static Parser toml4j(Toml toml, TomlWriter tomlWriter, boolean commented) {
        if (commented) {
            return new CommentToml4jParser(toml, tomlWriter);
        }

        return new Toml4jParser(toml, tomlWriter);
    }

    /**
     * Get SnakeYaml config parser to parse.
     *
     * @return a {@link Parser} using SnakeYaml
     * @since 1.1.1
     */
    @Contract(" -> new")
    @NotNull
    static Parser snakeYaml() {
        return new SnakeYamlParser();
    }

    /**
     * Get SnakeYaml config parser to parse.
     *
     * @param yaml a {@link Yaml} instance
     * @return a {@link Parser} using SnakeYaml
     * @since 1.1.1
     */
    @Contract(value = "_ -> new", pure = true)
    @NotNull
    static Parser snakeYaml(Yaml yaml) {
        return new SnakeYamlParser(yaml);
    }

    static FastjsonParser fastjson() {
        return new FastjsonParser();
    }
}
