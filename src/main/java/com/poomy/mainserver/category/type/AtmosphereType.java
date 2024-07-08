package com.poomy.mainserver.category.type;

import lombok.Getter;

@Getter
public enum AtmosphereType {

    CUTE("아기자기한"),
    GENTLE("온화한"),
    GLAMOROUS("화려한"),
    SOPHISTICATED("세련된"),
    CLASSIC("클래식한"),
    URBAN("도시적인"),
    MODERN("모던한");

    private final String name;

    AtmosphereType(String name){
        this.name = name;
    }


}
