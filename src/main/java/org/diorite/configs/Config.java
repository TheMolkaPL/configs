package org.diorite.configs;

public interface Config<T extends Config<T>> {
    T defaults();

    T config();
}
