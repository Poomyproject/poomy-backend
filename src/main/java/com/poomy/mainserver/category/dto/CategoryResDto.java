package com.poomy.mainserver.category.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CategoryResDto {

    private Integer id;

    private String name;

    private String imgUrl;

}
