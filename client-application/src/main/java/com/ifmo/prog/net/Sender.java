package com.ifmo.prog.net;

import com.ifmo.prog.model.Command;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Sender {
    private final DatagramChannel datagramChannel;
    private final SocketAddress socketAddress;

    public Sender(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public void send(Command command) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)){
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            byte[] buffer = byteArrayOutputStream.toByteArray();
            ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
            byteBuffer.clear();
            datagramChannel.send(byteBuffer, socketAddress);
        } catch (IOException e) {
            System.out.println("Подключитесь к серверу");
        }
    }
}
