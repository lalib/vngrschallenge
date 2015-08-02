package com.bilalalp.vngrschallenge.stub;

import com.bilalalp.vngrschallenge.dto.ContactDto;

public class ContactDtoStub {

    public static ContactDto getFirstContactDto() {

        final ContactDto contactDto = new ContactDto();
        contactDto.setContactDtoList(ContactDtoListStub.getContactDtoLists());
        return contactDto;
    }
}