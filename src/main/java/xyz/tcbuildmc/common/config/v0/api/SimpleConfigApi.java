package xyz.tcbuildmc.common.config.v0.api;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.function.IOSupplier;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;
import xyz.tcbuildmc.common.config.v0.impl.SimpleConfigApiImpl;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/**
 * The core of {@code simple-config-v0}, just involving two basic methods: {@code read(...)} and {@code write(...)}.
 * <p>
 * To work with the library, simply call {@link SimpleConfigApi#getInstance()} and use the methods.
 *
 * @since 1.0.0
 * @author Block_Mrlimr267
 */
@SuppressWarnings("unused")
public interface SimpleConfigApi {
    /**
     * Get an instance of {@link SimpleConfigApi}.
     *
     * @return an instance of {@link SimpleConfigApi}.
     */
    static SimpleConfigApi getInstance() {
        return (SimpleConfigApiImpl.INSTANCE != null) ? SimpleConfigApiImpl.INSTANCE : new SimpleConfigApiImpl();
    }

    /**
     * Parse the given config content and cast to the given class type.
     * <p>
     * There must be a constructor with no args in the class.
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    <T> T read(Class<T> clazz, String content, Parser parser);

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, String, Parser)}, just supporting {@link File}.
     *
     * @param clazz the class type of your config class
     * @param file the config {@link File}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, File file, Parser parser) {
        try {
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

            return read(clazz, content, parser);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read config file.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, File, Parser)}, just supporting {@link File} (By {@link Path#toFile()}).
     *
     * @param clazz the class type of your config class
     * @param path the {@link Path} of config {@link File}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, @NotNull Path path, Parser parser) {
        return read(clazz, path.toFile(), parser);
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, String, Parser)}, just supporting {@link Reader}.
     *
     * @param clazz the class type of your config class
     * @param reader an instance of {@link Reader}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, Reader reader, Parser parser) {
        try {
            String content = IOUtils.toString(reader);

            return read(clazz, content, parser);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, String, Parser)}, just supporting {@link InputStream}.
     *
     * @param clazz the class type of your config class
     * @param is an instance of {@link InputStream}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, InputStream is, Parser parser) {
        try {
            String content = IOUtils.toString(is, StandardCharsets.UTF_8);

            return read(clazz, content, parser);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, String, Parser)}, just supporting {@link URL}.
     *
     * @param clazz the class type of your config class
     * @param url an instance of {@link URL}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, URL url, Parser parser) {
        try {
            String content = IOUtils.toString(url, StandardCharsets.UTF_8);

            return read(clazz, content, parser);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, URL, Parser)}, just supporting {@link URI}.
     * <p>
     * Also see {@link SimpleConfigApi#read(Class, String, Parser)} and {@link SimpleConfigApi#read(Class, URL, Parser)}.
     *
     * @param clazz the class type of your config class
     * @param uri an instance of {@link URI}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, @NotNull URI uri, Parser parser) {
        try {
            return read(clazz, uri.toURL(), parser);

        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to parse URI.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, String, Parser)}, just supporting {@link Byte}.
     *
     * @param clazz the class type of your config class
     * @param bytes a {@code byte} array
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, byte[] bytes, Parser parser) {
        String content = IOUtils.toString(bytes, "utf-8");

        return read(clazz, content, parser);
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#read(Class, String, Parser)}, just supporting {@link IOSupplier}.
     *
     * @param clazz the class type of your config class
     * @param supplier an instance of {@link IOSupplier}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return an instance of your config class
     */
    default <T> T read(Class<T> clazz, IOSupplier<InputStream> supplier, Parser parser) {
        try {
            String content = IOUtils.toString(supplier, StandardCharsets.UTF_8);

            return read(clazz, content, parser);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get.", e);
        }
    }


    /**
     * Parse the given instance of your config class to a {@link String}.
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return a {@link String} of the instance of your config class
     */
    <T> String write(Class<T> clazz, T content, Parser parser);

    /**
     * A simple overload method of {@link SimpleConfigApi#write(Class, Object, Parser)}, and cast it to a {@code byte} array.
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     * @return a {@code byte} array of the instance of your config class
     */
    default <T> byte[] writeToBytes(Class<T> clazz, T content, Parser parser) {
        return write(clazz, content, parser).getBytes();
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#write(Class, Object, Parser)}, and write it to a {@link File}.
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param file the target {@link File} to write
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     */
    default <T> void write(Class<T> clazz, T content, File file, Parser parser) {
        String config = write(clazz, content, parser);

        try {
            FileUtils.writeStringToFile(file, config, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to file.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#write(Class, Object, File, Parser)}, and write it to a {@link File} (By {@link Path#toFile()}).
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param path the target {@link Path} of {@link File} to write
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     */
    default <T> void write(Class<T> clazz, T content, @NotNull Path path, Parser parser) {
        write(clazz, content, path.toFile(), parser);
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#write(Class, Object, Parser)}, and write by a {@link Writer} instance.
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param writer an instance of {@link Writer}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     */
    default <T> void write(Class<T> clazz, T content, @NotNull Writer writer, Parser parser) {
        String config = write(clazz, content, parser);

        try {
            writer.write("");
            writer.write(config);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write.", e);
        }
    }

    /**
     * A simple overload method of {@link SimpleConfigApi#write(Class, Object, Parser)}, and write by a {@link OutputStream} instance.
     *
     * @param clazz the class type of your config class
     * @param content the config content
     * @param os an instance of {@link OutputStream}
     * @param parser the config parser. Also see {@link Parser} and {@link DefaultParsers}
     * @param <T> the type of your config class
     */
    default <T> void write(Class<T> clazz, T content, @NotNull OutputStream os, Parser parser) {
        byte[] config = writeToBytes(clazz, content, parser);

        try {
            os.write(config);
            os.flush();
            os.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write.", e);
        }
    }
}
