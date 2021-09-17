package com.ifmo.prog.application;

import com.ifmo.prog.collection.CollectionHolder;
import com.ifmo.prog.command.*;
import com.ifmo.prog.io.console.ifc.ConsoleIO;
import com.ifmo.prog.io.file.ifc.FileIO;
import com.ifmo.prog.model.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class CommandManager {
    private final CollectionHolder collectionHolder;
    private final ConsoleIO<Person> consoleIO;
    private final FileIO<Person> fileIO;
    private byte scriptCounter = 0;

    public CommandManager(CollectionHolder collectionHolder, ConsoleIO<Person> consoleIO, FileIO<Person> fileIO) {
        this.collectionHolder = collectionHolder;
        this.consoleIO = consoleIO;
        this.fileIO = fileIO;
    }

    public String resolveCommand(com.ifmo.prog.model.Command receivedCommand) {
        Command command;
        switch (receivedCommand.getUserCommand()) {
            case "add":
                Person addPerson = consoleIO.readElement(receivedCommand.getArgument());
                if (Objects.nonNull(addPerson)) {
                    command = new Add(collectionHolder, addPerson);
                    return command.action();
                } else {
                    return "ВВедите данные по указанному формату";
                }
            case "add_if_max":
                Person addIfMaxPerson = consoleIO.readElement(receivedCommand.getArgument());
                if (Objects.nonNull(addIfMaxPerson)) {
                    command = new AddIfMax(collectionHolder, addIfMaxPerson);
                    return command.action();
                } else {
                    return "ВВедите данные по указанному формату";
                }
            case "clear":
                command = new Clear(collectionHolder);
                return command.action();
            case "execute_script":
                if (scriptCounter < 4) {
                    scriptCounter++;
                    return executeScript(receivedCommand.getArgument());
                } else {
                    scriptCounter = 0;
                    return "Не надо так много раз рекурсивно вызывать execute_script";
                }
            case "filter_greater_than_height":
                command = new FilterGreaterThanHeight(collectionHolder, consoleIO.readDouble(receivedCommand.getArgument()));
                return command.action();
            case "head":
                command = new Head(collectionHolder);
                return command.action();
            case "help":
                command = new Help();
                return command.action();
            case "info":
                command = new Info(collectionHolder);
                return command.action();
            case "print_ascending":
                command = new PrintAscending(collectionHolder);
                return command.action();
            case "print_descending":
                command = new PrintDescending(collectionHolder);
                return command.action();
            case "remove_by_id":
                command = new RemoveById(collectionHolder, consoleIO.readLong(receivedCommand.getArgument()));
                return command.action();
            case "remove_greater":
                Person removeGreaterPerson = consoleIO.readElement(receivedCommand.getArgument());
                if (Objects.nonNull(removeGreaterPerson)) {
                    command = new RemoveGreater(collectionHolder, removeGreaterPerson);
                    return command.action();
                } else {
                    return "ВВедите данные по указанному формату";
                }
            case "save":
                command = new Save(collectionHolder, fileIO);
                return command.action();
            case "show":
                command = new Show(collectionHolder);
                return command.action();
            case "update":
                Person updatePerson = consoleIO.readElement(receivedCommand.getArgument());
                Long updateID = consoleIO.readLong(receivedCommand.getId());
                if (Objects.nonNull(updateID) && Objects.nonNull(updatePerson)) {
                    command = new Update(collectionHolder, updatePerson, updateID);
                    return command.action();
                } else {
                    return "ВВедите данные по указанному формату";
                }
            default:
                return "Такой команды нет";
        }
    }

    private String executeScript(String fileName) {
        Scanner fileScanner;
        StringBuilder answer = new StringBuilder();
        try {
            fileScanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            return "Файл не найден";
        }
        while (fileScanner.hasNext()) {
            String[] arguments = fileScanner.nextLine().split(" ");
            switch (arguments.length) {
                case 1:
                    answer.append(resolveCommand(new com.ifmo.prog.model.Command(arguments[0])));
                    break;
                case 2:
                    answer.append(resolveCommand(new com.ifmo.prog.model.Command(arguments[0], arguments[1])));
                    break;
                case 3:
                    answer.append(resolveCommand(new com.ifmo.prog.model.Command(arguments[0], arguments[1], arguments[2])));
                    break;
                default:
                    answer.append("Такой команды нет");
            }
            answer.append("\n");
        }
        fileScanner.close();
        return answer.toString();
    }
}
