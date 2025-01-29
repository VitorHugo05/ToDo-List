package com.vitordev.todolist.domain.post.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum PostItColor {
    RED("Red"),
    YELLOW("Yellow"),
    BLUE("Blue"),
    GREEN("Green"),
    VIOLET("Violet");

    private String color;

    PostItColor(String color) {
        this.color = color;
    }

    @JsonValue
    public String getColor() {
        return color;
    }

    @JsonCreator
    public static PostItColor fromString(String color) {
        return Stream.of(PostItColor.values())
                .filter(c -> c.color.equalsIgnoreCase(color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid color: " + color));
    }
}
