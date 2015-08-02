package com.bilalalp.vngrschallenge.stub;

import com.bilalalp.vngrschallenge.entity.Contact;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactStub {

    public static List<Contact> getContactList() {

        final List<Contact> contactList = new ArrayList<>();
        contactList.add(getFirstContact());
        contactList.add(getSecondContact());
        return contactList;
    }

    public static Contact getFirstContact() {

        final Contact contact = new Contact();
        contact.setId("1");
        contact.setName("Bilal");
        contact.setLastName("Alp");

        final Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("5544558231");
        contact.setPhoneNumbers(phoneNumbers);

        return contact;
    }

    public static Contact getSecondContact(){

        final Contact contact = new Contact();
        contact.setId("1");
        contact.setName("İbrahim");
        contact.setLastName("Alp");

        final Set<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add("5544558232");
        contact.setPhoneNumbers(phoneNumbers);

        return contact;
    }
}
