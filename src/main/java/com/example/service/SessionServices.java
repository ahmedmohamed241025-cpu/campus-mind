package com.example.service;


import com.example.dto.SessionDto;
import com.example.entity.Session;
import com.example.repository.SessionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SessionServices {

    private final SessionRepository repo;


    //  DTO → Entity
    private Session convertToEntity(SessionDto dto) {
        Session session = new Session();

        return session;
    }

    //  Entity → DTO
    private SessionDto convertToResponse(Session session) {
        SessionDto response = new SessionDto();

        return response;
    }
    // CREATE
    public SessionDto createSession(SessionDto request) {
        Session session = convertToEntity(request);
        Session saved = repo.save(session);
        return convertToResponse(saved);
    }

    // GET ALL -
    public List<SessionDto> getAllSessions() {
        return repo.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public SessionDto getById(Long id) {
        Session session = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));
        return convertToResponse(session);
    }
    // UPDATE
    public SessionDto update(Long id, SessionDto request) {

        Session session = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found with id: " + id));

        if (request.getType() != null) {
            session.setType(request.getType());
        }
        if (request.getStartTime() != null) {
            session.setStartTime(request.getStartTime());
        }
        if (request.getEndTime() != null) {
            session.setEndTime(request.getEndTime());
        }
        if (request.getName() != null) {
            session.setName(request.getName());
        }

        // حفظ التغييرات
        Session updatedSession = repo.save(session);


        return convertToResponse(updatedSession);}
        // ✅ DELETE
        public void deleteById(Long id) {
            if (!repo.existsById(id)) {
                throw new RuntimeException("Session not found with id: " + id);
            }
            repo.deleteById(id);
        }
}




