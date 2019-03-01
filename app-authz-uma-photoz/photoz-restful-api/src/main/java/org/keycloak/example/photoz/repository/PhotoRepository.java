package org.keycloak.example.photoz.repository;

import org.keycloak.example.photoz.entity.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotoRepository extends CrudRepository<Photo, Long> {
    
}
