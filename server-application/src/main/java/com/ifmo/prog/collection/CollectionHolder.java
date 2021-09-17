package com.ifmo.prog.collection;

import com.ifmo.prog.model.Person;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class CollectionHolder {
    private final LocalDateTime initializationDate;
    private List<Person> list;

    private long currentId = 1L;

    public CollectionHolder(List<Person> list) {
        this.initializationDate = LocalDateTime.now();
        this.list = list;
    }

    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    public void refreshId(){
        currentId = 1L;
    }

    public long getAndIncrementId() {
       return currentId++;
    }

    public long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(long currentId) {
        this.currentId = currentId;
    }
}
