package com.bilalalp.vngrschallenge.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * Contact entity. This is used to store contacts in mongodb and query them.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {

    /**
     * id for Contact
     */
    @Id
    private String id;

    /**
     * Contact Name
     */
    private String name;

    /**
     * Contact Last Name
     */
    private String lastName;

    /**
     * Contact PhoneNumbers
     */
    private Set<String> phoneNumbers;
}