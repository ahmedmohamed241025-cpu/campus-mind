package com.example.service;

import com.example.dto.LocationDto;
import com.example.entity.Location;
import com.example.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {

    private final LocationRepository repo;

    //  DTO → Entity
    private Location convertToEntity(LocationDto.LocationRequest dto) {
        Location location = new Location();
        location.setName(dto.getName());
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        return location;
    }

    //  Entity → DTO
    private LocationDto.LocationResponse convertToResponse(Location location) {
        LocationDto.LocationResponse response = new LocationDto.LocationResponse();
        response.setId(location.getId());
        response.setName(location.getName());
        response.setLatitude(location.getLatitude());
        response.setLongitude(location.getLongitude());
        return response;
    }

    //  CREATE
    public LocationDto.LocationResponse createLocation(LocationDto.LocationRequest request) {
        Location location = convertToEntity(request);
        Location savedLocation = repo.save(location);
        return convertToResponse(savedLocation);
    }

    //  GET ALL
    public List<LocationDto.LocationResponse> getAllLocations() {
        return repo.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public LocationDto.LocationResponse getById(Long id) {
        Location location = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        return convertToResponse(location);
    }

    //  UPDATE
    public LocationDto.LocationResponse update(Long id, LocationDto.LocationUpdateRequest request) {
        Location location = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));

        if (request.getName() != null) {
            location.setName(request.getName());
        }
        if (request.getLatitude() != null) {
            location.setLatitude(request.getLatitude());
        }
        if (request.getLongitude() != null) {
            location.setLongitude(request.getLongitude());
        }

        Location updatedLocation = repo.save(location);
        return convertToResponse(updatedLocation);
    }

    //  DELETE
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Location not found with id: " + id);
        }
        repo.deleteById(id);
    }
}

