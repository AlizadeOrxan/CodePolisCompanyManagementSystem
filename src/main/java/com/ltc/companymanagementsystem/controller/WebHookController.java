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

            String branch = root.path("ref").asText();

            String commitMessage = root.path("head_commit").path("message").asText();

            String repoName = root.path("repository").path("name").asText();

            log.info("======= GITHUB UCHUN MELUMAAT =======");


            log.info("Repository: {}", repoName);
            log.info("Istifadechi: {}", pusherName);
            log.info("Branch: {}", branch);
            log.info("Mesaj: {}", commitMessage);
            log.info("===============================");

        } catch (Exception e) {
            log.error("Parsing Xetasi: {}", e.getMessage());
        }
        return ResponseEntity.ok("Webhook ugurla alindi !!");

    }



}
