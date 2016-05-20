/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Igor Gayvan
 */
public class AddressBook {

    private static final String FOLDER_FOR_CONTACT = "./data";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Console console = new Console(System.in);

        Contact contact = new Contact();
        List<Contact> contactList = new ArrayList<>();

        console.addListener(new ActiontListener() {
            // Выход
            @Override
            public void exitAction() {
                System.exit(0);
            }

            // Показываем приглашения для ввода данных контакта
            @Override
            public void showPromptInputContactAction() {
                contact.showPromptInputContact();
            }

            // Заполняем поля контакта
            @Override
            public void addContactAction() {
                contact.inputContact(console.getInputText());

                if (contact.getCurrentInputField() == null) {
                    console.setModeWorking("CHOICE_MODE");

                } else {
                    contact.showPromptInputContact();
                }
            }

            // Показываем список контактов
            @Override
            public void showListContactsAction() {
                try {
                    showListContact(contactList);
                } catch (IOException ex) {
                    Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Показываем приглашения для ввода ID контакта
            @Override
            public void showPromptInputContactIdAction() {
                contact.showPromptInputContactId();
            }

            // Показываем данные контатка
            @Override
            public void showContactAction() {
                try {
                    showContactFromFile(console.getInputText());
                    console.setModeWorking("CHOICE_MODE");
                } catch (IOException ex) {
                    Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        console.working();
    }

    private static void showContactFromFile(String contactFileName) throws FileNotFoundException, IOException {
        File f = new File(FOLDER_FOR_CONTACT); // current directory

        Contact contact = new Contact();

        File[] files = f.listFiles();
        for (File file : files) {
            if (file.getName().equals(contactFileName)) {
                FileInputStream fis = new FileInputStream(file);

                int i = 0;
                int cntLine = 0;
                String curLine = "";

                while (fis.available() > 0) {
                    int read = fis.read();
                    if (read == 10) {
                        switch (cntLine) {
                            case 0:
                                contact.setId(curLine);
                                break;
                            case 1:
                                contact.setNameFull(curLine);
                                break;
                            case 2:
                                contact.setPhone(curLine);
                                break;
                            case 3:
                                contact.setEmail(curLine);
                                break;
                            case 4:
                                contact.setSkype(curLine);
                                break;
                        }

                        curLine = "";
                        cntLine++;
                    } else {
                        curLine = curLine + (char) read;
                    }
                }

                fis.close();
                contact.showContact();
            }
        }
    }

    public static void loadContactFromFiles(List<Contact> contactList) throws FileNotFoundException, IOException {
        Contact contact = new Contact();
        File f = new File(FOLDER_FOR_CONTACT); // current directory

        File[] files = f.listFiles();
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);

            int i = 0;
            int cntLine = 0;
            String curLine = "";

            while (fis.available() > 0) {
                int read = fis.read();
                if (read == 10) {
                    switch (cntLine) {
                        case 0:
                            contact.setId(curLine);
                            break;
                        case 1:
                            contact.setNameFull(curLine);
                            break;
                        case 2:
                            contact.setPhone(curLine);
                            break;
                        case 3:
                            contact.setEmail(curLine);
                            break;
                        case 4:
                            contact.setSkype(curLine);
                            break;
                    }

                    curLine = "";
                    cntLine++;
                } else {
                    curLine = curLine + (char) read;
                }
            }

            fis.close();
            contactList.add(contact);
        }
    }

    public static void showListContact(List<Contact> contactList) throws FileNotFoundException, IOException {
        loadContactFromFiles(contactList);

        System.out.println("\nContact's list: ");
        System.out.println("   Full name    ");
        for (Contact contact : contactList) {
            System.out.printf("%s%n", contact.getNameFull());
        }

        System.out.println();
    }
}
