package com.nkediya.bookmyshow.theatre.domain;

import com.nkediya.bookmyshow.common.Domain.Screen;
import com.nkediya.bookmyshow.common.enums.City;
import lombok.Getter;

import java.util.List;

@Getter
public class Theatre {
    private final String name;
    private final City city;
    private final List<Screen> screens;

    public Theatre(String name, City city, List<Screen> screens) {
        this.name = name;
        this.city = city;
        this.screens = screens;
    }
}
