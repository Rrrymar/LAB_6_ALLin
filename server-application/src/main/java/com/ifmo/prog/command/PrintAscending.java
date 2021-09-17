package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.Comparator;
import java.util.stream.Collectors;

public class PrintAscending implements Command {
    private final CollectionHolder collectionHolder;

    public PrintAscending(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String action() {
        return collectionHolder.getList().stream().sorted(Comparator.comparingDouble(Person::getHeight)).collect(Collectors.toList()).toString();
    }
}
