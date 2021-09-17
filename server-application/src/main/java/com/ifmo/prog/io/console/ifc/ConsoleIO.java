package com.ifmo.prog.io.console.ifc;

import java.util.Objects;
import java.util.Scanner;

public interface ConsoleIO<T>{
    T readElement(String args);
    default Double readDouble(String line){
        Double number = null;
        while (Objects.isNull(number)) {
            System.out.println("Введите число");
            try {
                number = Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Некорректные данные");
            }
        }
        return number;
    }
    default Long readLong(String line){
        Long number = null;
        while (Objects.isNull(number)) {
            System.out.println("Введите число");
            try {
                number = Long.parseLong(line);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return number;
    }
}
