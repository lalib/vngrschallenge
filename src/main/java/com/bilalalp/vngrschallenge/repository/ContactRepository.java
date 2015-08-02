package com.bilalalp.vngrschallenge.repository;

import com.bilalalp.vngrschallenge.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * This repository object is used for crud operations on MongoDB easily.
 */
public interface ContactRepository extends MongoRepository<Contact, String> {

    /**
     * It returns all relevant results with given name.
     */
    List<Contact> findByNameLike(String name);

    /**
     * It returns a unique contact for given name and lastName.
     */
    Contact findByNameAndLastName(String name, String lastName);
}
