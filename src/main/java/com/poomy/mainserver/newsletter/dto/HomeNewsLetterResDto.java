package com.poomy.mainserver.newsletter.dto;

import com.poomy.mainserver.newsletter.entity.Newsletter;
import com.poomy.mainserver.newsletter.entity.NewsletterImage;
import lombok.Builder;

@Builder
public record HomeNewsLetterResDto(Long id, String headline, String subTopic, String firstKeyword, String secondKeyword, String thirdKeyword, String mainImage) {

    public static HomeNewsLetterResDto ofHomeNewsLetterResDto(Newsletter newsletter, NewsletterImage newsletterImage){
        return HomeNewsLetterResDto.builder()
                .id(newsletter.getId())
                .headline(newsletter.getHeadline())
                .subTopic(newsletter.getSubtopic())
                .firstKeyword('#'+newsletter.getFirstKeyword())
                .secondKeyword('#'+newsletter.getSecondKeyword())
                .thirdKeyword('#'+newsletter.getThirdKeyword())
                .mainImage(newsletterImage.getMainPhoto())
                .build();
    }
}
