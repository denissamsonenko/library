package by.itech.library.controller.command.readerimpl;

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
        if (!request.getParameter("middleName").equals("")) {
            reader.setMiddleName(request.getParameter("middleName"));
        }
        if (!request.getParameter("address").equals("")) {
            reader.setAddress(request.getParameter("address"));
        }
        if (!request.getParameter("passport").equals("")) {
            reader.setPassport(request.getParameter("passport"));
        }
        reader.setBirthDate(LocalDate.parse(request.getParameter("birthDate")));
        reader.setName(request.getParameter("name"));
        reader.setSurname(request.getParameter("surname"));
        reader.setEmail(request.getParameter("email"));

        try {
            readerService.createReader(reader);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        // TODO: should be redirect? Because after creation remain data?
    }
}