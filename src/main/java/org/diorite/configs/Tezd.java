package org.diorite.configs;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.diorite.configs.annotations.style.Style;

import javax.script.Compilable;
import javax.script.ScriptEngine;
import java.util.Map;

public class Tezd {
    Map<String, @Style String> getM() {
        return null;
    }

    public static void main(String[] args) {
        NashornScriptEngineFactory x = new NashornScriptEngineFactory();
        ScriptEngine scriptEngine = x
            .getScriptEngine("-scripting=true", "--global-per-engine=true", "--optimistic-types=true", "--language=es6",
                "-dump-on-error=true", "--debug-locals=true", "--loader-per-compile=false", "--persistent-code-cache=true");
        Compilable compilable = (Compilable) scriptEngine;

//        System.out.println(scriptEngine.eval("var x = 4; \"this is ${x}!\""));
////        Object load = new Yaml().load("!!pairs [ meeting: with team, meeting: with boss ]");
////        System.out.println(load);

        int[] ints = new int[]{1, 2, 3, 4, 5};
//        System.out.println(Arrays.equals(ints, newInts));
    }



}
