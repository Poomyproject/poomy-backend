package com.poomy.mainserver.newsletter.dto;

import com.poomy.mainserver.newsletter.entity.LikeNewsletter;
import com.poomy.mainserver.newsletter.entity.Newsletter;
import lombok.Builder;

@Builder
public record LikeNewsLetterResDto(Long id, Long user_feedback, Boolean isLike) {
    public static LikeNewsLetterResDto of(LikeNewsletter likeNewsletter, Newsletter newsletter) {
        return LikeNewsLetterResDto
                .builder()
                .id(likeNewsletter.getId())
                .user_feedback(newsletter.getUserFeedback())
                .isLike(likeNewsletter.getIsLike())
                .build();
    }
}
