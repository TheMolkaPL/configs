package org.diorite.configs;

import org.diorite.configs.annotations.ConfigClass;

import java.util.List;
import java.util.Map;

// this config class is styled via separate file
@ConfigClass
public interface ExampleStyledConfig extends Config<ExampleStyledConfig> {
    default int getValue() {
        return 5;
    }

    int getSecondValue();

    String getWithLocalizedComment();

    default String getAlwaysMultiLine() {
        return "even if it isn't multi line";
    }

    default String getFolded() {
        return "This long String will be folded to multiple lines. But it can also contain own new lines." +
            "\nAnd line at the end will be saved too.\n\n";
    }

    default SomeBean getInlineBean() {
        return new SomeBean(5, List.of("a", "b", "c"));
    }

    default Map<String, SomeBean> getMapOfObjects() {
        return Map.of(
            "keyA", new SomeBean(0x0A, List.of("a", "b", "c")),
            "keyB", new SomeBean(0x01, List.of("a", "e", "f")),
            "keyC", new SomeBean(0x03, List.of("g", "h", "j"))
        );
    }

}
