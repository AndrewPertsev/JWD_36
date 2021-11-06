package by.epam.andrewpertsev.homegadget.service.impl;

import by.epam.andrewpertsev.homegadget.entity.Device;

import java.util.List;

public class PrintDeviceInfo {

    public static void printListDevice(List<Device> device) {
        System.out.println("\nSuitable Device list : ");
        device.stream().forEach(System.out::println);

    }
    public static void printMessage(String message) {
        System.out.println(message);

    }
}
