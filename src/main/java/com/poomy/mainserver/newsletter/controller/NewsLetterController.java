package com.poomy.mainserver.newsletter.controller;

import com.poomy.mainserver.newsletter.dto.LikeNewsLetterResDto;
import com.poomy.mainserver.newsletter.dto.NewsLetterResDto;
import com.poomy.mainserver.newsletter.dto.NewsletterByIdResDto;
import com.poomy.mainserver.newsletter.service.NewsletterService;
import com.poomy.mainserver.util.api.ApiResult;
import com.poomy.mainserver.util.api.ApiUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/newsLetter")
public class NewsLetterController {
    private final NewsletterService newsletterService;

    @GetMapping("/new")
    public ResponseEntity<ApiResult<List<NewsLetterResDto>>> getNewNewsLetters() {
        List<NewsLetterResDto> newsLetterResDtos = newsletterService.getNewNewsletters();
        return ResponseEntity.ok(ApiUtils.success(newsLetterResDtos));
    }

    @GetMapping("/old")
    public ResponseEntity<ApiResult<List<NewsLetterResDto>>> getOldNewsLetters() {
        List<NewsLetterResDto> newsLetterResDtos = newsletterService.getOldNewsletters();
        return ResponseEntity.ok(ApiUtils.success(newsLetterResDtos));
    }

    @GetMapping("/hot")
    public ResponseEntity<ApiResult<List<NewsLetterResDto>>> getHotNewsLetters() {
        List<NewsLetterResDto> newsLetterResDtos = newsletterService.getHotNewsletters();
        return ResponseEntity.ok(ApiUtils.success(newsLetterResDtos));
    }

    @GetMapping("{newsletterId}")
    public ResponseEntity<ApiResult<NewsletterByIdResDto>> getNewsletterById(@PathVariable Long newsletterId) {
        NewsletterByIdResDto newsletterByIdResDto = newsletterService.getNewsletterById(newsletterId);
        return ResponseEntity.ok(ApiUtils.success(newsletterByIdResDto));
    }

    @PostMapping("/{newsletterId}/like")
    public ResponseEntity<ApiResult<LikeNewsLetterResDto>> insertUserFeedback(@PathVariable Long newsletterId) {
        LikeNewsLetterResDto likeNewsLetterResDto = newsletterService.updateLikeUserFeedbackById(newsletterId);
        return ResponseEntity.ok(ApiUtils.success(likeNewsLetterResDto));
    }

    @PostMapping("/{newsletterId}/unlike")
    public ResponseEntity<ApiResult<LikeNewsLetterResDto>> deleteUserFeedback(@PathVariable Long newsletterId) {
        LikeNewsLetterResDto likeNewsLetterResDto = newsletterService.updateUnlikeUserFeedbackById(newsletterId);
        return ResponseEntity.ok(ApiUtils.success(likeNewsLetterResDto));
    }

}

