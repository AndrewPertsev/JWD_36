package by.epam.andrewpertsev.homegadget.dao;

import by.epam.andrewpertsev.homegadget.entity.Device;

import org.jdom2.Element;

public interface Buildable {

    Device constructDevice(Element value);

}
