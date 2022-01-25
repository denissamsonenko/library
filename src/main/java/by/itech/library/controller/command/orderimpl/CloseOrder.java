package by.itech.library.controller.command.orderimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.CopyBookImg;
import by.itech.library.model.NotesCopyBook;
import by.itech.library.model.OrderStatus;
import by.itech.library.model.Orders;
import by.itech.library.service.OrderService;
import by.itech.library.service.ServiceException;
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
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class CloseOrder implements Command {
    private static final String ID_ORDER_ATTR = "idOrder";
    private static final String FEE_ATTR = "fee";
    private static final String EXPIRE_DATE_ATTR = "expireDate";
    private static final String TOTAL_PRICE_ATTR = "totalPrice";
    private static final String NOTES_ATTR = "notes";
    private static final String ID_BOOK_ATTR = "idBook";
    private static final String FILE_COPY_ATTR = "fileCopy";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Orders orders = new Orders();
        orders.setIdOrder(Integer.parseInt(request.getParameter(ID_ORDER_ATTR)));
        orders.setFine(new BigDecimal(request.getParameter(FEE_ATTR)));
        orders.setDateExpire(LocalDate.parse(request.getParameter(EXPIRE_DATE_ATTR)));
        orders.setFinishPrice(new BigDecimal(request.getParameter(TOTAL_PRICE_ATTR)));
        orders.setOrderStatus(OrderStatus.COMPLETED);

        String[] idBooks = request.getParameterValues(ID_BOOK_ATTR);

        List<CopyBookImg> copyBookImg = getCopyBookImg(request, idBooks);
        List<NotesCopyBook> notesCopy = getNotes(request, idBooks);

        try {
            orderService.closeOrder(orders, copyBookImg, notesCopy);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private List<NotesCopyBook> getNotes(HttpServletRequest request, String[] idBooks) {
        List<NotesCopyBook> notesCopyBooks = new ArrayList<>();
        for (String parameterValue : request.getParameterValues(NOTES_ATTR)) {
            NotesCopyBook notesCopyBook = new NotesCopyBook();
            notesCopyBook.setNote(parameterValue);
            notesCopyBooks.add(notesCopyBook);
        }

        for (int i = 0; i < idBooks.length; i++) {
            notesCopyBooks.get(i).setIdCopy(Integer.parseInt(idBooks[i]));
        }
        return notesCopyBooks;
    }

    private List<CopyBookImg> getCopyBookImg(HttpServletRequest request, String[] idBooks) throws IOException, ServletException {
        List<CopyBookImg> img = new ArrayList<>();

        for (Part part : request.getParts()) {
            if (part.getName().equals(FILE_COPY_ATTR)) {
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
        for (int i = 0; i < idBooks.length; i++) {
            img.get(i).setIdCopy(Integer.parseInt(idBooks[i]));
        }
        return img;
    }
}
