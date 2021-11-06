package by.epam.andrewpertsev.homegadget.service.validation;

import by.epam.andrewpertsev.homegadget.entity.criteria.Criteria;
import by.epam.andrewpertsev.homegadget.service.Validable;

import java.util.Map;

public class Validator {

    public static <E> boolean criteriaValidator(Criteria<E> criteria) {
        String groupName;
        boolean isValidated =false;
        Map<E, Object> criteriaMap = criteria.getRequestParam();

        groupName = criteria.getGroupSearchName().getSimpleName().toUpperCase();

        for (Map.Entry<E, Object> criteriaEntry : criteriaMap.entrySet()) {
            var keyCriteriaMap = criteriaEntry.getKey().toString().toUpperCase();
            String valueCriteriaMap = criteriaEntry.getValue().toString();

            Validable currentDevice = new ProviderValidator().selectDevice(groupName);
            isValidated = currentDevice.validateDevice(groupName, keyCriteriaMap, valueCriteriaMap);

        }
         return isValidated;}
}

