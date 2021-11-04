package by.epam.andrewpertsev.homegadget.entity;

import java.io.Serializable;
import java.util.Map;

public abstract class Device implements Serializable {

    public abstract <E> Map<E, Object> getMap();

}
