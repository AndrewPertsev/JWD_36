package by.epam.andrewpertsev.homegadget.service.validation;

import by.epam.andrewpertsev.homegadget.service.Validable;
import by.epam.andrewpertsev.homegadget.service.impl.PrintDeviceInfo;

import static by.epam.andrewpertsev.homegadget.service.validation.Constants.*;
import static by.epam.andrewpertsev.homegadget.service.validation.Constants.MAX_WEIGHT;
import static java.lang.Integer.parseInt;

public class ValidatorOven implements Validable {
    @Override
    public boolean validateDevice(String groupName, String keyCriteriaMap, String valueCriteriaMap) {

        if (keyCriteriaMap.equals("CAPACITY")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_CAPACITY)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_CAPACITY + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("POWER_CONSUMPTION")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_POWER_CONSUMPTION)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_POWER_CONSUMPTION + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("WIDTH")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_WIDTH)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_WIDTH + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("DEPTH")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_DEPTH)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_DEPTH + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("HEIGHT")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_HEIGHT)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_HEIGHT + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("WEIGHT")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_WEIGHT)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_WEIGHT + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("ID")
                && (parseInt(valueCriteriaMap) <= 0
                | parseInt(valueCriteriaMap) > MAX_ID)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_ID + ", or less than 0");
            return false;
        }

        return true;
    }
}


