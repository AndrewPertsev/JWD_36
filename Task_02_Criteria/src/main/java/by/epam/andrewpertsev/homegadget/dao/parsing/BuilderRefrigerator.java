package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.Refrigerator;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.REFRIGERATOR.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class BuilderRefrigerator implements Buildable {

    @Override
    public Device constructDevice(Element value) {
        Device builderRef = new BuilderRefrigerator().buildMapRefrigerator(value);
        return builderRef;
    }

    public static Device buildMapRefrigerator(Element value) {

        Map<SearchCriteria.REFRIGERATOR, Object> parameterRefrigerator = new HashMap<>();
        Refrigerator refrigerator = new Refrigerator(parameterRefrigerator);

        parameterRefrigerator.put(ID, parseInt(value.getChildText("ID")));
        parameterRefrigerator.put(POWER_CONSUMPTION, parseDouble(value.getChildText("POWER_CONSUMPTION")));
        parameterRefrigerator.put(WEIGHT, parseDouble(value.getChildText("WEIGHT")));
        parameterRefrigerator.put(FREEZER_CAPACITY, parseDouble(value.getChildText("FREEZER_CAPACITY")));
        parameterRefrigerator.put(OVERALL_CAPACITY, parseDouble(value.getChildText("OVERALL_CAPACITY")));
        parameterRefrigerator.put(HEIGHT, parseDouble(value.getChildText("HEIGHT")));
        parameterRefrigerator.put(WIDTH, parseDouble(value.getChildText("WIDTH")));

        return refrigerator;
    }
}
