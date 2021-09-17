package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.Objects;

public class Update implements Command {
    private final CollectionHolder collectionHolder;
    private final Person person;
    private final Number id;

    public Update(CollectionHolder collectionHolder, Person person, Number id) {
        this.collectionHolder = collectionHolder;
        this.person=person;
        this.id=id;
    }

    @Override
    public String action() {
        int index = id.intValue();
        Person existPerson = collectionHolder.getList().get(index);
        if (Objects.nonNull(existPerson)){
            person.setId(index);
            collectionHolder.getList().remove(index);
            collectionHolder.getList().add(person);
        }
        return null;
    }
}
