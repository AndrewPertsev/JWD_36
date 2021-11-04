package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.Oven;
import org.jdom2.Element;
import java.util.HashMap;
import java.util.Map;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.OVEN.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class BuilderOven implements Buildable {

    @Override
    public Device constructDevice(Element value) {
        Device builderOven = new BuilderOven().buildMapOven(value);
        return builderOven;
    }

    public static Device buildMapOven(Element value) {   /*конструктор для создания объекта с MAP параметров*/

        Map<SearchCriteria.OVEN, Object> parametersOVEN = new HashMap<>();
        Oven oven = new Oven(parametersOVEN);

        parametersOVEN.put(ID, parseInt(value.getChildText("ID")));
        parametersOVEN.put(POWER_CONSUMPTION, parseDouble(value.getChildText("POWER_CONSUMPTION")));
        parametersOVEN.put(WEIGHT, parseDouble(value.getChildText("WEIGHT")));
        parametersOVEN.put(CAPACITY, parseDouble(value.getChildText("CAPACITY")));
        parametersOVEN.put(DEPTH, parseDouble(value.getChildText("DEPTH")));
        parametersOVEN.put(HEIGHT, parseDouble(value.getChildText("HEIGHT")));
        parametersOVEN.put(WIDTH, parseDouble(value.getChildText("WIDTH")));

        return oven;
    }


    public static Device buildOven(Element value) {   /*конструктор для создания объекта с параметрами Bean*/

        Oven oven = new Oven();

        oven.setId(parseInt(value.getChildText("ID")));
        oven.setPowerConsumption(parseDouble(value.getChildText("POWER_CONSUMPTION")));
        oven.setWeight(parseDouble(value.getChildText("WEIGHT")));
        oven.setCapacity(parseDouble(value.getChildText("CAPACITY")));
        oven.setDepth(parseDouble(value.getChildText("DEPTH")));
        oven.setHeight(parseDouble(value.getChildText("HEIGHT")));
        oven.setWidth(parseDouble(value.getChildText("WIDTH")));
        return oven;
    }



}
