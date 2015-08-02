package com.bilalalp.vngrschallenge.service;

import com.bilalalp.vngrschallenge.dto.ContactDto;

import java.io.IOException;

public interface IoService {

    /**
     * It reads the file with given path and returns the ContactDto object.
     * <p>
     * All Contacts are stored in it.
     *
     * @throws IOException
     */
    ContactDto readFile(String path) throws IOException;
}
