package xyz.tcbuildmc.common.config.v0.impl;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

@ApiStatus.Internal
public final class SimpleConfigApiImpl implements SimpleConfigApi {
    @Nullable
    public static SimpleConfigApi INSTANCE;

    public SimpleConfigApiImpl() {
        SimpleConfigApiImpl.INSTANCE = this;
    }

    @Override
    public <T> T read(Class<T> clazz, String content, @NotNull Parser parser) {
        return parser.toObject(clazz).apply(content);
    }

    @Override
    public <T> String write(Class<T> clazz, T content, @NotNull Parser parser) {
        return parser.toConfig(clazz).apply(content);
    }
}
