package by.epam.heritage.ap.service;

import by.epam.heritage.ap.model.Entity;

import java.util.List;

public interface Serviceable<T extends Entity> {

    boolean deleteByid(int id) throws ServiceException;

    boolean update(T entity) throws ServiceException;

    boolean add(T entity) throws ServiceException;

    List<T> findAll() throws ServiceException;

   T findByid(int id) throws ServiceException;
}
