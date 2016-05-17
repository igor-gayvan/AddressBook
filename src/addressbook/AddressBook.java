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
        console.addExitListener(new ActiontListener() {
            @Override
            public void exitAction() {
                System.exit(0);
            }
        });
        console.working();
    }

}
