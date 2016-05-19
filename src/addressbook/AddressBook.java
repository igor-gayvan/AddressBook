/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

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
                contact.showListContact();
            }

            @Override
            public void showContactAction() {
                System.exit(0);
            }
        });

        console.working();
    }

}
