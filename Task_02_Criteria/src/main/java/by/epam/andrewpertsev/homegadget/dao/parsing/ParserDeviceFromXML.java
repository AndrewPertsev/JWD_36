package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.Device;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParserDeviceFromXML {

    private static final String PATH_TO_XML_FILE = "resources\\dbase.xml";
    private ProviderBuilder provider = new ProviderBuilder();

    public List<Device> getListDevicesFromXML() {
        List<Device> catalog = new ArrayList<>();
        SAXBuilder builder = new SAXBuilder();
        Document document = null;

        try {
            document = builder.build(PATH_TO_XML_FILE);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root = document.getRootElement();
        List<Element> parsedDevicesList = root.getChildren();

        Iterator<Element> menuIterator = parsedDevicesList.iterator();
        while (menuIterator.hasNext()) {
            Element value = menuIterator.next();
            String parsedName = value.getAttributeValue("NAME");

            Buildable currentBuilder = provider.selectDevice(parsedName);
            Device currentDevice = currentBuilder.constructDevice(value);

            catalog.add(currentDevice);
        }

        return catalog;
    }
}
