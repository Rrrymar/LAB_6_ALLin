package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

public class Add implements Command {
    private final CollectionHolder collectionHolder;
    private final Person person;

    public Add(CollectionHolder collectionHolder, Person person) {
        this.collectionHolder = collectionHolder;
        this.person=person;
    }

    @Override
    public String action() {
        person.setId(collectionHolder.getAndIncrementId());
        collectionHolder.getList().add(person);
        return "Элемент успешно добавлен";
    }
}
