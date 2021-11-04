package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.TabletPC;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.TABLETPC.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class BuilderTabletPC implements Buildable {

    public BuilderTabletPC() {
    }

    @Override
    public Device constructDevice(Element value) {
        Device builderTabletPC = new BuilderTabletPC().buildMapTabletPC(value);
        return builderTabletPC;
    }

    public static Device buildMapTabletPC(Element value) { /*конструктор для создания объекта с MAP параметров*/

        Map<SearchCriteria.TABLETPC, Object> parametersTabletPCMAP = new HashMap<>();

        TabletPC tabletPC = new TabletPC(parametersTabletPCMAP);

        parametersTabletPCMAP.put(ID, parseInt(value.getChildText("ID")));
        parametersTabletPCMAP.put(BATTERY_CAPACITY, parseDouble(value.getChildText("BATTERY_CAPACITY")));
        parametersTabletPCMAP.put(DISPLAY_INCHES, parseDouble(value.getChildText("DISPLAY_INCHES")));
        parametersTabletPCMAP.put(MEMORY_ROM, parseDouble(value.getChildText("MEMORY_ROM")));
        parametersTabletPCMAP.put(FLASH_MEMORY_CAPACITY, parseDouble(value.getChildText("FLASH_MEMORY_CAPACITY")));
        parametersTabletPCMAP.put(COLOR, value.getChildText("COLOR"));

        return tabletPC;
    }

    public static Device buildInstanceTabletPC(Element value) { /*конструктор для создания объекта с параметрами Bean*/

        TabletPC tabletPC = new TabletPC();

        tabletPC.setId(parseInt(value.getChildText("ID")));
        tabletPC.setBatteryCapacity(parseDouble(value.getChildText("BATTERY_CAPACITY")));
        tabletPC.setDisplayInches(parseDouble(value.getChildText("DISPLAY_INCHES")));
        tabletPC.setMemoryRom(parseDouble(value.getChildText("MEMORY_ROM")));
        tabletPC.setFlashMemoryCapacity(parseDouble(value.getChildText("FLASH_MEMORY_CAPACITY")));
        tabletPC.setColor(value.getChildText("COLOR"));

        return tabletPC;
    }
}
