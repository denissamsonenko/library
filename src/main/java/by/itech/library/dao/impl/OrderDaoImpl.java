package by.itech.library.dao.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.OrderDao;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.CopyBook;
import by.itech.library.model.dto.Order;
import by.itech.library.model.OrderStatus;
import by.itech.library.model.Status;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {
    private final PoolConnection pool = PoolConnection.getInstance();

    private final static String CREATE_ORDER = "INSERT INTO orders (id_reader, issue_date, return_date, advance_price, discount, status)" +
            "VALUES(?,?,?,?,?,?)";

    private final static String CREATE_ORDER_COPY = "INSERT INTO order_copy (id_copy, id_order) VALUES (?,?)";

    private final static String CHANGE_COPY_STATUS = "UPDATE book_copy SET status=? WHERE id_copy=?";

    private final static String GET_ORDER = "SELECT o.id_order, o.id_reader, oc.id_copy, b.name_ru, r.name, r.surname, r.email, o.issue_date, o.return_date, o.advance_price " +
            "FROM orders as o LEFT JOIN readers AS r ON (o.id_reader=r.id_reader) " +
            "JOIN order_copy AS oc ON (o.id_order= oc.id_order) " +
            "JOIN book_copy AS bc ON (oc.id_copy = bc.id_copy) " +
            "LEFT JOIN books AS b ON (bc.id_book=b.id_book) " +
            "WHERE o.status IN ('ACTIVE', 'OVERDUE') AND r.surname LIKE initcap(?)||'%'";

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
    public Order getOrder() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Order order = new Order();
        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(GET_ORDER);
            rs = ps.executeQuery();

            while(rs.next()){

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return null;
    }
}
