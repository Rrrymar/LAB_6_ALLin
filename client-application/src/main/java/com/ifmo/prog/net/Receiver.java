package com.ifmo.prog.net;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Receiver {
    private final DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private final byte[] packet = new byte[4096];

    public Receiver(DatagramChannel datagramChannel, SocketAddress socketAddress) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
    }

    public String receive() {
        String answer = "";
        ByteBuffer byteBuffer = ByteBuffer.wrap(packet);
        byteBuffer.clear();
        try {
            do {
                socketAddress = datagramChannel.receive(byteBuffer);
            } while (socketAddress == null);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packet);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            answer = (String) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Подключитесь к серверу");
        }
        return answer;
    }
}
