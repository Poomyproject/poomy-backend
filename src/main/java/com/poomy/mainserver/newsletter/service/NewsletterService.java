package com.poomy.mainserver.newsletter.service;

import com.poomy.mainserver.home.dto.res.HomeNewsletterRes;
import com.poomy.mainserver.newsletter.repository.NewsletterRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    public List<HomeNewsletterRes> getRandomNewsletters() {
        return newsletterRepository.getRandomNewsletters().stream().map(HomeNewsletterRes::of).toList();
    }
}
