package com.ifmo.prog.io.reader.ifc;

public interface Reader<T> {
    T readElement(String line);
}
