package com.bilalalp.vngrschallenge.service;

import com.bilalalp.vngrschallenge.VngrsChallengeApplication;
import com.bilalalp.vngrschallenge.dto.ContactDto;
import com.bilalalp.vngrschallenge.dto.ContactDtoList;
import com.bilalalp.vngrschallenge.entity.Contact;
import com.bilalalp.vngrschallenge.repository.ContactRepository;
import com.bilalalp.vngrschallenge.stub.ContactDtoListStub;
import com.bilalalp.vngrschallenge.stub.ContactDtoStub;
import com.bilalalp.vngrschallenge.stub.ContactStub;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = VngrsChallengeApplication.class)
public class ContactServiceTest {

    @Autowired
    private ContactServiceImpl contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private IoService ioService;

    @Before
    public void before() {
        contactService = new ContactServiceImpl(contactRepository, ioService);
    }

    @Test
    public void searchByName() {

        Mockito.when(contactRepository.findByNameLike(Mockito.anyString())).thenReturn(ContactStub.getContactList());
        contactService.searchByName("bilal");
        Mockito.verify(contactRepository).findByNameLike(Mockito.anyString());
    }

    @Test
    public void processFilesThrowsException() throws IOException {

        Mockito.when(ioService.readFile(Mockito.anyString())).thenThrow(new IOException());
        contactService.processFiles("bilal.xml");

        Mockito.verify(contactRepository, Mockito.times(0)).findByNameAndLastName(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void processFiles() throws IOException {

        final String filePath = "bilal.xml";
        final ContactDto contactDto = ContactDtoStub.getFirstContactDto();

        Mockito.when(ioService.readFile(filePath)).thenReturn(contactDto);
        Mockito.when(contactRepository.findByNameAndLastName(Mockito.anyString(), Mockito.anyString())).thenReturn(null);

        contactService.processFiles(filePath);

        Mockito.verify(contactRepository, Mockito.times(contactDto.getContactDtoList().size())).findByNameAndLastName(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(contactRepository, Mockito.times(contactDto.getContactDtoList().size())).insert(Mockito.<Contact>any());
    }

    @Test
    public void insertContactsToDatabase() {

        final List<Contact> contactList = ContactStub.getContactList();
        final Contact contact = contactList.get(0);
        final Contact contact1 = contactList.get(1);

        Mockito.when(contactRepository.findByNameAndLastName(contact.getName(), contact.getLastName())).thenReturn(null);
        Mockito.when(contactRepository.findByNameAndLastName(contact1.getName(), contact.getLastName())).thenReturn(contact1);

        contactService.insertContactsToDatabase(contactList);

        Mockito.verify(contactRepository, Mockito.times(contactList.size())).findByNameAndLastName(Mockito.anyString(), Mockito.anyString());
        Mockito.verify(contactRepository, Mockito.atLeastOnce()).insert(Mockito.<Contact>any());
        Mockito.verify(contactRepository, Mockito.atLeastOnce()).save(Mockito.<Contact>any());
    }

    @Test
    public void convertContactDtoListToContact() {

        final ContactDtoList contactDtoList = ContactDtoListStub.getFirstContactDtoList();

        final Contact contact = contactService.convertContactDtoListToContact(contactDtoList);

        Assert.assertNotNull(contact);
        Assert.assertEquals(contact.getId(), contactDtoList.getId());
        Assert.assertEquals(contact.getName(), contactDtoList.getName());
        Assert.assertThat(contact.getPhoneNumbers(), Matchers.contains("5544558231"));
    }

    @Test
    public void groupContacts() {

        final List<ContactDtoList> contactDtoLists = ContactDtoListStub.getContactDtoLists();

        final List<Contact> contactList = contactService.groupContacts(contactDtoLists);
        Assert.assertThat(contactList, Matchers.hasSize(2));
    }
}