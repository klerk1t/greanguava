package com.app.services;

import com.app.entity.TransformUser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.StreamSupport;

@Service
public class CommentService {

    @Value("${api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    public CommentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public TransformUser[] getComments() {
        var response = restTemplate.getForObject(apiUrl, JsonNode.class);

        return StreamSupport.stream(response.get("comments").spliterator(), false)
                .map(c -> new TransformUser(c.get("id").asLong(), c.get("body").asText(), c.get("postId").asLong(),
                        StringUtils.capitalize(c.get("user").get("username").asText()),
                        LocalDateTime.now().format(FORMATTER)
                        ))
                .toArray(TransformUser[]::new);
    }

}
