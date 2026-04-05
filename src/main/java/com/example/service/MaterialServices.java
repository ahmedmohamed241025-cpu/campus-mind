package com.example.service;


import com.example.dto.MaterialDto;
import com.example.entity.Course;
import com.example.entity.Material;
import com.example.repository.CourseRepository;
import com.example.repository.MaterialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MaterialServices {

    private final MaterialRepository repo;
    private final CourseRepository courseRepo;

    //  DTO → Entity
    private Material convertToEntity(MaterialDto.MaterialRequest dto) {
        Material material = new Material();
        material.setTitle(dto.getTitle());
        material.setPdfUrl(dto.getPdfUrl());
        material.setVideoUrl(dto.getVideoUrl());

        Course course = courseRepo.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found: " + dto.getCourseId()));
        material.setCourse(course);

        return material;
    }
    //  Entity → DTO
    private MaterialDto.MaterialResponse convertToResponse(Material material) {
        MaterialDto.MaterialResponse response = new MaterialDto.MaterialResponse();
        response.setId(material.getId());
        response.setTitle(material.getTitle());
        response.setPdfUrl(material.getPdfUrl());
        response.setVideoUrl(material.getVideoUrl());

        if (material.getCourse() != null) {
            response.setCourseId(material.getCourse().getId());
            response.setCourseName(material.getCourse().getName());
        }

        return response;
    }



    //  CREATE
    public MaterialDto.MaterialResponse materialUpload(MaterialDto.MaterialRequest request) {
        return convertToResponse(repo.save(convertToEntity(request)));
    }

    // GET ALL
    public List<MaterialDto.MaterialResponse> getAll() {
        return repo.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    //  GET BY ID
    public MaterialDto.MaterialResponse getById(Long id) {
        Material material = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));
        return convertToResponse(material);
    }

    //  UPDATE
    public MaterialDto.MaterialResponse update(Long id, MaterialDto.MaterialUpdateRequest request) {
        Material material = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found with id: " + id));

        if (request.getTitle() != null) material.setTitle(request.getTitle());
        if (request.getPdfUrl() != null) material.setPdfUrl(request.getPdfUrl());
        if (request.getVideoUrl() != null) material.setVideoUrl(request.getVideoUrl());

        return convertToResponse(repo.save(material));
    }
    //  DELETE
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Material not found with id: " + id);
        }
        repo.deleteById(id);
    }
}
