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
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to add simple script validators to property annotated with this validator. <br/>
 * Multiple validators can be added to single property. <br/>
 * Validators can be used only above get or set property methods. <br/>
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ScriptValidators.class)
public @interface ScriptValidator {
    /**
     * @return language to use and options, must be supported by configuration manager
     */
    ScriptLanguage language() default @ScriptLanguage;

    /**
     * Returns script used to validate property. <br/>
     * Property can be accessed by `x` variable.
     *
     * @return script used to validate property.
     */
    @Language(value = "JavaScript", prefix = "function validate(x, cfg){ if (", suffix = ") return object;throw new Exception()}")
    String isTrue();

    /**
     * Returns error message to use if validation of property fails. <br/>
     * This is also script snippet so you can use string interpolation. ${x} for Nashorn
     *
     * @return error message.
     */
    @Language(value = "JavaScript", prefix = "function validate(x, cfg){ return \"\"\"", suffix = "\"\"\";}")
    String elseThrow();
}
