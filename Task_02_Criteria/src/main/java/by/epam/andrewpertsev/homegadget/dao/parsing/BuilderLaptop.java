package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.Laptop;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.LAPTOP.*;
import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.LAPTOP.ID;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class BuilderLaptop implements Buildable {

    public BuilderLaptop() {
    }

    @Override
    public Device constructDevice(Element value) {
        Device builderLaptop = new BuilderLaptop().buildMapLaptop(value);
        return builderLaptop;
    }

    public static Device buildMapLaptop(Element value) {

        Map<SearchCriteria.LAPTOP, Object> parametersLaptop = new HashMap<>();

        Laptop laptop = new Laptop(parametersLaptop);

        parametersLaptop.put(ID, parseInt(value.getChildText("ID")));
        parametersLaptop.put(BATTERY_CAPACITY, parseDouble(value.getChildText("BATTERY_CAPACITY")));
        parametersLaptop.put(OS, value.getChildText("OS"));
        parametersLaptop.put(MEMORY_ROM, parseInt(value.getChildText("MEMORY_ROM")));
        parametersLaptop.put(SYSTEM_MEMORY, parseInt(value.getChildText("SYSTEM_MEMORY")));
        parametersLaptop.put(CPU, parseDouble(value.getChildText("CPU")));
        parametersLaptop.put(DISPLAY_INCHES, parseDouble(value.getChildText("DISPLAY_INCHES")));

        return laptop;
    }
}


