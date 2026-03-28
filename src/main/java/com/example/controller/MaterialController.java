package com.example.controller;

import com.example.entity.Material;
import com.example.service.MaterialServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialServices services;

    @PostMapping("/upload")
    public String materialUpload(@RequestBody Material material){
        return services.materialUpload(material);
    }

    @GetMapping("/all")
    public List<Material> getAll(){
        return services.getAll();
    }

    @GetMapping("/{id}")
    public Material getById(@PathVariable Long id){
        return services.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        services.delete(id);
    }
}
