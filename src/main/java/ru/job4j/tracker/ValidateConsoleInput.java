package ru.job4j.tracker;

import java.util.Scanner;

public class ValidateConsoleInput extends ConsoleInput {

    @Override
    public int askInt(String question) {
        int value = -1;
        boolean invalid = true;
        do {
        try {
            value = super.askInt(question);
            invalid = false;
        } catch (NumberFormatException e) {
            System.out.println("Please enter validate data again.");
        }
        } while (invalid);
        return value;
    }
}