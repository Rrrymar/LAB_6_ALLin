package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;

public class Clear implements Command {
    private final CollectionHolder collectionHolder;

    public Clear(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String action() {
        if (collectionHolder.getList().size() != 0) {
            collectionHolder.refreshId();
            collectionHolder.getList().clear();
            return "Коллекция успешно очищена";
        } else {
            return "Коллекция пуста";
        }
    }
}
