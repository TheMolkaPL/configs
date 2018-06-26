package org.diorite.configs.annotations.style;

import org.diorite.configs.style.StringBlockNewLinesStyle;
import org.diorite.configs.style.StringBlockStyle;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark given property to use literal style (if all condition are matched)
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StringBlock {
    /**
     * String block style to use, literal or folded
     */
    StringBlockStyle style() default StringBlockStyle.LITERAL;

    /**
     * Style for new lines at the end of block, all new lines at the end can be kept, only one new line at the end, or always trim all
     * new lines at the end.
     */
    StringBlockNewLinesStyle linesStyle() default StringBlockNewLinesStyle.TRIM_END;

    /**
     * Minimum length of string to apply this style
     */
    int minimumLength() default 1;

    /**
     * How many new lines must be in string to use this type
     */
    int minimumLines() default 1;

    /**
     * Spacing to use for each line of string, values below 0 will cause to use default spacing of configuration file.
     */
    int spacing() default - 1;

    /**
     * Depth of children nodes that should by affected by this settings - like higher values can be used to apply this annotation for
     * nested nodes inside map/list.
     */
    int depth() default 1;
}
