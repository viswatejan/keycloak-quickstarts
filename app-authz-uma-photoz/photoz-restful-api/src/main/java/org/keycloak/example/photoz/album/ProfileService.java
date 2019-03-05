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
package org.keycloak.example.photoz.album;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * @author <a href="mailto:psilva@redhat.com">Pedro Igor</a>
 */
@RestController
@RequestMapping("/profile")
public class ProfileService {

    private static final String PROFILE_VIEW = "profile:view";

    @Autowired
    private EntityManager entityManager;

    @GetMapping
    public Profile view(HttpServletRequest request) {
        Principal userPrincipal = request.getUserPrincipal();
        List albums = this.entityManager.createQuery("from Album where userId = :id").setParameter("id", userPrincipal.getName()).getResultList();
        return new Profile(userPrincipal.getName(), albums.size());
    }

    public static class Profile {
        private String userName;
        private int totalAlbums;

        public Profile(String name, int totalAlbums) {
            this.userName = name;
            this.totalAlbums = totalAlbums;
        }

        public String getUserName() {
            return userName;
        }

        public int getTotalAlbums() {
            return totalAlbums;
        }
    }
}
