package com.bilalalp.vngrschallenge;

import com.bilalalp.vngrschallenge.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
public class VngrsChallengeApplication implements CommandLineRunner {

    @Autowired
    private ContactService contactService;

    public static void main(final String[] args) {
        SpringApplication.run(VngrsChallengeApplication.class, args);
    }

    /**
     * You can import severals files with selecting menu 1.
     *
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(final String... strings) throws Exception {

        while (true) {
            System.out.println("1 - File Insert");
            System.out.println("2 - Name Search");
            System.out.println("3 - Exit");

            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            final String selection = br.readLine();

            switch (selection) {
                case "1":
                    fileInsert();
                    break;
                case "2":
                    searchByName();
                    break;
                case "3":
                    System.exit(0);
            }
        }
    }

    private void searchByName() throws IOException {

        System.out.println("Input a name : ");

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String name = br.readLine();
        contactService.searchByName(name);
    }

    private void fileInsert() throws IOException {

        System.out.println("Input file path : ");

        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final String filePath = br.readLine();

        contactService.processFiles(filePath);
    }
}