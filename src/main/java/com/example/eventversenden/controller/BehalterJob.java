package com.example.eventversenden.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Configuration
@EnableScheduling
public class BehalterJob {
    private final ApplicationEventPublisher applicationEventPublisher;

    public BehalterJob(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Scheduled(fixedDelay = 1_000)
    void doSomething() {
        Behalter behalter = new Behalter(LocalDateTime.now());
        applicationEventPublisher.publishEvent(behalter);
    }
}
