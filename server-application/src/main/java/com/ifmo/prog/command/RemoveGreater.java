package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

public class RemoveGreater implements Command {
    private final CollectionHolder collectionHolder;
    private final Person person;

    public RemoveGreater(CollectionHolder collectionHolder, Person person) {
        this.collectionHolder = collectionHolder;
        this.person=person;
    }

    @Override
    public String action() {
        if (collectionHolder.getList().size() != 0) {
            collectionHolder.getList().stream().filter(person1 -> person1.getHeight() > person.getHeight()).forEach(p -> collectionHolder.getList().remove(p));
            return "Элементы успешно удалены / Таких элементов нет";
        } else {
            return "Коллекция пуста";
        }
    }
}
