package ru.job4j.tracker;

import java.util.Scanner;

public class ValidateConsoleInput implements Input {
    private final Output out;
    private final Input in;

    public ValidateConsoleInput(Output out, Input input) {
        this.out = out;
        this.in = input;
    }

    @Override
    public String askStr(String question) {
        return in.askStr(question);
    }

    @Override
    public int askInt(String question) {
        int value = -1;
        boolean invalid = true;
        do {
            try {
                value = in.askInt(question);
                invalid = false;
            } catch (NumberFormatException e) {
                out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}