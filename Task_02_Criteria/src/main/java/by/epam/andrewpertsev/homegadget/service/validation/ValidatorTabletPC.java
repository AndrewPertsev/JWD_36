package by.epam.andrewpertsev.homegadget.service.validation;

import by.epam.andrewpertsev.homegadget.service.Validable;
import by.epam.andrewpertsev.homegadget.service.impl.PrintDeviceInfo;

import static by.epam.andrewpertsev.homegadget.service.validation.Constants.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ValidatorTabletPC implements Validable {
    @Override
    public boolean validateDevice(String groupName, String keyCriteriaMap, String valueCriteriaMap) {

        if (keyCriteriaMap.equals("BATTERY_CAPACITY")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_BATTERY_CAPACITY)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_BATTERY_CAPACITY + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("DISPLAY_INCHES")
                && (Double.parseDouble(valueCriteriaMap) <= 0
                | Double.parseDouble(valueCriteriaMap) > MAX_DISPLAY_INCHES)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_DISPLAY_INCHES + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("MEMORY_ROM")
                && (parseDouble(valueCriteriaMap) <= 0
                | parseDouble(valueCriteriaMap) > MAX_MEMORY_ROM)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_MEMORY_ROM + ", or less than 0");
            return false;
        }
        if (keyCriteriaMap.equals("FLASH_MEMORY_CAPACITY")
                && (parseDouble(valueCriteriaMap) <= 0
                | parseDouble(valueCriteriaMap) > MAX_FLASH_MEMORY_CAPACITY)) {
            PrintDeviceInfo.printMessage(groupName + " " + keyCriteriaMap + " can not exceed " + MAX_FLASH_MEMORY_CAPACITY + ", or less than 0");
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


