package com.cooksys.assessment1team3.controllers;

import com.cooksys.assessment1team3.dtos.HashtagDto;
import com.cooksys.assessment1team3.dtos.TweetResponseDto;
import com.cooksys.assessment1team3.services.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
public class HashtagController {
    private final HashtagService hashtagService;
	@GetMapping
    public List<HashtagDto> getAllHashtags() {
        return hashtagService.getAllHashtags();
    }

    @GetMapping("/{label}")
    public List<TweetResponseDto> getTagsByLabel(@PathVariable String label) {
        return hashtagService.getTagsByLabel(label);
    }
}
