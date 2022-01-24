package by.itech.library.dao.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.OrderDao;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.*;
import by.itech.library.model.dto.BookCopyDto;
import by.itech.library.model.dto.Order;
import by.itech.library.model.dto.OrderDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private final PoolConnection pool = PoolConnection.getInstance();

    private final static String CREATE_ORDER = "INSERT INTO orders (id_reader, issue_date, return_date, advance_price, discount, status)" +
            "VALUES(?,?,?,?,?,?)";

    private final static String CREATE_ORDER_COPY = "INSERT INTO order_copy (id_copy, id_order) VALUES (?,?)";

    private final static String CHANGE_COPY_STATUS = "UPDATE book_copy SET status=? WHERE id_copy=?";

    private final static String GET_READER_FOR_ORDER = "SELECT r.id_reader, r.name, r.surname, r.middle_name, r.passport, r.birth_date, r.email, r.address " +
            "FROM readers AS r LEFT JOIN orders AS o on (r.id_reader = o.id_reader) WHERE r.email=UPPER(?) AND status IN (?, ?)";

    private final static String GET_ORDER = "SELECT id_order, issue_date, return_date, advance_price, discount FROM orders WHERE id_reader=?";

    private final static String GET_COPY_BOOK = "SELECT oc.id_copy, b.name_ru, b.price_per_day " +
            "FROM order_copy oc JOIN book_copy AS bc on (oc.id_copy=bc.id_copy) " +
            "JOIN books as b on (bc.id_book=b.id_book) where oc.id_order=?";

    private final static String UPDATE_ORDER = "UPDATE orders SET expire_date=?, finish_price=?, fine=?, status=? WHERE id_order=?";

    private final static String SAVE_NOTES = "INSERT INTO notes (note, id_copy) VALUES (?, ?)";

    private final static String SAVE_COPY_IMG = "INSERT INTO copy_img (name, id_copy, img) VALUES (?,?,?)";

    private final static String UPDATE_BOOK_COPY_STATUS = "UPDATE book_copy SET status=? WHERE id_copy=?";


    @Override
    public void saveOrder(Order order) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(CREATE_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, order.getReader().getReaderId());
            ps.setDate(2, Date.valueOf(order.getDateIssue()));
            ps.setDate(3, Date.valueOf(order.getDateReturn()));
            ps.setBigDecimal(4, order.getAdvancePrice());
            ps.setInt(5, order.getDiscount());
            ps.setString(6, String.valueOf(OrderStatus.ACTIVE));
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);

            ps = con.prepareStatement(CREATE_ORDER_COPY);
            for (CopyBook copyBook : order.getCopyBooks()) {
                ps.setInt(1, copyBook.getId());
                ps.setInt(2, orderId);
                ps.executeUpdate();
            }

            ps = con.prepareStatement(CHANGE_COPY_STATUS);
            for (CopyBook copyBook : order.getCopyBooks()) {
                ps.setString(1, String.valueOf(Status.BOOKED));
                ps.setInt(2, copyBook.getId());
                ps.executeUpdate();
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            pool.rollback(con);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
    }

    @Override
    public OrderDto getOrder(String email) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        OrderDto orderDto = new OrderDto();
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(GET_READER_FOR_ORDER);

            ps.setString(1, email);
            ps.setString(2, String.valueOf(OrderStatus.ACTIVE));
            ps.setString(3, String.valueOf(OrderStatus.OVERDUE));
            rs = ps.executeQuery();

            Reader reader = new Reader();
            while (rs.next()) {
                reader.setReaderId(rs.getInt(1));
                reader.setName(rs.getString(2));
                reader.setSurname(rs.getString(3));
                reader.setMiddleName(rs.getString(4));
                reader.setPassport(rs.getString(5));
                reader.setBirthDate(rs.getDate(6).toLocalDate());
                reader.setEmail(rs.getString(7));
                reader.setAddress(rs.getString(8));
            }
            orderDto.setReader(reader);

            ps = con.prepareStatement(GET_ORDER);
            ps.setInt(1, reader.getReaderId());
            rs = ps.executeQuery();

            Orders order = new Orders();
            while (rs.next()) {
                order.setIdOrder(rs.getInt(1));
                order.setDateIssue(rs.getDate(2).toLocalDate());
                order.setDateReturn(rs.getDate(3).toLocalDate());
                order.setAdvancePrice(rs.getBigDecimal(4));
                order.setDiscount(rs.getInt(5));
            }
            orderDto.setOrders(order);

            ps = con.prepareStatement(GET_COPY_BOOK);
            ps.setInt(1, order.getIdOrder());
            rs = ps.executeQuery();

            List<BookCopyDto> bookCopyDtoList = new ArrayList<>();
            while (rs.next()) {
                BookCopyDto bookCopyDto = new BookCopyDto();
                CopyBook copyBook = new CopyBook();
                copyBook.setId(rs.getInt(1));
                bookCopyDto.setCopyBooks(copyBook);
                bookCopyDto.setNameRus(rs.getString(2));
                bookCopyDto.setPricePerDay(rs.getBigDecimal(3));
                bookCopyDtoList.add(bookCopyDto);
            }
            orderDto.setBookCopyDto(bookCopyDtoList);

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            pool.rollback(con);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }

        return orderDto;
    }

    @Override
    public void closeOrder(Orders orders, List<CopyBookImg> copyBookImg, List<NotesCopyBook> notesCopy) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement("");


        } catch (SQLException e) {
            pool.rollback(con);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps, rs);
        }
    }
}
