/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.keycloak.example.photoz.admin;

import org.keycloak.example.photoz.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */

@RestController
@RequestMapping("/admin/album")
public class AdminAlbumService {

    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public HashMap<String, List<Album>> findAll() {
        HashMap<String, List<Album>> albums = new HashMap<String, List<Album>>();
        List<Album> result = this.entityManager.createQuery("from Album").getResultList();

        for (Album album : result) {
            List<Album> userAlbums = albums.get(album.getUserId());

            if (userAlbums == null) {
                userAlbums = new ArrayList<Album>();
                albums.put(album.getUserId(), userAlbums);
            }

            userAlbums.add(album);
        }

        return albums;
    }
}
