package com.example.security;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ForumValidation {

    public boolean isForumTitleCorrectFormat(String title){
        final String FORUM_TITLE_PATTERN = "^[a-zA-Z0-9 ]{5,50}$";
        Pattern pattern = Pattern.compile(FORUM_TITLE_PATTERN);

        Matcher mather = pattern.matcher(title);
        return mather.matches();
    }

    public boolean isForumContentCorrectFormat(String content){
        final String FORUM_CONTENT_PATTERN = "^[a-zA-Z0-9 ]{10,256}$";
        Pattern pattern = Pattern.compile(FORUM_CONTENT_PATTERN);

        Matcher mather = pattern.matcher(content);
        return mather.matches();
    }
}
