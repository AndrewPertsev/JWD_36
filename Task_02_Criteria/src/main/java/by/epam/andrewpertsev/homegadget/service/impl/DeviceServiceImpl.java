package by.epam.andrewpertsev.homegadget.service.impl;

import by.epam.andrewpertsev.homegadget.dao.DAOFactory;
import by.epam.andrewpertsev.homegadget.dao.DeviceDAO;
import by.epam.andrewpertsev.homegadget.entity.criteria.Criteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.service.DeviceServiceIface;
import by.epam.andrewpertsev.homegadget.service.validation.Validator;

import java.util.List;

public class DeviceServiceImpl implements DeviceServiceIface {

    @Override
    public <E> List<Device> find(Criteria<E> criteria) {
        if (!Validator.criteriaValidator(criteria)) {
            return null;
        }

        DAOFactory factory = DAOFactory.getInstance();
        DeviceDAO deviceDAO = factory.getDeviceDAO();

        List<Device> device = deviceDAO.find(criteria);///////////////
        // you may add your own code here

        return device;
    }
}
//you may add your own new classes
