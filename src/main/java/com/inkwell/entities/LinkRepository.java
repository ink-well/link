package com.inkwell.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkRepository extends CrudRepository<Link, Long> {
    List<Link> findByCategory(String category);
}
