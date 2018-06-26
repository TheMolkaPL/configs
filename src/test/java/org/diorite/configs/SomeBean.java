package org.diorite.configs;

import java.util.List;

public class SomeBean {
    private final int beanValueInt;
    private final List<String> beanValueStringList;

    public SomeBean(int beanValueInt, List<String> beanValueStringList) {
        this.beanValueInt = beanValueInt;
        this.beanValueStringList = List.copyOf(beanValueStringList);
    }

    public int getBeanValueInt() {
        return this.beanValueInt;
    }

    public List<String> getBeanValueStringList() {
        return this.beanValueStringList;
    }
}
