package by.itech.library.dao.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.OrderDao;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.CopyBook;
import by.itech.library.model.Order;
import by.itech.library.model.OrderStatus;
import by.itech.library.model.Status;

import java.sql.*;

public class OrderDaoImpl implements OrderDao {
    private final PoolConnection pool = PoolConnection.getInstance();

    private final static String CREATE_ORDER = "INSERT INTO orders (id_reader, issue_date, return_date, advance_price, discount, status)" +
            "VALUES(?,?,?,?,?,?)";

    private final static String CREATE_ORDER_COPY = "INSERT INTO order_copy (id_copy, id_order) VALUES (?,?)";

    private final static String CHANGE_COPY_STATUS = "UPDATE book_copy SET status=? WHERE id_copy=?";

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
}
