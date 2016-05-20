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

   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Console console = new Console(System.in);

        Contact contact = new Contact();
        List<Contact> contactList = new ArrayList<>();

        DataSource ds = new DataSource();
        ds.loadContactFromFiles(contactList);

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
                showListContact(contactList);
            }

            // Показываем приглашения для ввода ID контакта
            @Override
            public void showPromptInputContactIdAction() {
                contact.showPromptInputContactId();
            }

            // Показываем данные контатка
            @Override
            public void showContactAction() {
                showContactInfo(contactList, console.getInputText());
                console.setModeWorking("CHOICE_MODE");
            }
        });

        console.working();
    }

    public static void showListContact(List<Contact> contactList) {
        System.out.println("\nContact's list: ");
        System.out.println("   Full name    ");
        for (Contact contact : contactList) {
            System.out.printf("%s%n", contact.getNameFull());
        }

        System.out.println();
    }

    private static void showContactInfo(List<Contact> contactList, String contactId) {
        for (Contact contact : contactList) {
            if (contactId == null || (contactId != null && contact.getId().equals(contactId))) {
                contact.showContact();
                break;
            }
        }
    }

   

}
