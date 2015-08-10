package com.bilalalp.vngrschallenge.service;

import com.bilalalp.vngrschallenge.VngrsChallengeApplication;
import com.bilalalp.vngrschallenge.dto.ContactDto;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = VngrsChallengeApplication.class)
public class IoServiceTest {

    private IoService ioService;

    @Before
    public void before() {
        ioService = new IoServiceImpl();
    }
    
    @Ignore
    @Test
    public void readFile() throws IOException {

        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final String filePath = classLoader.getResource("contactTest.xml").getPath().substring(1);

        final ContactDto contactDto = ioService.readFile(filePath);

        Assert.assertNotNull(contactDto);
        Assert.assertThat(contactDto.getContactDtoList(), Matchers.hasSize(2));
    }
}
