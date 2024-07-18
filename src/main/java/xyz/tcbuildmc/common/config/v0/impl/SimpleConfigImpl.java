package xyz.tcbuildmc.common.config.v0.impl;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfig;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

@ApiStatus.Internal
public final class SimpleConfigImpl implements SimpleConfig {
    @Nullable
    public static SimpleConfig INSTANCE;

    public SimpleConfigImpl() {
        SimpleConfigImpl.INSTANCE = this;
    }

    @Override
    public <T> T read(Class<T> clazz, String content, Parser parser) {
        return parser.read(clazz)
                .apply(content);
    }

    @Override
    public <T> String write(Class<T> clazz, T content, Parser parser) {
        return parser.write(clazz)
                .apply(content);
    }
}
