package by.epam.heritage.ap.service.validator;

import org.junit.jupiter.api.Test;

import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_EMAIL;
import static by.epam.heritage.ap.service.validator.ValidatorConstants.PATTERN_NAME;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidableTest {

    @Test
    void testValidateParameterStringClassName() {
        boolean expected1;
        boolean expected2;
        boolean expected3;
        boolean expected4;
        boolean expected5;

        String name1 = "Аня";
        String name2 = "-John";
        String name3 = "4+7";
        String name4 = "";
        String name5 = "Shelby Browning";

        expected1 = Validable.validateParameterStringClass(name1, PATTERN_NAME);
        expected2 = Validable.validateParameterStringClass(name2, PATTERN_NAME);
        expected3 = Validable.validateParameterStringClass(name3, PATTERN_NAME);
        expected4 = Validable.validateParameterStringClass(name4, PATTERN_NAME);
        expected5 = Validable.validateParameterStringClass(name5, PATTERN_NAME);

        assertTrue(expected1);

        assertFalse(expected2);
        assertFalse(expected3);
        assertFalse(expected4);
        assertFalse(expected5);
    }

    @Test
    void testValidateStringParameterIntegerClass() {
        int min = 0;
        int max = 100;
        boolean expected1;
        boolean expected2;
        boolean expected3;
        boolean expected4;
        boolean expected5;

        String name1 = "41";
        String name2 = "l";
        String name3 = "";
        String name4 = "Digit";
        String name5 = "101";

        expected1 = Validable.validateStringParameterIntegerClass(name1, max, min);
        expected2 = Validable.validateStringParameterIntegerClass(name2, max, min);
        expected3 = Validable.validateStringParameterIntegerClass(name3, max, min);
        expected4 = Validable.validateStringParameterIntegerClass(name4, max, min);
        expected5 = Validable.validateStringParameterIntegerClass(name5, max, min);

        assertTrue(expected1);

        assertFalse(expected2);
        assertFalse(expected3);
        assertFalse(expected4);
        assertFalse(expected5);
    }

    @Test
    void testValidateStringParameterClassEmail() {
        boolean expected1;
        boolean expected2;
        boolean expected3;
        boolean expected4;
        boolean expected5;

        String name1 = "good@mail.we";
        String name2 = "mail.we";
        String name3 = "bad@дом.we";
        String name4 = "good@mailwe";
        String name5 = "$$$$$@mail.we";

        expected1 = Validable.validateParameterStringClass(name1, PATTERN_EMAIL);
        expected2 = Validable.validateParameterStringClass(name2, PATTERN_EMAIL);
        expected3 = Validable.validateParameterStringClass(name3, PATTERN_EMAIL);
        expected4 = Validable.validateParameterStringClass(name4, PATTERN_EMAIL);
        expected5 = Validable.validateParameterStringClass(name5, PATTERN_EMAIL);

        assertTrue(expected1);

        assertFalse(expected2);
        assertFalse(expected3);
        assertFalse(expected4);
        assertFalse(expected5);
    }
}