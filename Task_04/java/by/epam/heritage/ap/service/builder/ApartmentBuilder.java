package by.epam.heritage.ap.service.builder;

import by.epam.heritage.ap.model.Apartment;
import by.epam.heritage.ap.service.ServiceException;
import by.epam.heritage.ap.service.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import static by.epam.heritage.ap.controller.ConstantsParametersAndAttributes.*;

public class ApartmentBuilder implements Buildable {
    private static final Logger logger = LogManager.getLogger(ApartmentBuilder.class);


    public Apartment create(HttpServletRequest request) throws ValidatorException {

        Apartment newApartment = new Apartment();

        newApartment.setApartmentId(Integer.parseInt(request.getParameter(PARAMETER_APARTMENT_ID)));
        newApartment.setCapacity(Integer.parseInt(request.getParameter(PARAMETER_CAPACITY)));
        newApartment.setCategory(Integer.parseInt(request.getParameter(PARAMETER_CATEGORY)));
        newApartment.setDescription((request.getParameter(PARAMETER_DESCRIPTION)));
        newApartment.setPathToPicture(request.getParameter(PARAMETER_PATH_TO_PICTURE));
        //    newApartment.setPrice(BigDecimal.valueOf(Long.valueOf(request.getParameter("price"))));

        return newApartment;
    }


    @Override
    public Apartment update(HttpServletRequest request) throws ServiceException, ValidatorException {
        return new ApartmentBuilder().create(request);
    }


}

