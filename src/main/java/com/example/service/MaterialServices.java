package com.example.service;


import com.example.dto.MaterialDto;
import com.example.entity.Course;
import com.example.entity.Material;
import com.example.repository.CourseRepository;
import com.example.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterialServices {

    private final MaterialRepository repo;
    private final CourseRepository courseRepo;



    public MaterialDto materialUpload(@Valid MaterialDto request) {

        Material material = new Material();
        material.setTitle(request.getTitle());
        material.setPdfUrl(request.getPdfUrl());
        material.setVideoUrl(request.getVideoUrl());

        //  (تحويل courseId → Course)
        Course course = courseRepo.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        material.setCourse(course);

        Material saved = repo.save(material);

        // تحويل للـ DTO
        MaterialDto response = new MaterialDto();
        response.setId(String.valueOf(saved.getId()));
        response.setTitle(saved.getTitle());
        response.setPdfUrl(saved.getPdfUrl());
        response.setVideoUrl(saved.getVideoUrl());
        response.setCourseId(saved.getCourse().getId());

        return response;
    }


    // GET ALL
    public Collection<Material> getAll() {
        return repo.findAll();
    }

    //  GET BY ID
    public Material getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));
    }

    //  UPDATE
    public MaterialDto update(Long id, @Valid MaterialDto request) {
        Material material = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));

        if (request.getTitle() != null) material.setTitle(request.getTitle());
        if (request.getPdfUrl() != null) material.setPdfUrl(request.getPdfUrl());
        if (request.getVideoUrl() != null) material.setVideoUrl(request.getVideoUrl());

        return new MaterialDto();
    }
    //  DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Material not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
