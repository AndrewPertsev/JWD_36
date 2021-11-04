package by.epam.andrewpertsev.homegadget.service;

import by.epam.andrewpertsev.homegadget.entity.criteria.Criteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import java.util.List;

public interface DeviceServiceIface {

    <E> List<Device> find(Criteria<E> criteria);

}
