package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;

import java.time.LocalDateTime;
import java.util.List;

public class Info implements Command {
    private final CollectionHolder collectionHolder;

    public Info(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String action() {
        int size = collectionHolder.getList().size();
        Class<? extends List> type = collectionHolder.getList().getClass();
        LocalDateTime initializationDate = collectionHolder.getInitializationDate();
        return "Тип коллекции - " + type + "\n" +
                "Размер - " + size + "\n" +
                "Дата инициализации - " + initializationDate;
    }
}
