package com.example.service;

import com.example.entity.Material;
import com.example.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialServices {

    private final MaterialRepository repo;

    public String materialUpload(Material material) {
        return repo.save(material).getId().toString();
    }

    public List<Material> getAll() {
        return repo.findAll();
    }

    public Material getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Material not found"));
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
