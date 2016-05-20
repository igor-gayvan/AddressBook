/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.util.List;

/**
 *
 * @author Igor Gayvan
 */
public class ShowData {

    public void showListContact(List<Contact> contactList) {
        System.out.println("\nContact's list: ");
        System.out.println("   Full name    ");
        for (Contact contact : contactList) {
            System.out.printf("%s%n", contact.getNameFull());
        }

        System.out.println();
    }

    public void showContactInfo(List<Contact> contactList, String contactId) {
        System.out.println(contactList.indexOf(contactId));

        for (Contact contact : contactList) {
            if (contactId == null || (contactId != null && contact.getId().equals(contactId))) {
                contact.showContact();
                break;
            }
        }
    }

    public static void inputContact(List<Contact> contactList, Contact contact, String currentFieldValue) {
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
                (new DataSource()).saveContact(contactList, contact);
                break;
        }
    }

}
