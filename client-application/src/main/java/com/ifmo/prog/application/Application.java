package com.ifmo.prog.application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Application {

    public static void start(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите порт");
            int port = Integer.parseInt(scanner.nextLine());
            System.out.println("Введите хост");
            String host = scanner.nextLine();
            DatagramChannel datagramChannel = DatagramChannel.open();
            datagramChannel.configureBlocking(true);
            SocketAddress socketAddress = new InetSocketAddress(host, port);
            ConnectionResolver connectionResolver = new ConnectionResolver(datagramChannel, socketAddress);
            connectionResolver.resolve(scanner);
        } catch (IOException | NumberFormatException exception) {
            System.out.println("Проверьте правильность подключения или введите данные заново");
            System.exit(0);
        }
    }
}
