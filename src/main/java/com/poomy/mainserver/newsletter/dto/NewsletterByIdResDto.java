package com.poomy.mainserver.newsletter.dto;

import com.poomy.mainserver.newsletter.entity.Newsletter;
import com.poomy.mainserver.newsletter.entity.NewsletterImage;
import com.poomy.mainserver.newsletter.entity.NewsletterShop;
import lombok.Builder;

@Builder

public record NewsletterByIdResDto(Long id, String headline, String mainPhoto, String subtopic,String keyword1, String keyword2, String keyword3,String textTop,
                                   Long firstShopId, String firstShopName, String firstShopImage1, String firstShopImage2, String firstShopTitle, String firstShopText, String firstShopLocation,
                                   Long secondShopId, String secondShopName, String secondShopImage1, String secondShopImage2, String secondShopTitle, String secondShopText, String secondShopLocation,
                                   Long thirdShopId, String thirdShopName, String thirdShopImage1, String thirdShopImage2, String thirdShopTitle, String thirdShopText, String thirdShopLocation,
                                   String textBottom, Boolean userFeedback) {
    public static NewsletterByIdResDto ofNewsletterById(Newsletter newsletter, NewsletterImage newsletterImage, NewsletterShop newsletterShop1, NewsletterShop newsletterShop2, NewsletterShop newsletterShop3, Boolean userFeedback){
        return NewsletterByIdResDto.builder()
                .id(newsletter.getId())
                .headline(newsletter.getHeadline())
                .mainPhoto(newsletterImage.getMainPhoto())
                .subtopic(newsletter.getSubtopic())
                .keyword1(newsletter.getFirstKeyword())
                .keyword2(newsletter.getSecondKeyword())
                .keyword3(newsletter.getThirdKeyword())
                .textTop(newsletter.getTextTop())
                //
                .firstShopId(newsletterShop1.getShop().getId())
                .firstShopName(newsletterShop1.getShop().getName())
                .firstShopImage1(newsletterImage.getFirstShopImage1())
                .firstShopImage2(newsletterImage.getFirstShopImage2())
                .firstShopTitle(newsletterShop1.getShopTitle())
                .firstShopText(newsletterShop1.getShopText())
                .firstShopLocation(newsletterShop1.getShop().getLocation())
                //
                .secondShopId(newsletterShop2.getShop().getId())
                .secondShopName(newsletterShop2.getShop().getName())
                .secondShopImage1(newsletterImage.getSecondShopImage1())
                .secondShopImage2(newsletterImage.getSecondShopImage2())
                .secondShopTitle(newsletterShop2.getShopTitle())
                .secondShopText(newsletterShop2.getShopText())
                .secondShopLocation(newsletterShop2.getShop().getLocation())
                //
                .thirdShopId(newsletterShop3.getShop().getId())
                .thirdShopName(newsletterShop3.getShop().getName())
                .thirdShopImage1(newsletterImage.getThirdShopImage1())
                .thirdShopImage2(newsletterImage.getThirdShopImage2())
                .thirdShopTitle(newsletterShop3.getShopTitle())
                .thirdShopText(newsletterShop3.getShopText())
                .thirdShopLocation(newsletterShop3.getShop().getLocation())
                //
                .textBottom(newsletter.getTextBottom())
                .userFeedback(userFeedback)
                .build();
    }
}
