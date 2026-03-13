package com.example.demo.controller;

import com.example.demo.entity.Session;
import com.example.demo.service.SessionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionServices services;


    @PostMapping("/create")
    public Session createdSession(@RequestBody Session session){
        return services.createSession(session);
    }
}
