package by.itech.library.controller.command;

import by.itech.library.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(ParameterName.CREATE_READER, new CreateReader());
        commands.put(ParameterName.READER_ADD_PAGE, new AddReaderPage());
        commands.put(ParameterName.CREATE_BOOK, new CreateBook());
        commands.put(ParameterName.BOOK_ADD_PAGE, new AddBookPage());
        commands.put(ParameterName.BOOK_LIST_PAGE, new ListBookPage());
        commands.put(ParameterName.SEND_GENRES, new SendGenres());
        commands.put(ParameterName.SEND_EMAIL, new SendEmail());
        commands.put(ParameterName.LIST_BOOK, new ListBook());
    }

    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        ParameterName valueName = ParameterName.valueOf(commandName);
        Command command = commands.get(valueName);

        return command;
    }
}
