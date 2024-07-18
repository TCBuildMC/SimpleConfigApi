package xyz.tcbuildmc.common.config.v0.impl.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

@ApiStatus.Internal
public final class GsonParser implements Parser {
    private final Gson gson;

    private GsonParser(Gson gson) {
        this.gson = gson;
    }

    public GsonParser() {
        this(new GsonBuilder()
                .setPrettyPrinting()
                .create());
    }

    @Override
    public <T> Function<String, T> read(Class<T> clazz) {
        return content -> {
            try {
                T instance = this.gson.fromJson(content, clazz);

                if (instance == null) {
                    return clazz.getDeclaredConstructor().newInstance();
                }

                return instance;
            } catch (JsonSyntaxException |
                     InvocationTargetException |
                     InstantiationException |
                     IllegalAccessException |
                     NoSuchMethodException e) {

                throw new RuntimeException("Failed to parse.", e);
            }
        };
    }

    @Override
    public <T> Function<T, String> write(Class<T> clazz) {
        return this.gson::toJson;
    }
}
