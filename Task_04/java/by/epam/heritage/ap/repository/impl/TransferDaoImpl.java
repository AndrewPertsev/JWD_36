package by.epam.heritage.ap.repository.impl;

import by.epam.heritage.ap.model.Transfer;
import by.epam.heritage.ap.repository.DAOException;
import by.epam.heritage.ap.repository.TransferDao;
import by.epam.heritage.ap.repository.connection.ConnectionPool;
import by.epam.heritage.ap.repository.connection.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TransferDaoImpl implements TransferDao {
    ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final Logger logger = LogManager.getLogger(TransferDaoImpl.class);

    private static final String FIND_TRANSFER_BY_ID = "SELECT transfer_id, transfer, price_1_km  FROM transfer WHERE transfer_id = ?";


    public TransferDaoImpl()  {
    }


    @Override
    public Transfer findByid(int id) throws DAOException {
        String idString = String.valueOf(id);
        Transfer transfer = new Transfer();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = connectionPool.getConnection();
            // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_TRANSFER_BY_ID);
            statement.setString(1, idString);

            rs = statement.executeQuery();

            while (rs.next()) {
                transfer.setTransfer(rs.getInt(1));
                transfer.setTransferMode(rs.getString(2));
                transfer.setPrice(new BigDecimal(rs.getInt(3)));
            }
            return transfer;

        } catch (SQLException | PoolException e) {
            logger.error("Element does not found ", e);
            throw new DAOException(e);

        } finally {
            try {
                connectionPool.closeConnection(connection, statement, rs);
            } catch (PoolException e) {
                logger.error("Can't close connection ", e);
                throw new DAOException(e);
            }

        }
    }

    @Override
    public BigDecimal getTransferTotalPrice(int idTransfer, int distance) {
        return null;
    }

    @Override
    public List<Transfer> findAll() throws DAOException {
        return null;
    }

    @Override
    public boolean update(Transfer entity) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean deleteByid(int id) throws DAOException {
        boolean done = true;
        return false;
    }

    @Override
    public boolean add(Transfer entity) throws DAOException {
        boolean done = true;
        return false;
    }
}
