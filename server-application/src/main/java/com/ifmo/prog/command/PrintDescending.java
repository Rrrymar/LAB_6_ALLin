package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintDescending implements Command {
    private final CollectionHolder collectionHolder;

    public PrintDescending(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String action() {
        List<Person> ascendingCollect = collectionHolder.getList().stream().sorted(Comparator.comparingDouble(Person::getHeight)).collect(Collectors.toList());
        Collections.reverse(ascendingCollect);
        return ascendingCollect.toString();
    }
}
