package com.poomy.mainserver.category.type;

import lombok.Getter;

@Getter
public enum HotPlaceType {

    HONGDAE("홍대"),
    ITAEWON("이태원"),
    SINSA("신사"),
    JAMSIL("잠실"),
    YEONGDEUNGPO("영등포"),
    MYEONGDONG("명동"),
    BUKCHONHANOKVILLAGE("북촌한옥마을"),
    SEONGSU("성수"),
    GANGNAM("강남"),
    SINCHON("신촌"),
    JONGNO("종로"),
    HYEHWA("혜화");

    private final String name;

    HotPlaceType(String name){
        this.name = name;
    }


}
