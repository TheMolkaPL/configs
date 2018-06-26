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

package org.diorite.configs.annotations.style;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to apply style to numbers, possible values: <br/>
 * Note that some values are not yaml-compatible, so they might not load to valid value/type when using other api<br/>
 * <table>
 * <tr> <th>value</th>          <th>example</th>        <th>result</th>         <th>compatible</th> <th>description</th> </tr>
 * <tr> <td>0x0</td>            <td>1</td>              <td>0x1</td>            <td>yes</td>        <td>hex</td>         </tr>
 * <tr> <td>0x0000</td>         <td>26</td>             <td>0x001A</td>         <td>yes</td>        <td>hex</td>         </tr>
 * <tr> <td>0o</td>             <td>8</td>              <td>010</td>            <td>yes</td>        <td>octal</td>       </tr>
 * <tr> <td>0o000</td>          <td>8</td>              <td>0010</td>           <td>yes</td>        <td>octal</td>       </tr>
 * <tr> <td>0b0000</td>         <td>5</td>              <td>0b0101</td>         <td>yes</td>        <td>binary</td>      </tr>
 * <tr> <td>0.0</td>            <td>5</td>              <td>5.0</td>            <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>00.00</td>          <td>5</td>              <td>05.00</td>          <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>.</td>              <td>123.456</td>        <td>123.456</td>        <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>00.00</td>          <td>123.456</td>        <td>123.47</td>         <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>00.00#</td>         <td>123.4567</td>       <td>123.457</td>        <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>00.00#</td>         <td>123.45</td>         <td>123.45</td>         <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>H:M:S</td>          <td>5400</td>           <td>1:30:00</td>        <td>yes</td>        <td>sexagesimal</td> </tr>
 * <tr> <td>H:M</td>            <td>90</td>             <td>1:30:00</td>        <td>yes</td>        <td>sexagesimal</td> </tr>
 * <tr> <td>+0</td>             <td>5</td>              <td>+5</td>             <td>yes</td>        <td>integer</td>     </tr>
 * <tr> <td>+0</td>             <td>-5</td>             <td>-5</td>             <td>yes</td>        <td>integer</td>     </tr>
 * <tr> <td>+0,</td>            <td>1500</td>           <td>+1,500</td>         <td>yes</td>        <td>integer</td>     </tr>
 * <tr> <td>0,</td>             <td>1500</td>           <td>1,500</td>          <td>yes</td>        <td>integer</td>     </tr>
 * <tr> <td>0,</td>             <td>9000500.5</td>      <td>9,000,500.5</td>    <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>0,.00</td>          <td>9500.5</td>         <td>9,500.50</td>       <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>+0,.00</td>         <td>9500.5</td>         <td>+9,500.50</td>      <td>yes</td>        <td>float</td>       </tr>
 * <tr> <td>00</td>             <td>5</td>              <td>"05"</td>           <td>type loss</td>  <td>integer</td>     </tr>
 * </table>
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberStyle {
    String value() default "";

    /**
     * Depth of children nodes that should by affected by this settings - like higher values can be used to apply this annotation for
     * nested nodes inside map/list.
     */
    int depth() default 1;
}
