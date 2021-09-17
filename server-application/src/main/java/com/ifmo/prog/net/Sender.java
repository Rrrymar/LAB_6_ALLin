package com.ifmo.prog.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class Sender {
    private final DatagramSocket datagramSocket;
    private final SocketAddress socketAddress;
    private final String answer;

    public Sender(DatagramSocket datagramSocket, SocketAddress socketAddress, String answer) {
        this.datagramSocket = datagramSocket;
        this.socketAddress = socketAddress;
        this.answer = answer;
    }

    public void send() {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(answer);
            byte[] sendBuf = byteArrayOutputStream.toByteArray();
            DatagramPacket datagramPacket = new DatagramPacket(sendBuf, sendBuf.length, socketAddress);
            datagramSocket.send(datagramPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
