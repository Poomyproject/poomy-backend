package com.poomy.mainserver.newsletter.dto;

import com.poomy.mainserver.newsletter.entity.Newsletter;
import com.poomy.mainserver.newsletter.entity.NewsletterImage;
import lombok.Builder;

@Builder
public record NewsLetterResDto(Long id, String headline, String image) {
    public static NewsLetterResDto ofNewsLetterResDto(Newsletter newsletter, NewsletterImage newsletterImage) {
        return NewsLetterResDto
                .builder()
                .id(newsletter.getId())
                .headline(newsletter.getHeadline())
                .image(newsletterImage.getMainPhoto())
                .build();
    }
}

