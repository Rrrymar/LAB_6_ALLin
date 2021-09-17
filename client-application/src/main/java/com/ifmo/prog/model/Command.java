package com.ifmo.prog.model;

import java.io.Serializable;

public class Command implements Serializable {
    private static final long serialVersionUID = 10L;
    private String userCommand;
    private String argument;
    private String id;

    public Command(String userCommand) {
        this.userCommand = userCommand;
    }

    public Command(String userCommand, String argument) {
        this.userCommand = userCommand;
        this.argument = argument;
    }

    public Command(String userCommand, String argument, String id) {
        this.userCommand = userCommand;
        this.argument = argument;
        this.id = id;
    }
    public String getUserCommand() {
        return userCommand;
    }

    public void setUserCommand(String userCommand) {
        this.userCommand = userCommand;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
