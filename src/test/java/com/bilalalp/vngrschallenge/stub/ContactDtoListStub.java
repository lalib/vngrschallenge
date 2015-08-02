package com.bilalalp.vngrschallenge.stub;

import com.bilalalp.vngrschallenge.dto.ContactDtoList;

import java.util.ArrayList;
import java.util.List;

public class ContactDtoListStub {

    public static ContactDtoList getFirstContactDtoList() {

        final ContactDtoList contactDtoList = new ContactDtoList();
        contactDtoList.setId("1");
        contactDtoList.setName("Bilal");
        contactDtoList.setLastName("ALP");
        contactDtoList.setPhones("5544558231");
        return contactDtoList;
    }

    public static List<ContactDtoList> getContactDtoLists() {

        final ContactDtoList firstContactDtoList = getFirstContactDtoList();
        final ContactDtoList secondContactDtoList = getFirstContactDtoList();

        secondContactDtoList.setName("İbrahim");
        secondContactDtoList.setLastName("Alp");

        final List<ContactDtoList> contactDtoLists = new ArrayList<>();
        contactDtoLists.add(firstContactDtoList);
        contactDtoLists.add(secondContactDtoList);

        return contactDtoLists;
    }
}
