package com.example.demo.service;

import com.example.demo.entity.Session;
import com.example.demo.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionServices {

    private final SessionRepository repo;

    public Session createSession(Session session) {
        return repo.save(session);
    }
}
