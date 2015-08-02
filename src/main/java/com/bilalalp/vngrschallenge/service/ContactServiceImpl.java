package com.bilalalp.vngrschallenge.service;

import com.bilalalp.vngrschallenge.dto.ContactDto;
import com.bilalalp.vngrschallenge.dto.ContactDtoList;
import com.bilalalp.vngrschallenge.entity.Contact;
import com.bilalalp.vngrschallenge.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implementation of ContactService.
 */
@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);

    private ContactRepository contactRepository;

    private IoService ioService;

    /**
     * I've used constructor injection because of tests. It makes easy to set mock objects in tests.
     */
    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository, IoService ioService) {

        this.contactRepository = contactRepository;
        this.ioService = ioService;
    }

    @Override
    public void processFiles(final String filePath) {

        try {

            final ContactDto contactDto = ioService.readFile(filePath);
            final List<Contact> contactList = groupContacts(contactDto.getContactDtoList());

            insertContactsToDatabase(contactList);

            System.out.println("Records have been added.");

        } catch (final Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.error("An error occured. Please fix the file and try again.");
        }
    }

    @Override
    public void searchByName(final String name) {

        final List<Contact> contactList = contactRepository.findByNameLike(name);
        contactList.forEach(System.out::println);
    }

    /**
     * Inserts given contacts to database.
     */
    protected void insertContactsToDatabase(final List<Contact> contactList) {

        for (final Contact contact : contactList) {

            final Contact contactByNameAndLastName = contactRepository.findByNameAndLastName(contact.getName(), contact.getLastName());

            if (contactByNameAndLastName == null) {
                contactRepository.insert(contact);
            } else {
                contactByNameAndLastName.getPhoneNumbers().addAll(contact.getPhoneNumbers());
                contactRepository.save(contactByNameAndLastName);
            }
        }
    }

    /**
     * Group contacts with name and lastName.
     */
    protected List<Contact> groupContacts(final List<ContactDtoList> contactDtoLists) {

        final Map<String, Contact> contactMap = new HashMap<>();

        for (final ContactDtoList contactDtoList : contactDtoLists) {

            final String key = contactDtoList.getName() + contactDtoList.getLastName();

            if (contactMap.containsKey(key)) {
                contactMap.get(key).getPhoneNumbers().add(contactDtoList.getPhones());
            } else {
                final Contact contact = convertContactDtoListToContact(contactDtoList);
                contactMap.put(key, contact);
            }
        }

        return new ArrayList<>(contactMap.values());
    }

    /**
     * Conver ContactDtoList object to Contact object to insert DB.
     */
    protected Contact convertContactDtoListToContact(final ContactDtoList contactDtoList) {

        final Contact contact = new Contact();
        contact.setId(contactDtoList.getId());
        contact.setName(contactDtoList.getName());
        contact.setLastName(contactDtoList.getLastName());

        final HashSet<String> phoneNumbers = new HashSet<>();
        phoneNumbers.add(contactDtoList.getPhones());
        contact.setPhoneNumbers(phoneNumbers);
        return contact;
    }
}