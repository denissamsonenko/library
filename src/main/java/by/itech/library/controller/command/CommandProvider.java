package by.itech.library.controller.command;

import by.itech.library.controller.command.impl.BookForm;
import by.itech.library.controller.command.impl.CreateBook;
import by.itech.library.controller.command.impl.CreateReader;
import by.itech.library.controller.command.impl.ReaderForm;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(ParameterName.CREATE_READER, new CreateReader());
        commands.put(ParameterName.READER_FORM, new ReaderForm());
        commands.put(ParameterName.CREATE_BOOK, new CreateBook());
        commands.put(ParameterName.BOOK_FORM, new BookForm());
    }

    public Command getCommand(String commandName) {
        commandName = commandName.toUpperCase();
        ParameterName valueName = ParameterName.valueOf(commandName);
        Command command = commands.get(valueName);

        return command;
    }
}
