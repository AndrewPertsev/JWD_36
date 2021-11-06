package by.epam.andrewpertsev.homegadget.service;

public interface Validable {

    public boolean validateDevice(String deviceName, String keyCriteriaMap, String valueCriteriaMap);
}
