package com.music.taskscheduler.utils;

import io.swagger.v3.core.util.Json;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Map;

@Converter
public class MapConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> map) {
        return JsonUtility.toJson(map);
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String s) {
        return JsonUtility.toMap(s);
    }
}
