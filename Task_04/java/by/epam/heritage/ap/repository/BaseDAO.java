package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Entity;

import java.util.List;

public interface BaseDAO<T extends Entity> {

    boolean deleteByid(int id) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean add(T entity) throws DAOException;

    T findByid(int id) throws DAOException;

    List<T> findAll() throws DAOException;

}
