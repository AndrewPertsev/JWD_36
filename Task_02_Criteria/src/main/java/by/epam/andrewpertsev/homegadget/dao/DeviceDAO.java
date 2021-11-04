package by.epam.andrewpertsev.homegadget.dao;

import by.epam.andrewpertsev.homegadget.entity.criteria.Criteria;
import by.epam.andrewpertsev.homegadget.entity.Device;

import java.util.List;

public interface DeviceDAO {

    <E> List<Device> find(Criteria<E> criteria);

    <E> List<Device> addToRepository(Criteria<E> criteria);

}








//    List<T> findAllDevices();//<K, T extends Device>
//
//    boolean addDevice(T t);
//
//    T findByID(int ID); //Criteria criteria
//
//    T findByParameter(K parameter); //Criteria criteria
//
//    T update(T toUpdate);
//
//    boolean deleteDevice(T t);