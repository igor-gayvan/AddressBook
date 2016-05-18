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

        console.addListener(new ActiontListener() {
            @Override
            public void exitAction() {
                System.exit(0);
            }

            @Override
            public void addContactAction() {
                System.exit(0);
            }

            @Override
            public void showListContacts() {
                System.exit(0);
            }

            @Override
            public void showContact() {
                System.exit(0);
            }
        });

        System.out.println("1 - show list of contacts");
        System.out.println("2 - add new contact");
        System.out.println("3 - show information about contact");
        System.out.println("0 - exit");
        System.out.print("Your choice? ");

        console.working();
    }

}
