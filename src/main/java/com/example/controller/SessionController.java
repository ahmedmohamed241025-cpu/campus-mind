package com.example.controller;

import com.example.entity.Session;
import com.example.service.SessionServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionServices services;

    @PostMapping("/create")
    public Session createdSession(@RequestBody Session session){
        return services.createSession(session);
    }

    @GetMapping
    public List<Session> getAllSessions() {
        return services.getAllSession();
    }
    @GetMapping("/{id}")
    public Session getSessionById(@PathVariable Long id)
    {
        return services.getById(id);
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Session session){
        services.update(id, session);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        services.deleteById(id);
    }




}
