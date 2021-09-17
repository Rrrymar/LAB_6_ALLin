package com.ifmo.prog.application;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.command.Command;
import com.ifmo.prog.command.Save;
import com.ifmo.prog.io.console.ifc.ConsoleIO;
import com.ifmo.prog.io.console.impl.ConsolePersonIO;
import com.ifmo.prog.io.file.ifc.FileIO;
import com.ifmo.prog.io.file.impl.CsvPersonIO;
import com.ifmo.prog.io.reader.ifc.Reader;
import com.ifmo.prog.io.reader.impl.PersonReader;
import com.ifmo.prog.model.Person;

import java.io.*;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class Application {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Reader<Person> reader = new PersonReader();
    private static final ConsoleIO<Person> consoleIO = new ConsolePersonIO(reader);
    private static final CollectionHolder collectionHolder = new CollectionHolder(new LinkedList<>());
    private static FileIO<Person> fileIO;


    public static void start(String fileName) throws IOException, ClassNotFoundException {
        System.out.println("Введите порт");
        Scanner scanner = new Scanner(System.in);
        int port = Integer.parseInt(scanner.nextLine());
        try (DatagramSocket datagramSocket = new DatagramSocket(null)) {
            SocketAddress socketAddress = new InetSocketAddress(port);
            datagramSocket.bind(socketAddress);
            InputStreamReader fileInputStream = new InputStreamReader(new FileInputStream(fileName));
            FileWriter fileOutputStream = new FileWriter(fileName, true);
            fileIO = new CsvPersonIO(fileInputStream, fileName, collectionHolder, reader);
            CommandManager commandManager = new CommandManager(collectionHolder, consoleIO, fileIO);
            ConnectionResolver<Person> connectionResolver = new ConnectionResolver(datagramSocket, fileIO, commandManager);
            connectionResolver.connect(fileName);
        }
    }

    public static void hook(){
        Command save = new Save(collectionHolder, fileIO);
        save.action();
    }


    public static SimpleDateFormat getApplicationDateFormat(){
        return simpleDateFormat;
    }
}
