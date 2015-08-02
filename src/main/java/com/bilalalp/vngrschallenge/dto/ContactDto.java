package com.bilalalp.vngrschallenge.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * This class is used for Xstream. It makes easy to read xml file.
 */
@Getter
@Setter
@XStreamAlias("contactList")
public class ContactDto {

    /**
     * It contains all contacts
     */
    @XStreamImplicit(itemFieldName = "contact")
    private List<ContactDtoList> contactDtoList;
}
