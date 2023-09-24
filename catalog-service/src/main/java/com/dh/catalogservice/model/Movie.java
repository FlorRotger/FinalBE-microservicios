package com.dh.catalogservice.model;

import org.springframework.data.mongodb.core.mapping.MongoId;

public class Movie {

    @MongoId
    private Long id;
    private String name;
    private String genre;
    private String urlStream;
}
