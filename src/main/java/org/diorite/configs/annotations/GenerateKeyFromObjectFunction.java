/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017. Diorite (by Bartłomiej Mazur (aka GotoFinal))
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.diorite.configs.annotations;

import org.intellij.lang.annotations.Language;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used with {@link AsMap} to mark list properties in config interfaces that are saved as maps. <br>
 * {@link AsMap} Annotation needs to be placed on property get method, and <br>
 * {@link GenerateKeyFromObjectFunction} on private mapping function, function that takes object of list generic type and returns
 * String/simple value - key of value in map. If returned value isn't simple then manager will try to find serializer to simple type -
 * like string, and otherwise it will throw error. <br>
 * {@link DeserializeKeyFunction} should be then used to annotate method that change that simple key back to object properties - unless
 * valid serializer exist. <br/>
 * <pre> {@code
 * @AsMap(key="id")
 * list:
 * - id: myId
 *   x: 3
 *
 * list:
 *   myId:
 *     x: 3
 * --------------
 *
 * @AsMap(keys="id") // simple serializer for ID class must exist
 * list:
 * - id: {category: x, name: y, id: 34}
 *   x: 3
 *
 * list:
 *   'x:y:34':
 *   x: 3
 * --------------
 * @AsMap(keys = {"category", "internalID"},
 *        createKey = "x.category + ':' + x.internalID)",
 *        deserializeKey = "{category: x.split(':')[0], internalID: x.split(':')[1]}")
 * list:
 * - category: X
 *   internalID: 54
 *   x: 3
 *
 * list:
 *   'x:54':
 *     x: 3
 * }
 * </pre>
 *
 * {@link GenerateKeyFromObjectFunction} can be also used on Map properties to override default key serialization, same with
 * {@link DeserializeKeyFunction} for deserialization.
 * <pre>{@code
 * @GenerateKeyFromObjectFunction("'data_' + x")
 * @DeserializeKeyFunction("x.replace('data_', '')")
 * Map<UUID, String> getSomeData();
 * someData:
 *   data_e41a2f31-1ca6-4f70-b9c3-2b13a8d2675f: value
 *
 * }</pre>
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateKeyFromObjectFunction {
    /**
     * @return language to use and options, must be supported by configuration manager
     */
    ScriptLanguage language() default @ScriptLanguage;

    /**
     * Returns groovy script that will be used to map object to string.
     *
     * @return groovy script that will be used to map object to string.
     */
    @Language(value = "groovy", prefix = "String toString(def x, Config config) {", suffix = "}")
    String value() default "";

    /**
     * Returns property name that use this mapping function. <br>
     * Optional if annotation is over property with value filled with groovy mapper.
     *
     * @return property name that use this mapping function.
     */
    String property() default "";
}
