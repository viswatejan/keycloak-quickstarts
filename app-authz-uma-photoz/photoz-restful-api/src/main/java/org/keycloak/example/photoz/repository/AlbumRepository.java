package org.keycloak.example.photoz.repository;

import org.keycloak.example.photoz.entity.Album;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Album, String> {
    
}
