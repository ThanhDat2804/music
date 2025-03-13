package com.music.taskscheduler.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Map;

@Slf4j
public class JsonUtility {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ");

    private JsonUtility() {
    }

    public static String toJson(Object object) {
        try {
            return getJackSonObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error while converting object to json", e);
            return null;
        }
    }

    public static ObjectMapper getJackSonObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT,true );
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        objectMapper.setDateFormat(DATE_FORMAT);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE );
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static <T> T fromMap(Map<String, Object> linkedHashMap, Class<T> type) {
        return getJackSonObjectMapper().convertValue(linkedHashMap, type);
    }

    public static Map<String, Object> toMap(String data) {

        try {
            return getJackSonObjectMapper().readValue(data, Map.class);
        } catch (JsonProcessingException e) {
            log.warn("JsonProcessingException - toMap", e);
            return null;
        }
    }

}
