package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ProviderBuilder {

    private Map<String, Buildable> buildersDevice = new HashMap<>();

    public ProviderBuilder() {
        buildersDevice.put("OVEN", new BuilderOven());
        buildersDevice.put("LAPTOP", new BuilderLaptop());
        buildersDevice.put("SPEAKERS", new BuilderSpeakers());
        buildersDevice.put("TABLETPC", new BuilderTabletPC());
        buildersDevice.put("REFRIGERATOR", new BuilderRefrigerator());
        buildersDevice.put("VACUUMCLEANER", new BuilderVacuumCleaner());
    }

    public Buildable selectDevice(String nameDevice) {
        Buildable device;
        device = buildersDevice.get(nameDevice.toUpperCase());
        if (device == null) {
            throw new NoSuchElementException("no such device in the catalog");
        }
        return device;
    }
}
