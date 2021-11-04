package by.epam.andrewpertsev.homegadget.dao.impl;

import by.epam.andrewpertsev.homegadget.dao.parsing.ParserDeviceFromXML;
import by.epam.andrewpertsev.homegadget.entity.Device;

import java.util.List;

public class Repository {

    private static Repository instance = null;

    private List<Device> catalog;

    Repository() {
        ParserDeviceFromXML parser = new ParserDeviceFromXML();
        catalog = parser.getListDevicesFromXML();
    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    public List<Device> getData() {
        return catalog;
    }

    public void setCatalog(List<Device> catalog) {
        this.catalog = catalog;
    }
}



