package by.epam.andrewpertsev.homegadget.entity.criteria;

import java.util.HashMap;
import java.util.Map;

public class Criteria<E> {

    private Class<E> groupSearchName;

    private Map<E, Object> requestParam = new HashMap<E, Object>();

    public Criteria(Class<E> groupSearchName) {
        this.groupSearchName = groupSearchName;
    }

    public Class<E> getGroupSearchName() {
        return groupSearchName;
    }

    public void add(E searchCriteria, Object value) {
        requestParam.put(searchCriteria, value);
    }

    public Map<E, Object> getRequestParam() {
        return requestParam;

    }
}
// you may add your own code here
