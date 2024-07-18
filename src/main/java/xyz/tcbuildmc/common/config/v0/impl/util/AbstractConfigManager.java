package xyz.tcbuildmc.common.config.v0.impl.util;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;
import xyz.tcbuildmc.common.config.v0.api.util.ConfigManager;

@Getter
public abstract class AbstractConfigManager<T> implements ConfigManager {
    private final Class<T> clazz;
    private final Parser parser;

    @Setter
    @Nullable
    private T content;

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
