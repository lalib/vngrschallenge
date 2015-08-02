package com.bilalalp.vngrschallenge.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * This is just a dto object to read xml file easily.
 */
@Getter
@Setter
@XStreamAlias("contact")
public class ContactDtoList {

    /**
     * Id with given field.
     */
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
     * Contact Phone
     */
    private String phones;
}
