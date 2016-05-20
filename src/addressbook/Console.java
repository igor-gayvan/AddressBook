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

    private String modeWorking = "CHOICE_MODE";

    private String inputText;

    public Console(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);

        this.AddressBookListeners = new ArrayList<>();
    }

    public void addListener(ActiontListener ActiontListener) {
        AddressBookListeners.add(ActiontListener);
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getModeWorking() {
        return modeWorking;
    }

    /**
     *
     * @param modeWorking ADD_CONTACT - добавление, CHOICE_MODE - главное меню, SHOW_CONTACT - отображение данных о контакте
     *
     */
    public void setModeWorking(String modeWorking) {
        this.modeWorking = modeWorking;
    }

    public void working() {
        while (true) {
            if (modeWorking == "CHOICE_MODE") {
                System.out.println("1 - show list of contacts");
                System.out.println("2 - add new contact");
                System.out.println("3 - show information about contact");
                System.out.println("0 - exit");
                System.out.print("Your choice? ");
            }

            inputText = scanner.nextLine().trim();

            switch (modeWorking) {
                case "SHOW_CONTACT": {
                    for (ActiontListener addressBookListeners : AddressBookListeners) {
                        addressBookListeners.showContactAction();
                    }
                    break;
                }                
                case "ADD_CONTACT": {
                    for (ActiontListener addressBookListeners : AddressBookListeners) {
                        addressBookListeners.addContactAction();
                    }
                    break;
                }
                case "CHOICE_MODE":
                    switch (inputText.toLowerCase().trim()) {
                        case "0":
                            for (ActiontListener addressBookListeners : AddressBookListeners) {
                                addressBookListeners.exitAction();
                            }
                            break;
                        case "1":
                            for (ActiontListener addressBookListeners : AddressBookListeners) {
                                addressBookListeners.showListContactsAction();
                            }
                            break;
                        case "2":
                            setModeWorking("ADD_CONTACT");
                            for (ActiontListener addressBookListeners : AddressBookListeners) {
                                addressBookListeners.showPromptInputContactAction();
                            }
                            break;
                        case "3":
                            setModeWorking("SHOW_CONTACT");
                            for (ActiontListener addressBookListeners : AddressBookListeners) {
                                addressBookListeners.showPromptInputContactIdAction();
                            }
                            break;
                        default:
                            System.out.println("Make your choice");
                    }

            }
        }
    }

}
