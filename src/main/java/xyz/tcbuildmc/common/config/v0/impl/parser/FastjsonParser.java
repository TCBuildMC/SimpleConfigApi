package xyz.tcbuildmc.common.config.v0.impl.parser;

import com.alibaba.fastjson2.JSON;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

public class FastjsonParser implements Parser {
    @Override
    public <T> Function<String, T> serialize(Class<T> clazz) {
        return content -> {
            try {
                T instance = JSON.to(clazz, content);

                if (instance == null) {
                    clazz.getDeclaredConstructor().newInstance();
                }

                return instance;
            } catch (InvocationTargetException |
                      InstantiationException |
                      IllegalAccessException |
                      NoSuchMethodException e) {

                throw new RuntimeException("Failed to parse.", e);
            }
        };
    }

    @Override
    public <T> Function<T, String> deserialize(Class<T> clazz) {
        return JSON::toJSONString;
    }
}
