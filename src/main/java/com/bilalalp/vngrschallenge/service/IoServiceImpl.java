package com.bilalalp.vngrschallenge.service;

import com.bilalalp.vngrschallenge.dto.ContactDto;
import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Implementation of IoService.
 */
@Service
public class IoServiceImpl implements IoService {

    @Override
    public ContactDto readFile(final String path) throws IOException {

        final String fileContent = new String(Files.readAllBytes(Paths.get(path)));
        final XStream xstream = new XStream();
        xstream.processAnnotations(ContactDto.class);

        // I'm adding a root node for contactList for XStream. XStream needs a root Node.
        final String rootNodeStart = "<contactList>";
        final String rootNodeEnd = "</contactList>";

        return (ContactDto) xstream.fromXML(rootNodeStart + fileContent + rootNodeEnd);
    }
}
