package com.ltc.companymanagementsystem.controller;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/webhooks")
@Slf4j
public class WebHookController {


    @PostMapping("/github")
    public ResponseEntity<String> handleGithubRequest (@RequestBody String payload,
                                                       @RequestHeader("X-GitHub-Event") String eventType) {
        log.info("Received GitHub webhook event: {}", eventType);

        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(payload);

            String pusherName = root.path("pusher").path("name").asText();

            String commit = root.path("commit").path("message").asText();

            String header = root.path("header").asText();

            String branch = root.path("ref").asText();

            String repo = root.path("repo").path("name").asText();



            log.info("Received GitHub webhook event: {}", eventType);

            log.info("GITHUB MELUMATLARI ");
            log.info("Pusher Name: {}", pusherName);
            log.info("Commit: {}", commit);
            log.info("Header: {}", header);
            log.info("Branch: {}", branch);
            log.info("Repo: {}", repo);




        }catch (Exception e ){
            log.warn("Parsing error: {}", e.getMessage());
        }

        return ResponseEntity.ok("Webhook received successfully");

    }



}
