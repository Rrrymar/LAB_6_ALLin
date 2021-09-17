package com.ifmo.prog.io.reader.impl;

import com.ifmo.prog.application.Application;
import com.ifmo.prog.io.reader.ifc.Reader;
import com.ifmo.prog.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonReader implements Reader<Person> {

    @Override
    public Person readElement(String line) {
        String[] args = line.split(",");
        String name = null; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates = null; //Поле не может быть null
        Date creationDate = null; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        float height = 0; //Поле может быть null, Значение поля должно быть больше 0
        Color eyeColor = null; //Поле может быть null
        Color hairColor = null; //Поле может быть null
        Country nationality = null; //Поле может быть null
        Location location; //Поле не может быть null

        try {
            name = args[0];

            coordinates = readCoordinates(args);

            try {
                creationDate = Application.getApplicationDateFormat().parse(args[3]);
            } catch (ParseException e) {
                System.out.println("Некорректные данные");
                return null;
            }
            try {
                height = Float.parseFloat(args[4]);
            } catch (NumberFormatException e) {
                System.out.println("Некорректные данные");
                return null;
            }

            switch (args[5]) {
                case "RED":
                    eyeColor = Color.RED;
                    break;
                case "ORANGE":
                    eyeColor = Color.ORANGE;
                    break;
                case "BROWN":
                    eyeColor = Color.BROWN;
                    break;
                default:
                    System.out.println("Некорректные данные");
                    return null;
            }

            switch (args[6]) {
                case "RED":
                    hairColor = Color.RED;
                    break;
                case "ORANGE":
                    hairColor = Color.ORANGE;
                    break;
                case "BROWN":
                    hairColor = Color.BROWN;
                    break;
                default:
                    System.out.println("Некорректные данные");
                    return null;
            }

            switch (args[7]) {
                case "FRANCE":
                    nationality = Country.FRANCE;
                    break;
                case "CHINA":
                    nationality = Country.CHINA;
                    break;
                case "ITALY":
                    nationality = Country.ITALY;
                    break;
                case "THAILAND":
                    nationality = Country.THAILAND;
                    break;
                case "SOUTH_KOREA":
                    nationality = Country.SOUTH_KOREA;
                    break;
                default:
                    System.out.println("Некорректные данные");
                    return null;
            }
            location = readLocation(args);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Некорректные данные");
            return null;
        }
        return new Person(-1, name, coordinates, creationDate, height, eyeColor, hairColor, nationality, location);
    }

    private Location readLocation(String[] args) {
        int locationX = 0;
        try {
            locationX = Integer.parseInt(args[8]);
        } catch (NumberFormatException e) {
            System.out.println("Некорректные данные");
            return null;
        }
        long locationY = 0;
        try {
            locationY = Integer.parseInt(args[9]);
        } catch (NumberFormatException e) {
            System.out.println("Некорректные данные");
            return null;
        }
        String locationName;
        locationName = args[10];
        return new Location(locationX, locationY, locationName);
    }

    private Coordinates readCoordinates(String[] args) {
        Coordinates coordinates;
        double coordinatesX = 0;
        try {
            coordinatesX = Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Некорректные данные");
        }
        float coordinatesY = 0;
        try {
            coordinatesY = Float.parseFloat(args[2]);
        } catch (NumberFormatException e) {
            System.out.println("Некорректные данные");
        }
        return new Coordinates(coordinatesX, coordinatesY);
    }

}
