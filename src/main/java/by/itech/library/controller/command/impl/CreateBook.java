package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateBook implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.getWriter().write("Hello");
    }
}
