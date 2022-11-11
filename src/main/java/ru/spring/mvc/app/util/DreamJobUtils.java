package ru.spring.mvc.app.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class DreamJobUtils {

    public long generateId() {
        return Math.abs(new Random().nextLong());
    }
}
