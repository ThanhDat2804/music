package com.music.musicservice.service;

import com.music.musicservice.model.Sentiment;
import dev.langchain4j.service.UserMessage;

public interface SentimentAnalyzer {

    @UserMessage("Analyze sentiment of {{it}}")
    Sentiment analyzeSentimentOf(String text);


}
