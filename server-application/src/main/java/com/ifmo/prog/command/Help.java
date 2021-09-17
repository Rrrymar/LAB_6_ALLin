package com.ifmo.prog.command;

public class Help implements Command  {
    private final String availableCommands = "\n" +
            "help : вывести справку по доступным командам\n" +
            "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
            "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
            "add {element} : добавить новый элемент в коллекцию\n" +
            "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
            "remove_by_id id : удалить элемент из коллекции по его id\n" +
            "clear : очистить коллекцию\n" +
            "save : сохранить коллекцию в файл\n" +
            "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
            "exit : завершить программу (без сохранения в файл)\n" +
            "head : вывести первый элемент коллекции\n" +
            "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
            "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
            "filter_greater_than_height height : вывести элементы, значение поля height которых больше заданного\n" +
            "print_ascending : вывести элементы коллекции в порядке возрастания\n" +
            "print_descending : вывести элементы коллекции в порядке убывания";
    @Override
    public String action() {
        return availableCommands;
    }
}
