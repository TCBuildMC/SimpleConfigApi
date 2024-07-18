package xyz.tcbuildmc.common.config.v0.test;

import lombok.*;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class TestConfig {
    private String lang;
    private int time;
    private boolean enable;
    private List<String> depends;
    private Map<String, ?> properties;
}
