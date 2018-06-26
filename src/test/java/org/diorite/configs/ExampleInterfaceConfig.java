package org.diorite.configs;

import org.diorite.configs.annotations.meta.Comment;
import org.diorite.configs.annotations.ConfigClass;
import org.diorite.configs.annotations.meta.Header;

@ConfigClass
@Header("Header of config file")
public interface ExampleInterfaceConfig extends Config<ExampleInterfaceConfig> {
    @Comment("Comment")
    default int getValue() {
        return 5; // default value
    }

    // setter validator - optional
    default void setValue(int newValue) {
        // this.config() - escapes from this context to raw config values
        this.config().setValue(Math.abs(newValue));
    }

    @Comment({"Comment",
        "More lines"})
    default int getSecondValue() {
        return this.defaults().getValue(); // shared default value
    }

}
