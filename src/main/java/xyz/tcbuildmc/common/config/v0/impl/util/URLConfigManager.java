package xyz.tcbuildmc.common.config.v0.impl.util;

import lombok.Getter;
import org.jetbrains.annotations.ApiStatus;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

import java.net.URL;

@ApiStatus.Experimental
@Getter
public class URLConfigManager<T> extends AbstractConfigManager<T> {
    private final URL url;

    public URLConfigManager(Class<T> clazz, Parser parser, URL url) {
        super(clazz, parser);
        this.url = url;
    }

    @Override
    public void load() {
        setContent(SimpleConfigApi.getInstance().read(getClazz(), this.url, getParser()));
    }

    @Override
    public void save() {
        throw new UnsupportedOperationException();
    }
}
