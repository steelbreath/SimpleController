package com.shpp.simplecontroller.validation;

import jakarta.validation.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

class IPNValidatorTest {

    private ConstraintValidatorContext constraintValidatorContext;
    private IPNValidator ipnValidator;

    @BeforeEach
    void setUp(){
        constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
        ipnValidator = new IPNValidator();
    }

    @Test
    void isValidTest() {
        Assertions.assertTrue(ipnValidator.isValid(1234567899L, constraintValidatorContext));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidIPNsForTest")
    void isNotValidTest(Long ipn) {
        Assertions.assertFalse(ipnValidator.isValid(ipn, constraintValidatorContext));
    }

    private static Stream<Arguments> provideInvalidIPNsForTest() {
        return Stream.of(
                Arguments.of(1234567890L),
                Arguments.of(1234567891L),
                Arguments.of(1234567892L),
                Arguments.of(1234567893L),
                Arguments.of(1234567894L),
                Arguments.of(1234567895L),
                Arguments.of(1234567896L),
                Arguments.of(1234567897L),
                Arguments.of(1234567898L)

        );
    }
}