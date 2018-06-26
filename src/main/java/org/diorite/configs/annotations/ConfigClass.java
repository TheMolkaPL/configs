package org.diorite.configs.annotations;

import org.diorite.configs.annotations.meta.Footer;
import org.diorite.configs.annotations.meta.Header;

/**
 * Used over configuration classes to specify additional options
 */
public @interface ConfigClass {
    /**
     * @return default file name to use if no name is provided.
     */
    String fileName() default "";

    /**
     * @return default naming policy for this configuration
     */
    Class<?> namingPolicy() default Class.class; // TODO

    /**
     * @return header comment of configuration file, can be placed above config class too.
     */
    Header header() default @Header({});

    /**
     * @return footer comment of configuration file, can be placed above config class too.
     */
    Footer footer() default @Footer({});
}
