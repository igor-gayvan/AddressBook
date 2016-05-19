/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.IOException;
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
    public static void main(String[] args) {
        Console console = new Console(System.in);

        Contact contact = new Contact();

        console.addListener(new ActiontListener() {
            @Override
            public void exitAction() {
                System.exit(0);
            }

            @Override
            public void addShowContactAction() {
                contact.showPromptInputContact();
            }

            @Override
            public void addContactAction() {
                contact.inputContact(console.getInputText());

                if (contact.getCurrentInputField() == null) {
                    console.setModeWorking("CHOICE_MODE");

                } else {
                    contact.showPromptInputContact();
                }

            }

            @Override
            public void showListContactsAction() {
                try {
                    contact.showListContact();
                } catch (IOException ex) {
                    Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }

            @Override
            public void showContactAction() {
                System.exit(0);
            }
        });

        console.working();
    }

}
