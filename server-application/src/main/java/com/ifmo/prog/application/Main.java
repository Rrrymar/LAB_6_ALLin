package com.ifmo.prog.application;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Отключение сервера");
            Application.hook();
        }));
        Application.start(System.getenv("PROG_LAB_PATH"));

    }
}
