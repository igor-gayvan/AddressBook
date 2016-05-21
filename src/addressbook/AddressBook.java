/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

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
        ShowData showData = new ShowData();
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
                ShowData.showAddContact(ds, contactList, contact, console.getInputText());

                if (contact.getCurrentInputField() == null) {
                    console.setModeWorking("CHOICE_MODE");

                } else {
                    contact.showPromptInputContact();
                }
            }

            // Показываем список контактов
            @Override
            public void showListContactsAction() {
                showData.showListContact(contactList);
            }

            // Показываем приглашения для ввода ID контакта
            @Override
            public void showPromptInputContactIdAction() {
                showData.showPromptInputContactId();
            }

            // Показываем данные контатка
            @Override
            public void showContactAction() {
                showData.showContactInfo(contactList, console.getInputText());
                console.setModeWorking("CHOICE_MODE");
            }

            @Override
            public void refreshDataAction() {
                try {
                    ds.loadContactFromFiles(contactList);
                } catch (IOException ex) {
                    Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
                }
                console.setModeWorking("CHOICE_MODE");
            }

            // Сортируем список контактов по телефону и показываем его
            @Override
            public void sortByPhoneAction() {
                showData.showListContact(contactList, "phone");
                console.setModeWorking("CHOICE_MODE");
            }

            // Сортируем список контактов по указанному полю и показываем его
            @Override
            public void sortByAnyFieldAction() {
                ShowData.showPromptFieldForSort();
            }

            @Override
            public void sortByAnyField() {
                ShowData.showListContact(contactList, console.getInputText().toLowerCase());

                console.setModeWorking("CHOICE_MODE");
            }

        });

        console.working();
    }
}
