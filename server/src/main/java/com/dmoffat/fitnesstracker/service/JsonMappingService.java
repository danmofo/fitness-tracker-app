package com.dmoffat.fitnesstracker.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is just a wrapper around Jackson that gets rid of the checked exceptions. They make our database
 * code which has JSON columns quite unpleasant.
 */
@Service
public class JsonMappingService {
    private ObjectMapper objectMapper;

    @Autowired
    public JsonMappingService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String writeToJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }
}
