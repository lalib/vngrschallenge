package com.bilalalp.vngrschallenge.service;

public interface ContactService {

    /**
     * Processes the given file and insert to database.
     *
     * @param filePath
     */
    void processFiles(String filePath);

    /**
     * It searches people with given name.
     *
     * @param name
     */
    void searchByName(String name);

}