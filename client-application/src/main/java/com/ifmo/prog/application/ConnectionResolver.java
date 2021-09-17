package com.ifmo.prog.application;

import com.ifmo.prog.model.Command;
import com.ifmo.prog.net.Receiver;
import com.ifmo.prog.net.Sender;

import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class ConnectionResolver {
    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    public ConnectionResolver(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public void resolve(Scanner scanner) {
        Receiver receiver = new Receiver(datagramChannel, socketAddress);
        Sender sender = new Sender(datagramChannel, socketAddress);
        while (true) {
            System.out.println("Введите команду");
            String command = scanner.nextLine();
            switch (command) {
                case "remove_greater":
                case "add_if_max":
                case "add":
                    System.out.println("Введите данные в формате\n" +
                            "name,coordiantes_x,coordinates_y,date(yyyy-mm-dd),height,eyeColor,hairColor,country,location_x,location_y,location_name");
                    System.out.println("Введите объект Person");
                    String personArgument = scanner.nextLine();
                    sender.send(new Command(command, personArgument));
                    break;
                case "execute_script":
                case "filter_greater_than_height":
                case "remove_by_id":
                    System.out.println("Введите аргумент");
                    String soloArgument = scanner.nextLine();
                    sender.send(new Command(command, soloArgument));
                    break;
                case "update":
                    System.out.println("Введите данные в формате\n" +
                            "name,coordiantes_x,coordinates_y,date(yyyy-mm-dd),height,eyeColor,hairColor,country,location_x,location_y,location_name");
                    System.out.println("Введите объект Person");
                    String personToUpdate = scanner.nextLine();
                    System.out.println("Введите id");
                    String peronId = scanner.nextLine();
                    sender.send(new Command(command, personToUpdate, peronId));
                    break;
                default:
                    sender.send(new Command(command));
            }
            System.out.println(receiver.receive());
        }
    }
}
