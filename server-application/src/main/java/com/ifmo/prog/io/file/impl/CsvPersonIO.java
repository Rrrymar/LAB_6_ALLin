package com.ifmo.prog.io.file.impl;

import com.ifmo.prog.application.Application;
import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.io.file.ifc.FileIO;
import com.ifmo.prog.io.reader.ifc.Reader;
import com.ifmo.prog.model.*;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class CsvPersonIO implements FileIO<Person> {
    private final InputStreamReader csvReader;
    private BufferedWriter csvWriter;
    private final String fileName;
    private final CollectionHolder collectionHolder;
    private final Reader<Person> personReader;

    public CsvPersonIO(InputStreamReader csvReader, String fileName, CollectionHolder collectionHolder, Reader<Person> personReader) {
        this.csvReader = csvReader;
        this.fileName = fileName;
        this.collectionHolder = collectionHolder;
        this.personReader = personReader;
    }


    @Override
    public List<Person> read() throws IOException {
        BufferedReader csvBuf = new BufferedReader(csvReader);
        String line = csvBuf.readLine();
        while (line != null) {
            writePersonInCollection(line);
            line = csvBuf.readLine();
        }
        return collectionHolder.getList();
    }

    private void writePersonInCollection(String line) {
        int firstCommaIndex = line.indexOf(",");
        String lineWithoutId = line.substring(firstCommaIndex + 1);
        Person person = personReader.readElement(lineWithoutId);
        if (Objects.nonNull(person)) {
            try {
                long id = Long.parseLong(line.substring(0, firstCommaIndex));
                person.setId(id);
                collectionHolder.getList().add(person);
                collectionHolder.setCurrentId(person.getId());
                collectionHolder.getAndIncrementId();
            } catch (NumberFormatException e) {
                System.out.println("неверные данные в файле");
            }
        }
    }


    @Override
    public void write(List<Person> list) throws IOException {
        csvWriter = new BufferedWriter(new FileWriter(fileName, false));
        for (Person element :
                list) {
            csvWriter.write(String.join(",",
                    String.valueOf(element.getId()),
                    element.getName(),
                    String.valueOf(element.getCoordinates().getX()),
                    String.valueOf(element.getCoordinates().getY()),
                    Application.getApplicationDateFormat().format(element.getCreationDate()),
                    String.valueOf(element.getHeight()),
                    element.getEyeColor().name(),
                    element.getHairColor().name(),
                    element.getNationality().name(),
                    String.valueOf(element.getLocation().getX()),
                    String.valueOf(element.getLocation().getY()),
                    element.getLocation().getName()));
            csvWriter.newLine();
        }
        csvWriter.close();
    }
}
