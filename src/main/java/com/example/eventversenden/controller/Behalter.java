package com.example.eventversenden.controller;

import java.time.LocalDateTime;

public class Behalter {
    private LocalDateTime localDateTime  ;

    public Behalter(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
