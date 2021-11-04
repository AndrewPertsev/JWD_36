package by.epam.andrewpertsev.homegadget.dao.impl;

import by.epam.andrewpertsev.homegadget.dao.DeviceDAO;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.criteria.Criteria;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceDAOImpl implements DeviceDAO {

    @Override
    public <E> List<Device> find(Criteria<E> criteria) {  /*Метод принимает запрос Map параметров и возвращает
                                                           List приборов с такими параметрами безотносительно к категории*/
        int countEntry = 0;
        int countAllMatch = 0;
        List<Device> matchedDevices = new ArrayList<>();

        Repository repo = Repository.getInstance();
        List<Device> repository = repo.getData();

        Map<E, Object> criteriaMap = criteria.getRequestParam();
        countAllMatch = criteriaMap.size();

        for (int i = 0; i < repository.size(); i++) {
            var deviceMapParameters = repository.get(i).getMap();
            for (var deviceEntry : deviceMapParameters.entrySet()) {
                var keyDeviceMap = deviceEntry.getKey().toString().toUpperCase();        //для поиска c привязкой к категории удалить toString().toUpperCase();
                Object valueDeviceMap = deviceEntry.getValue();

                for (Map.Entry<E, Object> criteriaEntry : criteriaMap.entrySet()) {
                    var keyCriteriaMap = criteriaEntry.getKey().toString().toUpperCase();     //для поиска c привязкой к категории удалить toString().toUpperCase();
                    Object valueCriteriaMap = criteriaEntry.getValue();

                    if (keyDeviceMap.equals(keyCriteriaMap) & valueDeviceMap.equals(valueCriteriaMap)) {
                        countEntry++;
                    }
                }
                if (countEntry == countAllMatch) {
                    matchedDevices.add(repository.get(i));
                    countEntry = 0;
                }
            }
        }
        return matchedDevices;
    }

    @Override
    public <E> List<Device> addToRepository(Criteria<E> criteria) { // метод добавляет 1 прибор в репозиторий,
        List<Device> updatedRepository;

        Repository repo = Repository.getInstance();
        updatedRepository = repo.getData();
        // Map<E, Object> criteriaMap = (Map<E, Object>)criteria.getRequestParam();
        // Device device = new Device(criteriaMap);
        // updatedRepository.add(device);
        return updatedRepository;
    }

    public boolean writeDeviceListToXML(List<Device> list) {
        boolean isAdded = false;
        final String PATH_TO_NEW_XML_REPOSITORY = "newDB.xml";
        try (XMLEncoder xmlEncoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(PATH_TO_NEW_XML_REPOSITORY)))) {
            xmlEncoder.writeObject(list);
            xmlEncoder.flush();
            isAdded = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return isAdded;
    }


    // методы для последующей реализации :



    public void findAllDevices() {
        //  private static final String SELECT_ALL_DEVICES = "SELECT * FROM DB";
        //  rep.stream().forEach(x->System.out.println(x+"\n"));
        //  return catalog;
    }

    public Device findByID(int ID) {
        // return catalog.stream().filter(x->x.getID()==ID).findAny().orElse(null);
        return null;
    }

    public Device findByParameter(Object ID) {
        return null;
    }

    public Device update(int id, Criteria criteria) {
        return null;
    }

    public boolean deleteDevice(Device device) {
        boolean isDeleted = false;
        return false;
    }

    public boolean hasKeyCriteriaInRepository(Map<String, Object> criteria, String parameterName) {
        return (criteria.keySet().contains(parameterName.toUpperCase()));
    }

    public boolean hasValueParameter(Map<String, Object> criteria, String parameterName) {
        return (criteria.keySet().contains(parameterName.toUpperCase()));
    }
}

