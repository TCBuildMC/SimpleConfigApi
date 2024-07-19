package xyz.tcbuildmc.common.config.v0.impl.manager;

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
        super.content = SimpleConfigApi.getInstance().read(getClazz(), this.file, getParser());
    }

    @Override
    public void save() {
        SimpleConfigApi.getInstance().write(getClazz(), super.content, this.file, getParser());
        this.load();
    }
}
