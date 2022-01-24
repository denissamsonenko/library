package by.itech.library.controller.command.orderimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.CopyBookImg;
import by.itech.library.model.NotesCopyBook;
import by.itech.library.model.OrderStatus;
import by.itech.library.model.Orders;
import by.itech.library.service.OrderService;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CloseOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Orders orders = new Orders();
        orders.setFine(new BigDecimal(request.getParameter("")));
        orders.setDateExpire(LocalDate.parse(request.getParameter("")));
        orders.setFine(new BigDecimal(request.getParameter("")));
        orders.setFinishPrice(new BigDecimal(request.getParameter("")));
        orders.setOrderStatus(OrderStatus.COMPLETED);

        List<CopyBookImg> copyBookImg = getCopyBookImg(request);

        List<NotesCopyBook> notesCopy = getNotes(request);

        NotesCopyBook notes = new NotesCopyBook();
        notes.setNote(request.getParameter(""));
        notesCopy.add(notes);

    }

    private List<NotesCopyBook> getNotes(HttpServletRequest request) {
        List<NotesCopyBook> notesCopyBooks = new ArrayList<>();
        for (String parameterValue : request.getParameterValues("")) {
            NotesCopyBook notesCopyBook = new NotesCopyBook();
            notesCopyBook.setNote(parameterValue);
            notesCopyBooks.add(notesCopyBook);
        }
        return notesCopyBooks;
    }

    private List<CopyBookImg> getCopyBookImg(HttpServletRequest request) throws IOException, ServletException {
        List<CopyBookImg> img = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals("")) {
                if (!part.getSubmittedFileName().isEmpty()) {
                    CopyBookImg copyBookImg = new CopyBookImg();
                    String name = UUID.randomUUID().toString() + "." + part.getSubmittedFileName().trim().replace(" ", "");
                    copyBookImg.setName(name);
                    InputStream inputStream = part.getInputStream();
                    copyBookImg.setImg(inputStream);
                    img.add(copyBookImg);
                } else {
                    CopyBookImg copyBookImg = new CopyBookImg();
                    copyBookImg.setName(part.getSubmittedFileName());
                    img.add(copyBookImg);
                }
            }
        }
        return img;
    }
}
