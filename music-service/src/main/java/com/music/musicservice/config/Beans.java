package com.music.musicservice.config;

import com.music.musicservice.service.SentimentAnalyzer;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.service.AiServices;
import org.neo4j.cypherdsl.core.renderer.Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    org.neo4j.cypherdsl.core.renderer.Configuration cypherDslConfiguration() {
        return org.neo4j.cypherdsl.core.renderer.Configuration.newConfig()
                .withDialect(Dialect.NEO4J_5).build();
    }

    @Bean
    public SentimentAnalyzer sentimentAnalyzer(ChatLanguageModel chatLanguageModel) {
        return AiServices.create(SentimentAnalyzer.class, chatLanguageModel);
    }


}