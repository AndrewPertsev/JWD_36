package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.VacuumCleaner;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.VACUUMCLEANER.*;
import static java.lang.Integer.parseInt;

public class BuilderVacuumCleaner implements Buildable {
    @Override
    public Device constructDevice(Element value) {
        Device builderVacuumCleaner = new BuilderVacuumCleaner().buildMapVacuumCleaner(value);
        return builderVacuumCleaner;
    }

    public static Device buildMapVacuumCleaner(Element value) {

        Map<SearchCriteria.VACUUMCLEANER, Object> parametersVacuumCleanerMap = new HashMap<>();

        VacuumCleaner vacuumCleaner = new VacuumCleaner(parametersVacuumCleanerMap);

        parametersVacuumCleanerMap.put(ID, parseInt(value.getChildText("ID")));
        parametersVacuumCleanerMap.put(POWER_CONSUMPTION, parseInt(value.getChildText("POWER_CONSUMPTION")));
        parametersVacuumCleanerMap.put(FILTER_TYPE, value.getChildText("FILTER_TYPE"));
        parametersVacuumCleanerMap.put(BAG_TYPE, value.getChildText("BAG_TYPE"));
        parametersVacuumCleanerMap.put(WAND_TYPE, value.getChildText("WAND_TYPE"));
        parametersVacuumCleanerMap.put(MOTOR_SPEED_REGULATION, parseInt(value.getChildText("MOTOR_SPEED_REGULATION")));
        parametersVacuumCleanerMap.put(CLEANING_WIDTH, parseInt(value.getChildText("CLEANING_WIDTH")));

        return vacuumCleaner;
    }

}


