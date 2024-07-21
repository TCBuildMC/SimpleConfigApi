package xyz.tcbuildmc.common.config.v0.api;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import xyz.tcbuildmc.common.config.v0.api.parser.DefaultParsers;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;
import xyz.tcbuildmc.common.config.v0.impl.SimpleConfigApiImpl;
import xyz.tcbuildmc.common.config.v0.api.model.ConfigObject;

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
     * @see SimpleConfigApi#read(Class, String, Parser)
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
     * @see SimpleConfigApi#read(Class, String, Parser)
     */
    default <T> T read(Class<T> clazz, @NotNull Path path, Parser parser) {
        return read(clazz, path.toFile(), parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, String, Parser)
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
     * @see SimpleConfigApi#read(Class, String, Parser)
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
     * @see SimpleConfigApi#read(Class, String, Parser)
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
     * @see SimpleConfigApi#read(Class, String, Parser)
     */
    default <T> T read(Class<T> clazz, @NotNull URI uri, Parser parser) {
        try {
            return read(clazz, uri.toURL(), parser);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Failed to parse URI.", e);
        }
    }

    /**
     * @see SimpleConfigApi#read(Class, String, Parser)
     */
    default <T> T read(Class<T> clazz, byte[] bytes, Parser parser) {
        String content = IOUtils.toString(bytes, "utf-8");

        return read(clazz, content, parser);
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
     * @see SimpleConfigApi#write(Class, Object, Parser)
     */
    default <T> byte[] writeToBytes(Class<T> clazz, T content, Parser parser) {
        return write(clazz, content, parser).getBytes(StandardCharsets.UTF_8);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, Parser)
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
     * @see SimpleConfigApi#write(Class, Object, Parser)
     */
    default <T> void write(Class<T> clazz, T content, @NotNull Path path, Parser parser) {
        write(clazz, content, path.toFile(), parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, Parser)
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
     * @see SimpleConfigApi#write(Class, Object, Parser) 
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


    /**
     * @see SimpleConfigApi#read(Class, String, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(String content, Parser parser) {
        return read(ConfigObject.class, content, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, File, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(File file, Parser parser) {
        return read(ConfigObject.class, file, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, Path, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(Path path, Parser parser) {
        return read(ConfigObject.class, path, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, Reader, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(Reader reader, Parser parser) {
        return read(ConfigObject.class, reader, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, InputStream, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(InputStream is, Parser parser) {
        return read(ConfigObject.class, is, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, URL, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(URL url, Parser parser) {
        return read(ConfigObject.class, url, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, URI, Parser)
     * @since 1.2.0
     */
    default ConfigObject read(URI uri, Parser parser) {
        return read(ConfigObject.class, uri, parser);
    }

    /**
     * @see SimpleConfigApi#read(Class, byte[], Parser)
     * @since 1.2.0
     */
    default ConfigObject read(byte[] bytes, Parser parser) {
        return read(ConfigObject.class, bytes, parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, Parser)
     * @since 1.2.0
     */
    default String write(ConfigObject object, Parser parser) {
        return write(ConfigObject.class, object, parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, File, Parser)
     * @since 1.2.0
     */
    default void write(ConfigObject object, File file, Parser parser) {
        write(ConfigObject.class, object, file, parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, Path, Parser)
     * @since 1.2.0
     */
    default void write(ConfigObject object, Path path, Parser parser) {
        write(ConfigObject.class, object, path, parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, Writer, Parser)
     * @since 1.2.0
     */
    default void write(ConfigObject object, Writer writer, Parser parser) {
        write(ConfigObject.class, object, writer, parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, OutputStream, Parser)
     * @since 1.2.0
     */
    default void write(ConfigObject object, OutputStream os, Parser parser) {
        write(ConfigObject.class, object, os, parser);
    }

    /**
     * @see SimpleConfigApi#write(ConfigObject, Parser)
     * @see SimpleConfigApi#read(Class, String, Parser)
     * @since 1.2.1-hotfix.1
     */
    default <T> T toJavaObject(Class<T> clazz, ConfigObject object, Parser parser) {
        String config = write(object, parser);
        return (T) read(clazz, config, parser);
    }

    /**
     * @see SimpleConfigApi#write(Class, Object, Parser)
     * @see SimpleConfigApi#read(String, Parser)
     * @since 1.2.1-hotfix.1
     */
    default <T> ConfigObject toConfigObject(Class<T> clazz, T content, Parser parser) {
        String config = write(clazz, content, parser);
        return read(config, parser);
    }
}
