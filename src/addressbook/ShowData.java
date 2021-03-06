/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Igor Gayvan
 */
public class ShowData {

    public static void showListContact(List<Contact> contactList) {
        System.out.println("\nContact's list: ");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("|%14s|%20s|%20s|%20s|%20s|%n", "id", "nameFull", "phone", "email", "skype");
        System.out.println("---------------------------------------------------------------------------------------------------");
        for (Contact contact : contactList) {
            System.out.printf("|%14s|%-20s|%-20s|%-20s|%-20s|%n", contact.getId(), contact.getNameFull(), contact.getPhone(), contact.getEmail(), contact.getSkype());
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    public static void showListContact(List<Contact> contactList, String sortBy) {
        Contact.setCompareField(sortBy);

        Collections.sort(contactList);
        System.out.printf("\nSort contacts by %s (%s)", sortBy, Contact.getSortAsc() == 1 ? "ascending" : "descending");
        showListContact(contactList);
    }

    public void showContactInfo(List<Contact> contactList, String contactId) {
        Contact cn = new Contact(contactId);
        System.out.println(contactList.indexOf(cn));

        for (Contact contact : contactList) {
            if (contactId == null) {
                contact.showContact();
            } else if (contact.getId().equals(contactId)) {
                contact.showContact();
                break;
            }
        }
    }

    /**
     * Overloading for showing all contacts.
     *
     * @param contactList list of contacts that showing.
     */
    public void showContactInfo(List<Contact> contactList) {
        showContactInfo(contactList, null);
    }

    public static void showAddContact(DataSource ds, List<Contact> contactList, Contact contact, String currentFieldValue) {
        switch (contact.getCurrentInputField()) {
            case "nameFull":
                contact.setNameFull(currentFieldValue);
                contact.setCurrentInputField("phone");
                break;
            case "phone":
                contact.setPhone(currentFieldValue);
                contact.setCurrentInputField("email");
                break;
            case "email":
                contact.setEmail(currentFieldValue);
                contact.setCurrentInputField("skype");
                break;
            case "skype":
                contact.setSkype(currentFieldValue);
                contact.setCurrentInputField(null);
                ds.saveContact(contactList, contact);
                break;
        }
    }

    public void showPromptInputContactId() {
        System.out.print("\nInput contact's id: ");
    }

    public static void showPromptFieldForSort() {
        System.out.print("\nInput field for sort: ");
    }

}
