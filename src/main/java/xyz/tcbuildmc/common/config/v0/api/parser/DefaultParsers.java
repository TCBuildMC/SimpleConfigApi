package xyz.tcbuildmc.common.config.v0.api.parser;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.config.v0.impl.parser.GsonParser;

/**
 * The default config parsers of {@code simple-config-v0}.
 * <p>
 * For now, the library just supports Gson.
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
}
