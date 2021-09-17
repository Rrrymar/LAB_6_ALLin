package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.model.Person;

import java.util.Comparator;
import java.util.Optional;

public class AddIfMax implements Command {
    private final CollectionHolder collectionHolder;
    private final Person person;

    public AddIfMax(CollectionHolder collectionHolder, Person person) {
        this.collectionHolder = collectionHolder;
        this.person=person;
    }

    @Override
    public String action() {
        Optional<Person> max = collectionHolder.getList().stream().max(Comparator.comparingDouble(Person::getHeight));
        if (max.isPresent()){
            Person optionalPerson = max.get();
            if (optionalPerson.getHeight() < person.getHeight()){
                person.setId(collectionHolder.getAndIncrementId());
                collectionHolder.getList().add(person);
                return "Элемент успешно добавлен";
            } else {
                return "Переданный элемент меньше максимального";
            }
        } else {
            return "Коллекция пуста";
        }
    }
}
