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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to save list of objects as map, examples:
 * <pre>
 * \@AsMap(key="id")
 * list:
 * - id: myId
 *   x: 3
 *
 * list:
 *   myId:
 *     x: 3
 * --------------
 *
 * \@AsMap(keys="id") // simple serializer for ID class must exist
 * list:
 * - id: {category: x, name: y, id: 34}
 *   x: 3
 *
 * list:
 *   'x:y:34':
 *   x: 3
 * --------------
 * \@AsMap(keys = {"category", "internalID"},
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
 * </pre>
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AsMap {
    /**
     * @return keys to be removed from object, only one simple key is supported by default, if more than one key is provided you need to
     *     use {@link GenerateKeyFromObjectFunction} and {@link DeserializeKeyFunction} to provide a way to convert that properties to
     *     map key, and map
     *     key to properties.
     */
    String[] keys();

    /**
     * @return language to use and options, must be supported by configuration manager
     */
    ScriptLanguage language() default @ScriptLanguage;

    /**
     * Alternative to {@link GenerateKeyFromObjectFunction} </br>
     * Example:
     * <pre>
     * \@AsMap(keys = {"category", "internalID"},
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
     * </pre>
     *
     * @return script that takes object "x" and changes it to key that can be used in map - if returned object isn't a simple type
     *     serializer to simple type will be used - if no such serializer exists it will fallback to yaml object to object map.
     */
    String createKey() default "";

    /**
     * Alternative to {@link DeserializeKeyFunction} </br>
     * Example:
     * <pre>
     * \@AsMap(keys = {"category", "internalID"},
     *         createKey = "x.category + ':' + x.internalID)",
     *         deserializeKey = "{category: x.split(':')[0], internalID: x.split(':')[1]}")
     * list:
     * - category: X
     *   internalID: 54
     *   x: 3
     *
     * list:
     *   'x:54':
     *     x: 3
     * </pre>
     *
     * @return script that takes string "x" and changes it back to object/map of missing properties. If no function/code is provided
     *     manager will try to find simple deserializer of key.
     */
    String deserializeKey() default "";

    /**
     * If only one property is present after extracting key map can be simplified by moving that property directly to key:
     * <pre>
     * \@AsMap(keys = {"category", "internalID"},
     *         createKey = "x.category + ':' + x.internalID)",
     *         deserializeKey = "{category: x.split(':')[0], internalID: x.split(':')[1]}",
     *         simplify = "x")
     * list:
     * - category: X
     *   internalID: 54
     *   x: 3
     *
     * list:
     *   'x:54': 3
     * ------------------
     * \@AsMap(key="id", simplify = "x")
     * list:
     * - id: myId
     *   x: 3
     *
     * list:
     *   myId: 3
     * ------------------
     * \@AsMap(key="id", simplify = "x")
     * list:
     * - id: myId
     *   x: 3
     *   y: 5
     *
     * // Not possible to simplify
     * list:
     *   myId:
     *     x: 3
     *     y: 5
     * </pre>
     * @return true if map should be simplified if possible
     */
    String simplify() default "";
}
