package xyz.tcbuildmc.common.config.v0.api.parser;

import blue.endless.jankson.Jankson;
import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.config.v0.impl.parser.GsonParser;
import xyz.tcbuildmc.common.config.v0.impl.parser.JanksonParser;

/**
 * The default config parsers of {@code simple-config-v0}.
 * <p>
 * For now, the library just supports Gson, Jankson.
 *
 * @since 1.0.0
 * @author Block_Mrlimr267
 */
public interface DefaultParsers {
    /**
     * Get Gson config parser to parse.
     *
     * @return a {@link Parser} use Gson
     */
    @Contract(" -> new")
    @NotNull
    static Parser gson() {
        return new GsonParser();
    }

    /**
     * Get Gson config parser to parse.
     *
     * @param gson a {@link Gson} instance.
     * @return a {@link Parser} use Gson
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
     * @return a {@link Parser} use Jankson
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
     * @return a {@link Parser} use Jankson
     *
     * @since 1.0.2
     */
    static Parser jankson(Jankson jankson) {
        return new JanksonParser(jankson);
    }
}
