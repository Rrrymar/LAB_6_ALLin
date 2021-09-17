package com.ifmo.prog.net;

import com.ifmo.prog.model.Command;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {
    private final byte[] receiveBuffer = new byte[4096];
    private final DatagramSocket datagramSocket;
    private Protocol protocol;

    public Receiver(DatagramSocket datagramSocket, Protocol protocol) {
        this.datagramSocket = datagramSocket;
    }

    public Command receive() throws IOException, ClassNotFoundException {
        DatagramPacket datagramPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        datagramSocket.receive(datagramPacket);
        protocol = new Protocol(datagramPacket.getAddress(), datagramPacket.getPort());
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(datagramPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Command result = (Command) objectInputStream.readObject();
        byteArrayInputStream.reset();
        byteArrayInputStream.close();
        objectInputStream.close();
        return result;
    }

    public Protocol getProtocol() {
        return protocol;
    }
}
