package com.ifmo.prog.io.console.impl;

import com.ifmo.prog.io.console.ifc.ConsoleIO;
import com.ifmo.prog.io.reader.ifc.Reader;
import com.ifmo.prog.io.reader.impl.PersonReader;
import com.ifmo.prog.model.Person;

import java.util.Objects;
import java.util.Scanner;

public class ConsolePersonIO implements ConsoleIO<Person> {
    private final Reader<Person> personReader;

    public ConsolePersonIO(Reader<Person> personReader) {
        this.personReader = personReader;
    }

    @Override
    public Person readElement(String personCsvData) {
        System.out.println("Введите данные в формате\n" +
                "name,coordiantes_x,coordinates_y,date(yyyy-mm-dd),height,eyeColor,hairColor,country,location_x,location_y,location_name");
        return personReader.readElement(personCsvData);
    }
}
