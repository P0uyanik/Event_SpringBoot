package com.example.eventversenden.controller;

import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;
@RestController
public class SseController {
    private final CopyOnWriteArrayList<SseEmitter> ermitters = new CopyOnWriteArrayList<>();
    @GetMapping("/Behalter")
    public SseEmitter handle() {
        System.out.println("ssssssssssss");
        SseEmitter sseEmitter = new SseEmitter();
        this.ermitters.add(sseEmitter);
        sseEmitter.onCompletion(() -> this.ermitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> this.ermitters.remove(sseEmitter));
        return sseEmitter;
    }
    @EventListener
    public void onBehalter(Behalter behalter) {
        System.out.println("ggggggggggggggggggggggg");
        this.ermitters.forEach(sseEmitter ->
        {
            try {
                sseEmitter.send(SseEmitter.event().data(behalter.getLocalDateTime()).name("behalter"));
            } catch (IOException e) {
                sseEmitter.completeWithError(e);
            }
        });
    }

}