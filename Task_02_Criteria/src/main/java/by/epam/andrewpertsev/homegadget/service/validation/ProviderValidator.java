package by.epam.andrewpertsev.homegadget.service.validation;

import by.epam.andrewpertsev.homegadget.service.Validable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ProviderValidator {
    private Map<String, Validable> validatorsDevice = new HashMap<>();

    public ProviderValidator() {

        validatorsDevice.put("OVEN", new ValidatorOven());
        //  validatorsDevice.put("LAPTOP", new ValidatorLaptop());
        //  validatorsDevice.put("SPEAKERS", new ValidatorSpeakers());
        validatorsDevice.put("TABLETPC", new ValidatorTabletPC());
        validatorsDevice.put("REFRIGERATOR", new ValidatorRefrigerator());
        // validatorsDevice.put("VACUUMCLEANER", new ValidatorVacuumCleaner());
    }

    public Validable selectDevice(String nameDevice) {
        Validable device;
        device = validatorsDevice.get(nameDevice.toUpperCase());
        if (device == null) {
            throw new NoSuchElementException("no such device in the catalog");
        }
        return device;
    }
}

