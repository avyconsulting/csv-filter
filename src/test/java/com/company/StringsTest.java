package com.company;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringsTest {

    @Test
    public void shouldCheckPalindrome() {
        String str = "amma";
        assertTrue(Optional.of(str).map(Strings.isPalindrome).get());

        str = "111111111";
        assertTrue(Optional.of(str).map(Strings.isPalindrome).get());
    }

    @Test
    public void shouldCheckNonPalindrome() {
        String str = "abcd";
        assertFalse(Optional.of(str).map(Strings.isPalindrome).get());
        
        str = "123456543215";
        assertFalse(Optional.of(str).map(Strings.isPalindrome).get());
    }

    @Test
    public void shouldStringContainOnlyNumberReturnTrue() {
        String str = "123123";

        Optional<Boolean> result = Stream.of(str).map(Strings.isNumber).findAny();
        assertTrue(result.get());
    }

    @Test
    public void shouldStringContainNonNumberReturnFalse() {
        String str = "123sfdfs123";

        Optional<Boolean> result = Stream.of(str).map(Strings.isNumber).findAny();
        assertFalse(result.get());
    }

    @Test
    public void shouldStringContainSpecialCharsrReturnFalse() {
        String str = "12£%!3";

        Optional<Boolean> result = Stream.of(str).map(Strings.isNumber).findAny();
        assertFalse(result.get());
    }
}
