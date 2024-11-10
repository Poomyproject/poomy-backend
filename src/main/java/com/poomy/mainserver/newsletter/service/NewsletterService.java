package com.poomy.mainserver.newsletter.service;

import com.poomy.mainserver.newsletter.dto.HomeNewsLetterResDto;
import com.poomy.mainserver.newsletter.dto.LikeNewsLetterResDto;
import com.poomy.mainserver.newsletter.dto.NewsLetterResDto;
import com.poomy.mainserver.newsletter.dto.NewsletterByIdResDto;
import com.poomy.mainserver.newsletter.entity.LikeNewsletter;
import com.poomy.mainserver.newsletter.entity.Newsletter;
import com.poomy.mainserver.newsletter.entity.NewsletterImage;
import com.poomy.mainserver.newsletter.entity.NewsletterShop;
import com.poomy.mainserver.newsletter.repository.LikeNewsletterRepository;
import com.poomy.mainserver.newsletter.repository.NewsletterImageRepository;
import com.poomy.mainserver.newsletter.repository.NewsletterRepository;
import com.poomy.mainserver.newsletter.repository.NewsletterShopRepository;
import com.poomy.mainserver.user.entity.User;
import com.poomy.mainserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;
    private final UserService userService;
    private final NewsletterImageRepository newsletterImageRepository;
    private final LikeNewsletterRepository likeNewsletterRepository;
    private final NewsletterShopRepository newsletterShopRepository;

    public List<HomeNewsLetterResDto> getRandomNewsletters() {
        return newsletterRepository.getRandomNewsletters().stream().map(newsletter -> {
            NewsletterImage newsletterImage = newsletterImageRepository.findNewsletterImageByNewsletterId(newsletter.getId());
            return HomeNewsLetterResDto.ofHomeNewsLetterResDto(newsletter, newsletterImage);
        }).toList();
    }

    public List<NewsLetterResDto> getNewNewsletters() {

        List<NewsLetterResDto> newsLetters = newsletterRepository.getAllNewNewsLetter().stream().map(newsletter -> {
            NewsletterImage newsletterImage = newsletterImageRepository.findNewsletterImageByNewsletterId(newsletter.getId());
            return NewsLetterResDto.ofNewsLetterResDto(newsletter, newsletterImage);
        }).toList();
        return newsLetters;
    }

    public List<NewsLetterResDto> getOldNewsletters() {

        List<NewsLetterResDto> newsLetters = newsletterRepository.getAllOldNewsLetter().stream().map(newsletter -> {
            NewsletterImage newsletterImage = newsletterImageRepository.findNewsletterImageByNewsletterId(newsletter.getId());
            return NewsLetterResDto.ofNewsLetterResDto(newsletter, newsletterImage);
        }).toList();
        return newsLetters;
    }

    public List<NewsLetterResDto> getHotNewsletters() {

        List<NewsLetterResDto> newsLetters = newsletterRepository.getAllHotNewsLetter().stream().map(newsletter -> {
            NewsletterImage newsletterImage = newsletterImageRepository.findNewsletterImageByNewsletterId(newsletter.getId());
            return NewsLetterResDto.ofNewsLetterResDto(newsletter, newsletterImage);
        }).toList();
        return newsLetters;
    }


    public NewsletterByIdResDto getNewsletterById(Long newsletterId) {
        User user = userService.getUser();
        Newsletter newsletter = newsletterRepository.getNewsletterById(newsletterId);
        NewsletterImage newsletterImage = newsletterImageRepository.getNewsletterImageById(newsletterId);
        NewsletterShop newsletterShop1 = newsletterShopRepository.getNewsletterShopById(newsletter.getShop1().getId());
        NewsletterShop newsletterShop2 = newsletterShopRepository.getNewsletterShopById(newsletter.getShop2().getId());
        NewsletterShop newsletterShop3 = newsletterShopRepository.getNewsletterShopById(newsletter.getShop3().getId());
        Boolean userFeedback = false;
        LikeNewsletter likeNewsletter = likeNewsletterRepository.getLikeNewsletter(user.getId(), newsletterId);
        if(likeNewsletter!=null){
            userFeedback = likeNewsletter.getIsLike();
        }
        NewsletterByIdResDto newsletterByIdResDto = NewsletterByIdResDto.ofNewsletterById(newsletter, newsletterImage, newsletterShop1, newsletterShop2, newsletterShop3, userFeedback);
        return newsletterByIdResDto;
    }

    public LikeNewsLetterResDto updateLikeUserFeedbackById(Long newsletterId) {
        User user = userService.getUser();
        LikeNewsletter checkLikeNewsletter = likeNewsletterRepository.getLikeNewsletter(user.getId(), newsletterId);
        if (checkLikeNewsletter == null) {
            likeNewsletterRepository.insertUserToLikeNewsletter(user.getId(), newsletterId);
            newsletterRepository.increaseUserFeedbackById(newsletterId);
        } else if (!checkLikeNewsletter.getIsLike()) {
            likeNewsletterRepository.updateLikeUserFeedbackById(user.getId(), newsletterId);
            newsletterRepository.increaseUserFeedbackById(newsletterId);
        }

        LikeNewsletter likeNewsletter = likeNewsletterRepository.getLikeNewsletter(user.getId(), newsletterId);
        LikeNewsLetterResDto likeNewsLetterResDto = LikeNewsLetterResDto.of(likeNewsletter);
        return likeNewsLetterResDto;
    }

    public LikeNewsLetterResDto updateUnlikeUserFeedbackById(Long newsletterId) {
        User user = userService.getUser();
        LikeNewsletter checkLikeNewsletter = likeNewsletterRepository.getLikeNewsletter(user.getId(), newsletterId);
        if (checkLikeNewsletter.getIsLike()) {
            likeNewsletterRepository.updateUnlikeUserFeedbackById(user.getId(), newsletterId);
            newsletterRepository.decreaseUserFeedbackById(newsletterId);
        }
        LikeNewsletter likeNewsletter = likeNewsletterRepository.getLikeNewsletter(user.getId(), newsletterId);
        LikeNewsLetterResDto likeNewsLetterResDto = LikeNewsLetterResDto.of(likeNewsletter);
        return likeNewsLetterResDto;
    }

}
