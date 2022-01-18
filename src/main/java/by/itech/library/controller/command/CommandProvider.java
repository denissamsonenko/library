package by.itech.library.controller.command;

import by.itech.library.controller.command.bookimpl.*;
import by.itech.library.controller.command.readerimpl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(ParameterName.CREATE_READER, new CreateReader());
        commands.put(ParameterName.CREATE_BOOK, new CreateBook());
        commands.put(ParameterName.READER_ADD_PAGE, new AddReaderPage());
        commands.put(ParameterName.BOOK_ADD_PAGE, new AddBookPage());
        commands.put(ParameterName.BOOK_LIST_PAGE, new ListBookPage());
        commands.put(ParameterName.READER_LIST_PAGE, new ReaderListPage());
        commands.put(ParameterName.SEND_GENRES, new SendGenres());
        commands.put(ParameterName.SEND_EMAIL, new SendEmail());
        commands.put(ParameterName.LIST_BOOK, new SendListBook());
        commands.put(ParameterName.SEND_LIST_READER, new SendListReader());
        commands.put(ParameterName.SEND_COUNT_READER, new SendCountReader());
        commands.put(ParameterName.SEND_BOOK_COUNT, new SendBookCount());
    }

    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        ParameterName valueName = ParameterName.valueOf(commandName);
        Command command = commands.get(valueName);

        return command;
    }
}
