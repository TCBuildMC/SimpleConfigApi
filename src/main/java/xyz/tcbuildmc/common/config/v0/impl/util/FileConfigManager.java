package xyz.tcbuildmc.common.config.v0.impl.util;

import lombok.Getter;
import xyz.tcbuildmc.common.config.v0.api.SimpleConfigApi;
import xyz.tcbuildmc.common.config.v0.api.parser.Parser;

import java.io.File;

@Getter
public class FileConfigManager<T> extends AbstractConfigManager<T> {
    private final File file;

    public FileConfigManager(Class<T> clazz, Parser parser, File file) {
        super(clazz, parser);
        this.file = file;
    }

    @Override
    public void load() {
        setContent(SimpleConfigApi.getInstance().read(getClazz(), this.file, getParser()));
    }

    @Override
    public void save() {
        SimpleConfigApi.getInstance().write(getClazz(), getContent(), this.file, getParser());
        this.load();
    }
}
