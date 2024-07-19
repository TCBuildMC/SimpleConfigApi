package xyz.tcbuildmc.common.config.v0.impl.manager;

import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;
import xyz.tcbuildmc.common.config.v0.api.manager.ConfigLoader;

public abstract class AbstractConfigManager<T> implements ConfigLoader {
    @Getter
    private final Class<T> clazz;
    @Getter
    private final Parser parser;

    @Nullable
    public T content;

    @Contract(pure = true)
    public AbstractConfigManager(Class<T> clazz, Parser parser) {
        this.clazz = clazz;
        this.parser = parser;
    }

    @Override
    public abstract void load();

    @Override
    public void reload() {
        this.load();
    }

    @Override
    public abstract void save();
}
