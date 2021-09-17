package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.Optional;

public class Head implements Command {
    private final CollectionHolder collectionHolder;

    public Head(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String action() {
        Optional<Person> firstElement = collectionHolder.getList().stream().findFirst();
        if (firstElement.isPresent()){
            return firstElement.get().toString();
        } else {
            return "Коллекция пуста";
        }
    }
}
