package by.epam.heritage.ap.repository;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.model.Category;

import java.math.BigDecimal;

public interface CategoryDao extends  BaseDAO <Category>{

    BigDecimal getCategoryPrice(int  idCategory) throws DAOException;
}
