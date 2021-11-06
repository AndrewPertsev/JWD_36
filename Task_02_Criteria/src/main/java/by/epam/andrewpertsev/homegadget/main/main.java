package by.epam.andrewpertsev.homegadget.main;

import by.epam.andrewpertsev.homegadget.entity.criteria.Criteria;
import by.epam.andrewpertsev.homegadget.entity.Device;
import by.epam.andrewpertsev.homegadget.service.DeviceServiceIface;
import by.epam.andrewpertsev.homegadget.service.ServiceFactory;
import by.epam.andrewpertsev.homegadget.service.impl.PrintDeviceInfo;

import java.util.List;

import static by.epam.andrewpertsev.homegadget.entity.criteria.SearchCriteria.*;

public class main {
    public static void main(String[] args) {

        List<Device> device1;
        List<Device> device2;
        List<Device> device3;
        List<Device> device4;
        boolean isAddDevice5;

        ServiceFactory factory = ServiceFactory.getInstance();
        DeviceServiceIface service = factory.getDeviceService();

        //////////////////////////////////////////////////////////////////

        Criteria<OVEN> criteriaOven = new Criteria<OVEN>(OVEN.class);
        criteriaOven.add(OVEN.CAPACITY, 33.0);

        device1 = service.find(criteriaOven);
        PrintDeviceInfo.printListDevice(device1);

       //////////////////////////////////////////////////////////////////

        Criteria<OVEN> criteriaOven2 = new Criteria<>(OVEN.class);
        criteriaOven2.add(OVEN.HEIGHT, 200.0);
        criteriaOven2.add(OVEN.DEPTH, 80.0);

        device2 = service.find(criteriaOven2);

        PrintDeviceInfo.printListDevice(device2);
         /////////////////////////////////////////////////////////////////////////////

        Criteria<REFRIGERATOR> criteriaRef = new Criteria<>(REFRIGERATOR.class);
        criteriaRef.add(REFRIGERATOR.HEIGHT, 200.0);
       criteriaRef.add(REFRIGERATOR.FREEZER_CAPACITY, 10.0);

        device3 = service.find(criteriaRef);

        PrintDeviceInfo.printListDevice(device3);
        //////////////////////////////////////////////////////////////////

        Criteria<TABLETPC> criteriaTabletPC = new Criteria<TABLETPC>(TABLETPC.class);
        criteriaTabletPC.add(TABLETPC.COLOR, "BLUE");
        criteriaTabletPC.add(TABLETPC.DISPLAY_INCHES, 8888814.0);
        criteriaTabletPC.add(TABLETPC.MEMORY_ROM, 4.0);

        device4 = service.find(criteriaTabletPC);

        PrintDeviceInfo.printListDevice(device4);
///////////////////////////////////////////////////////////////////////////////////////////////////////
//        Criteria<SPEAKERS> criteriaSpeakers = new Criteria<SPEAKERS>(SPEAKERS.class);
//        criteriaSpeakers.add(SPEAKERS.FREQUENCY_RANGE, "BLUE");
//        criteriaSpeakers.add(SPEAKERS.ID, 20);
//        criteriaSpeakers.add(SPEAKERS.POWER_CONSUMPTION, 444);
//        criteriaSpeakers.add(SPEAKERS.NUMBER_OF_SPEAKERS, 4);
//        criteriaSpeakers.add(SPEAKERS.CORD_LENGTH, 4);
//
//        isAddDevice5 = service.add(criteriaSpeakers);
//        System.out.println(isAddDevice5);/////////////////////////////////////////////////////

    }
}
