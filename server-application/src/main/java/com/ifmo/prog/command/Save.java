package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.io.file.ifc.FileIO;
import com.ifmo.prog.model.Person;

import java.io.IOException;

public class Save implements Command {
    private final CollectionHolder collectionHolder;
    private final FileIO<Person> fileIO;

    public Save(CollectionHolder collectionHolder, FileIO<Person> fileIO) {
        this.collectionHolder = collectionHolder;
        this.fileIO = fileIO;
    }

    @Override
    public String action() {
        try {
            fileIO.write(collectionHolder.getList());
            return "Коллекция успешно записана";
        } catch (IOException e) {
            return "Ошибка записи в файл";
        }
    }
}
