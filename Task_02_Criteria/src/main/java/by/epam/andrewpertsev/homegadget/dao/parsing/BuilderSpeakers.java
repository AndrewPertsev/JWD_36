package by.epam.andrewpertsev.homegadget.dao.parsing;

import by.epam.andrewpertsev.homegadget.dao.Buildable;
import by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.entity.Speakers;
import org.jdom2.Element;

import java.util.HashMap;
import java.util.Map;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.SPEAKERS.*;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class BuilderSpeakers implements Buildable {

    @Override
    public Device constructDevice(Element value) {
        Device builderSpeakers = new BuilderSpeakers().buildMapSpeakers(value);
        return builderSpeakers;
    }

    public static Device buildMapSpeakers(Element value) {

        Map<SearchCriteria.SPEAKERS, Object> parametersSpeakersMAP = new HashMap<>();

        Speakers speakers = new Speakers(parametersSpeakersMAP);

        parametersSpeakersMAP.put(ID, parseInt(value.getChildText("ID")));
        parametersSpeakersMAP.put(POWER_CONSUMPTION, parseDouble(value.getChildText("POWER_CONSUMPTION")));
        parametersSpeakersMAP.put(NUMBER_OF_SPEAKERS, parseDouble(value.getChildText("NUMBER_OF_SPEAKERS")));
        parametersSpeakersMAP.put(FREQUENCY_RANGE, value.getChildText("FREQUENCY_RANGE"));
        parametersSpeakersMAP.put(CORD_LENGTH, parseDouble(value.getChildText("CORD_LENGTH")));

        return speakers;
    }

}
