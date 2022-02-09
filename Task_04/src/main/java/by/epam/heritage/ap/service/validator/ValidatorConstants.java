package by.epam.heritage.ap.service.validator;

public final class ValidatorConstants {
    private ValidatorConstants() {
    }

    public static final int MAXIMUM_CAPACITY_APARTMENT = 8;
    public static final int MAXIMUM_CATEGORY_NUMBER_APARTMENT = 3;
    public static final int MAXIMUM_DAYS_FOR_BOOKING = 180;
    public static final int MAXIMUM_MENU_ID = 4;
    public static final int MAXIMUM_NUMBER_APARTMENT = 1000;
    public static final int MAXIMUM_NUMBER_REQUEST = 1000000;
    public static final int MAXIMUM_NUMBER_USER_ID = 10000;
    public static final int MAXIMUM_QUANTITY_PERSONS = 25;
    public static final int MAXIMUM_ROLE_ID = 2;
    public static final int MAXIMUM_TRANSFER_DISTANCE = 3500;
    public static final int MAXIMUM_TRANSFER_ID = 4;

    public static final int MINIMUM_ZERO = 0;

    public static final String PATTERN_DESCRIPTION = "^(.){3,200}$";
    public static final String PATTERN_DIGIT = "^[0-9]+$";
    public static final String PATTERN_NUMBER_FRACTIONAL = "^[-]?[0-9]+[\\.|,]?[0-9]*$";
    public static final String PATTERN_DATE = "^((20)[2-9][1-9])-(0[1-9]|1[012])-(0[1-9]|[12][1-9]|3[01])$";
    public static final String PATTERN_COMMENT = "^(.){0,150}$";
    public static final String PATTERN_EMAIL = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String PATTERN_NAME = "^[\\wа-яА-Я]{2,30}$";
    public static final String PATTERN_PASSPORT_NUMBER = "^[\\d]{6,12}$";
    public static final String PATTERN_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{3,16}$";
    public static final String PATTERN_PHONE = "^[+]?[\\d]{6,16}$";
    public static final String PATTERN_PICTURE_PNG = "^(.){1,100}.png$";


}
