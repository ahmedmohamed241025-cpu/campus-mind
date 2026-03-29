package com.example.service;


import com.example.entity.Session;
import com.example.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServices {

    private final SessionRepository repo;
    // CREATE
    public Session createSession(Session session) {
        return repo.save(session);
    }

    // GET ALL -
    public List<Session> getAllSession() {
        return repo.findAll();
    }

    // GET BY ID
    public Session getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("// Session not found with id:" + id));
    }
    // UPDATE
    public void update(Long id, Session session) {
        Session s= repo.findById(id).orElseThrow(()->new RuntimeException("Session not found with id :" + id));
        s.setName(session.getName());
        repo.save(s);
    }
    // DELETE
    public void deleteById(Long id) {
        if (!repo.existsById(id)){
            throw new RuntimeException("Session not found with id:" + id);
        }
        repo.deleteById(id);
    }
}




