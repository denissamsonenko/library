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
    private static final String MIDDLE_NAME_ATTR = "middleName";
    private static final String ADDRESS_ATTR = "address";
    private static final String PASSPORT_ATTR = "passport";
    private static final String EMPTY_STRING = "";
    private static final String BIRTH_DATE_ATTR = "birthDate";
    private static final String NAME_ATTR = "name";
    private static final String SURNAME_ATTR = "surname";
    private static final String EMAIL_ATTR = "email";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        Reader reader = new Reader();
        if (!request.getParameter(MIDDLE_NAME_ATTR).equals(EMPTY_STRING)) {
            reader.setMiddleName(request.getParameter(MIDDLE_NAME_ATTR));
        }
        if (!request.getParameter(ADDRESS_ATTR).equals(EMPTY_STRING)) {
            reader.setAddress(request.getParameter(ADDRESS_ATTR));
        }
        if (!request.getParameter(PASSPORT_ATTR).equals(EMPTY_STRING)) {
            reader.setPassport(request.getParameter(PASSPORT_ATTR));
        }
        reader.setBirthDate(LocalDate.parse(request.getParameter(BIRTH_DATE_ATTR)));
        reader.setName(request.getParameter(NAME_ATTR));
        reader.setSurname(request.getParameter(SURNAME_ATTR));
        reader.setEmail(request.getParameter(EMAIL_ATTR));

        try {
            readerService.createReader(reader);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
