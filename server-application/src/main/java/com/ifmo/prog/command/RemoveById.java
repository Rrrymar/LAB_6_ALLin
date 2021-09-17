package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.List;
import java.util.stream.Collectors;

public class RemoveById implements Command {
    private final CollectionHolder collectionHolder;
    private final Number number;

    public RemoveById(CollectionHolder collectionHolder, Number number) {
        this.collectionHolder = collectionHolder;
        this.number = number;
    }

    @Override
    public String action() {
        if (collectionHolder.getList().size() != 0) {
            List<Person> collect = collectionHolder.getList().stream().filter(person -> person.getId() == number.longValue()).collect(Collectors.toList());
            if (collect.size() == 1) {
                collectionHolder.getList().remove(collect.get(0));
                return "Элемент успешно удален";
            } else {
                return String.format("Элементов с id %s либо нету либо несколько", number.intValue());
            }
        } else {
            return "Коллекция пуста";
        }

    }
}
