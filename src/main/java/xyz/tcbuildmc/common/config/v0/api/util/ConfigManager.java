package xyz.tcbuildmc.common.config.v0.api.util;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;
import xyz.tcbuildmc.common.config.v0.impl.util.FileConfigManager;
import xyz.tcbuildmc.common.config.v0.impl.util.URLConfigManager;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

/**
 * An easy api to load/ save config.
 * <p>
 * It's like Bukkit config api.
 *
 * @since 1.0.1
 * @author Block_Mrlimr267
 */
public interface ConfigManager {
    /**
     * Get an implementation of {@link ConfigManager}.
     * <p>
     * Use {@link File} to load/ save.
     *
     * @param clazz the class type of your config class
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param file the config {@link File}
     * @param <T> the type of your config class
     * @return an implementation of {@link ConfigManager}
     */
    @Contract("_, _, _ -> new")
    @NotNull
    static <T> FileConfigManager<T> ofFile(Class<T> clazz, Parser parser, File file) {
        return new FileConfigManager<>(clazz, parser, file);
    }

    /**
     * A simple overload method of {@link ConfigManager#ofFile(Class, Parser, File)} (By {@link Path#toFile()}).
     *
     * @param clazz the class type of your config class
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param path the {@link Path} of the config {@link File}
     * @param <T> the type of your config class
     * @return an implementation of {@link ConfigManager}
     */
    @Contract("_, _, _ -> new")
    @NotNull
    static <T> FileConfigManager<T> ofPath(Class<T> clazz, Parser parser, Path path) {
        return ofFile(clazz, parser, path.toFile());
    }

    /**
     * Get an implementation of {@link ConfigManager}.
     * <p>
     * Use {@link URL} to load. (It doesn't support save!)
     *
     * @param clazz the class type of your config class
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param url a {@link URL} of the config file.
     * @param <T> the type of your config class
     * @return an implementation of {@link ConfigManager}
     */
    @Contract("_, _, _ -> new")
    @ApiStatus.Experimental
    @NotNull
    static <T> URLConfigManager<T> ofURL(Class<T> clazz, Parser parser, URL url) {
        return new URLConfigManager<>(clazz, parser, url);
    }

    /**
     * A simple overload method of {@link ConfigManager#ofURL(Class, Parser, URL)}.
     *
     * @param clazz the class type of your config class
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param uri a {@link URI} of the config file.
     * @param <T> the type of your config class
     * @return an implementation of {@link ConfigManager}
     */
    @Contract("_, _, _ -> new")
    @ApiStatus.Experimental
    @NotNull
    static <T> URLConfigManager<T> ofURI(Class<T> clazz, Parser parser, URI uri) {
        try {
            return ofURL(clazz, parser, uri.toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to parse URI.", e);
        }
    }

    /**
     * To load the config.
     */
    void load();

    /**
     * To reload the config.
     * <p>
     * By default, this method just call {@link ConfigManager#load()}.
     */
    void reload();

    /**
     * To save the config.
     */
    void save();
}
