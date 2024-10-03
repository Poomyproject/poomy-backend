package com.poomy.mainserver.newsletter.dto;

import com.poomy.mainserver.newsletter.entity.LikeNewsletter;
import lombok.Builder;

@Builder
public record LikeNewsLetterResDto(Long id, Boolean isLike) {
    public static LikeNewsLetterResDto of(LikeNewsletter likeNewsletter) {
        return LikeNewsLetterResDto
                .builder()
                .id(likeNewsletter.getId())
                .isLike(likeNewsletter.getIsLike())
                .build();
    }
}
