package com.ifmo.prog.io.file.ifc;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileIO<T> {
    List<T> read() throws IOException;
    void write(List<T> list) throws IOException;
}
