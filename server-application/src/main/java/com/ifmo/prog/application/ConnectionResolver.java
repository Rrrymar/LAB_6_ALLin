package com.ifmo.prog.application;

import com.ifmo.prog.io.file.ifc.FileIO;
import com.ifmo.prog.model.Command;
import com.ifmo.prog.model.Person;
import com.ifmo.prog.net.Protocol;
import com.ifmo.prog.net.Receiver;
import com.ifmo.prog.net.Sender;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

public class ConnectionResolver<T> {
    private final DatagramSocket datagramSocket;
    private SocketAddress socketAddress;
    private final FileIO<T> csvPersonIO;
    private Receiver receiver;
    private Sender sender;
    private final CommandManager commandManager;

    public ConnectionResolver(DatagramSocket datagramSocket, FileIO<T> csvPersonIO, CommandManager commandManager) {
        this.datagramSocket = datagramSocket;
        this.csvPersonIO = csvPersonIO;
        this.commandManager = commandManager;
    }

    public void connect(String file) throws IOException, ClassNotFoundException {
        System.out.println("Сервер начал работу" +
                "\nЕсли захотите сохранить коллекцию нажмите ctrl + c");
        List<T> csvCollection = csvPersonIO.read();
        System.out.printf("Загружена коллекция размером %s элементов%n", csvCollection.size());
        while (true) {
            receiver = new Receiver(datagramSocket, new Protocol());
            Command userCommand = receiver.receive();
            String answer = commandManager.resolveCommand(userCommand);
            socketAddress = new InetSocketAddress(receiver.getProtocol().getInetAddress(), receiver.getProtocol().getPort());
            sender = new Sender(datagramSocket, socketAddress, answer);
            sender.send();
        }
    }
}
