package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.Reader;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class CreateReader implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        Reader reader = new Reader();
        reader.setName(request.getParameter("name"));
        reader.setSurname(request.getParameter("surname"));
        reader.setMiddleName(request.getParameter("middleName"));
//        reader.setCity(request.getParameter("city"));
        reader.setEmail(request.getParameter("email"));
        reader.setAddress(request.getParameter("address"));
        reader.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        reader.setPassport(request.getParameter("passport"));

        try {
            readerService.createReader(reader);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

//        response.sendRedirect("lib?command=create_reader");
    }
}
