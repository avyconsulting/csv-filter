package com.company;

import java.util.function.Function;

public class Strings {

    public static final Function<String, Boolean> isNumber = string -> string.chars().allMatch(Character::isDigit);

    public static final Function<String, Boolean> isPalindrome = string -> {
        return new StringBuilder(string).reverse().toString().equals(string);
    };

}
