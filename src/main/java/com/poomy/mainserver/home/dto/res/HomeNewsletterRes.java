package com.poomy.mainserver.home.dto.res;

import com.poomy.mainserver.newsletter.entity.Newsletter;
import lombok.Builder;

@Builder
public record HomeNewsletterRes(Long id, String title, String image, String content) {

    public static HomeNewsletterRes of(Newsletter newsletter) {
        return HomeNewsletterRes
                .builder()
                .id(newsletter.getId())
                .title(newsletter.getTitle())
                .content(newsletter.getExplanation())
                .image(newsletter.getThumbnail())
                .build();
    }
}
