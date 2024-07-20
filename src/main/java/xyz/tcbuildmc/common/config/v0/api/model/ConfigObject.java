package xyz.tcbuildmc.common.config.v0.api.model;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * An implementation of {@link Map}, for reading values by keys from config.
 *
 * @since 1.2.0
 */
@SuppressWarnings("unused")
public class ConfigObject extends LinkedHashMap<String, Object> implements Map<String, Object> {
    /**
     * An implementation of {@link Map#get(Object)} (Config key is {@link String} only).
     *
     * @param key the key of config
     * @param <T> a type
     * @return the value of the config key
     */
    @Nullable
    public <T> T get(String key) {
        return (T) super.get(key);
    }

    /**
     * An implementation of {@link Map#getOrDefault(Object, Object)} (Config key is {@link String} only).
     *
     * @param key the key of config
     * @param defaultValue if the value of the config key is null, this method will return it.
     * @param <T> a type
     * @return the value of the config key. If the value is null, it will be {@code defaultValue}
     */
    @NotNull
    public <T> T getOrDefault(String key, T defaultValue) {
        T value = get(key);

        if (value == null) {
            if (defaultValue == null) {
                throw new NullPointerException();
            }

            return defaultValue;
        }

        return value;
    }

    /**
     * @see ConfigObject#getOrDefault(Object, Object)
     */
    @Contract("_, null -> fail")
    @NotNull
    public <T> T getOrDefault(String key, Supplier<T> supplier) {
        if (supplier == null) {
            throw new NullPointerException();
        }

        return this.getOrDefault(key, supplier.get());
    }

    /**
     * @see ConfigObject#get(String) 
     */
    @NotNull
    public <T> T getOrThrow(String key) {
        T value = this.get(key);

        if (value == null) {
            throw new NullPointerException();
        }

        return value;
    }

    /**
     * @see ConfigObject#getOrThrow(String) 
     */
    @NotNull
    public <T, X extends Throwable> T getOrThrow(String key, X e) throws X {
        T value = this.get(key);

        if (value == null) {
            throw e;
        }

        return value;
    }

    /**
     * @see ConfigObject#getOrThrow(String, Throwable)
     */
    @NotNull
    public <T, X extends Throwable> T getOrThrow(String key, Supplier<X> supplier) throws X {
        T value = this.get(key);

        if (value == null) {
            throw supplier.get();
        }

        return value;
    }

    /**
     * An alternative of {@link Map#put(Object, Object)} and {@link Map#remove(Object)}.
     * <p>
     * If the value is {@code null}, the config key and value will be deleted.
     * <p>
     * If the value in config exists, it will be replaced with value in the method.
     * <p>
     * Otherwise, it will add the key and the value to the config.
     *
     * @param key the config key
     * @param value a value of the key
     */
    public void set(String key, @Nullable Object value) {
        if (value == null) {
            super.remove(key);
            return;
        }

        super.merge(key, value, ConfigObject::replaceOld);
    }

    /**
     * Use {@link ConfigObject#set(String, Object)} instead.
     *
     * @deprecated
     */
    @Deprecated
    public void delete(String key) {
        this.set(key, null);
    }

    /**
     * Returns whether the value of the key exists.
     *
     * @param key the config key
     * @return Whether the value of the key exists
     */
    public boolean contains(String key) {
        return super.containsKey(key);
    }

    /**
     * @see ConfigObject#get(String)
     */
    public int getInt(String key) throws NullPointerException {
        return (int) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public int getIntOrDefault(String key, int defaultValue) {
        return (int) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier) 
     */
    public int getIntOrDefault(String key, Supplier<Integer> supplier) {
        return (int) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public int getIntOrThrow(String key) {
        return (int) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    public float getFloat(String key) throws NullPointerException {
        return (float) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public float getFloatOrDefault(String key, float defaultValue) {
        return (float) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public float getFloatOrDefault(String key, Supplier<Float> supplier) {
        return (float) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public float getFloatOrThrow(String key) {
        return (float) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    public double getDouble(String key) throws NullPointerException {
        return (double) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public double getDoubleOrDefault(String key, double defaultValue) {
        return (double) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public double getDoubleOrDefault(String key, Supplier<Double> supplier) {
        return (double) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public double getDoubleOrThrow(String key) {
        return (double) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    public long getLong(String key) throws NullPointerException {
        return (long) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public long getLongOrDefault(String key, long defaultValue) {
        return (long) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public long getLongOrDefault(String key, Supplier<Long> supplier) {
        return (long) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public long getLongOrThrow(String key) {
        return (long) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    public short getShort(String key) throws NullPointerException {
        return (short) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public short getShortOrDefault(String key, short defaultValue) {
        return (short) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public short getShortOrDefault(String key, Supplier<Short> supplier) {
        return (short) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public short getShortOrThrow(String key) {
        return (short) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    @Nullable
    public String getString(String key) {
        return (String) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public String getStringOrDefault(String key, String defaultValue) {
        return (String) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public String getStringOrDefault(String key, Supplier<String> supplier) {
        return (String) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public String getStringOrThrow(String key) {
        return (String) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    @Nullable
    public List<?> getList(String key) {
        return (List<?>) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public List<?> getListOrDefault(String key, List<?> defaultValue) {
        return (List<?>) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public List<?> getListOrDefault(String key, Supplier<List<?>> supplier) {
        return (List<?>) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public List<?> getListOrThrow(String key) {
        return (List<?>) getOrThrow(key);
    }


    /**
     * @see ConfigObject#get(String)
     */
    @Nullable
    public Map<String, ?> getMap(String key) throws NullPointerException {
        return (Map<String, ?>) get(key);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Object)
     */
    public Map<String, ?> getMapOrDefault(String key, Map<String, ?> defaultValue) {
        return (Map<String, ?>) getOrDefault(key, defaultValue);
    }

    /**
     * @see ConfigObject#getOrDefault(String, Supplier)
     */
    public Map<String, ?> getMapOrDefault(String key, Supplier<Map<String, ?>> supplier) {
        return (Map<String, ?>) getOrDefault(key, supplier);
    }

    /**
     * @see ConfigObject#getOrThrow(String)
     */
    public Map<String, ?> getMapOrThrow(String key) {
        return (Map<String, ?>) getOrThrow(key);
    }


    /**
     * A helper method for {@link ConfigObject#set(String, Object)}.
     * <p>
     * At the same time, this is also an implementation of {@link java.util.function.BiFunction#apply(Object, Object)}.
     * <p>
     * Just be used to replace the {@code oldValue}.
     * 
     * @param oldValue the old value
     * @param newValue the new value
     * @return the new value
     */
    @Contract(value = "_, _ -> param2", pure = true)
    public static Object replaceOld(Object oldValue, Object newValue) {
        return newValue;
    }
}
