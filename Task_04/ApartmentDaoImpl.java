package by.epam.ap.hotelapp.repository.dao.impl;

import by.epam.ap.hotelapp.model.impl.Apartment;
import by.epam.ap.hotelapp.repository.connection.ConnectionPool;
import by.epam.ap.hotelapp.repository.dao.ApartmentDao;
import by.epam.ap.hotelapp.repository.dao.DAOException;
import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class ApartmentDaoImpl implements ApartmentDao {
   //  private final  Logger logger = LogManager.getLogger(this.getClass().getName());

    private final static String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id = ?";
    private final static String FIND_APARTMENT_BY_ID = "SELECT apartment_id ,apartment_class ,capacity,price ,picture ,description  FROM apartments where apartment_id=?";

    

    @Override
    public Apartment findByID(int id) throws DAOException {
        //   logger.info("find apartment by id");
        String idSearch = String.valueOf(id);//request.getId();
        Apartment apartment = new Apartment();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;

        try {
            connection = ConnectionPool.INSTANCE.getConnection();
           // connection.setAutoCommit(false);

            statement = connection.prepareStatement(FIND_APARTMENT_BY_ID);
            statement.setString(1, idSearch);

            rs = statement.executeQuery();
            while (rs.next()) {
                apartment.setId(rs.getInt(1));
                apartment.setCategory(rs.getString(2));
                apartment.setCapacity(rs.getInt(3));
                apartment.setPrice(rs.getInt(4));
                apartment.setPathToPicture(rs.getString(5));
                apartment.setDescription(rs.getString(6));

            }
            return apartment;

        } catch (SQLException e) {
            throw new DAOException("Not find by id");
        } finally {
            try {
                rs.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {

                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
	
	
    @Override
    public Apartment updateDescription(String description) {
        return null;
    }

    @Override
    public List<Apartment> findAll() throws DAOException {
        return null;
    }
    @Override
    public boolean delete(Apartment entity) throws DAOException {
        return false;
    }

    @Override
    public boolean deleteByID(int id) throws DAOException {
        return false;
    }

    @Override
    public boolean add(Apartment entity) throws DAOException {
        return false;
    }
}
