package com.ifmo.prog.command;

import com.ifmo.prog.collection.CollectionHolder;

public class Show implements Command {
    private final CollectionHolder collectionHolder;

    public Show(CollectionHolder collectionHolder) {
        this.collectionHolder = collectionHolder;
    }

    @Override
    public String action() {
        return collectionHolder.getList().toString();
    }
}
