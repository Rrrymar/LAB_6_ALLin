package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class FilterGreaterThanHeight implements Command  {
    private final CollectionHolder collectionHolder;
    private final Double number;

    public FilterGreaterThanHeight(CollectionHolder collectionHolder, Double number) {
        this.collectionHolder = collectionHolder;
        this.number=number;
    }

    @Override
    public String action() {
        List<Person> collect = collectionHolder.getList().stream().filter(person -> person.getHeight() > number).collect(Collectors.toList());
        return collect.toString();
    }
}
