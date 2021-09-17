package com.ifmo.prog.net;

import java.net.InetAddress;

public class Protocol {
    private InetAddress inetAddress;
    private int port;

    public Protocol() {
    }

    public Protocol(InetAddress inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public int getPort() {
        return port;
    }
}
