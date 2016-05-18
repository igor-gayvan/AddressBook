/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Igor Gayvan
 */
public class Console {

    private Scanner scanner;

    private List<ActiontListener> AddressBookListeners;

    public Console(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);

        this.AddressBookListeners = new ArrayList<>();
    }

    public void addListener(ActiontListener ActiontListener) {
        AddressBookListeners.add(ActiontListener);
    }

    public void working() {
        while (true) {
            String input = scanner.nextLine();

            switch (input.toLowerCase().trim()) {
                case "0":
                    for (ActiontListener addressBookListeners : AddressBookListeners) {
                        addressBookListeners.exitAction();
                    }
                    break;
                case "1":
                    for (ActiontListener addressBookListeners : AddressBookListeners) {
                        addressBookListeners.showListContacts();
                    }
                    break;
                case "2":
                    for (ActiontListener addressBookListeners : AddressBookListeners) {
                        addressBookListeners.addContactAction();
                    }
                    break;
                case "3":
                    for (ActiontListener addressBookListeners : AddressBookListeners) {
                        addressBookListeners.showContact();
                    }
                    break;
                default:
                    System.out.println("Make your choice");
            }
        }
    }

}
