package com.example.service;

import com.example.entity.Session;
import com.example.repository.SessionRepository;
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
