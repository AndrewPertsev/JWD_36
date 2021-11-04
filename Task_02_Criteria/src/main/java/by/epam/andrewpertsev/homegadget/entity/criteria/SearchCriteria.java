package by.epam.andrewpertsev.homegadget.entity.criteria;

public class SearchCriteria {

    public static enum OVEN {
        ID, POWER_CONSUMPTION, WEIGHT, CAPACITY, DEPTH, HEIGHT, WIDTH
    }

    public static enum LAPTOP {
        ID, BATTERY_CAPACITY, OS, MEMORY_ROM, SYSTEM_MEMORY, CPU, DISPLAY_INCHES
    }

    public static enum REFRIGERATOR {
        ID, POWER_CONSUMPTION, WEIGHT, FREEZER_CAPACITY, OVERALL_CAPACITY, HEIGHT, WIDTH
    }

    public static enum VACUUMCLEANER {
        ID, POWER_CONSUMPTION, FILTER_TYPE, BAG_TYPE, WAND_TYPE, MOTOR_SPEED_REGULATION, CLEANING_WIDTH
    }

    public static enum TABLETPC {
        ID, BATTERY_CAPACITY, DISPLAY_INCHES, MEMORY_ROM, FLASH_MEMORY_CAPACITY, COLOR
    }

    public static enum SPEAKERS {
        ID, POWER_CONSUMPTION, NUMBER_OF_SPEAKERS, FREQUENCY_RANGE, CORD_LENGTH
    }

    private SearchCriteria() {
    }
}



