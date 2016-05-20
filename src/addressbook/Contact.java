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
public class Contact {

    private String id;
    private String nameFull;
    private String phone;
    private String email;
    private String skype;

    private String currentInputField;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameFull() {
        return nameFull;
    }

    public void setNameFull(String nameFull) {
        this.nameFull = nameFull;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getCurrentInputField() {
        return currentInputField;
    }

    public void setCurrentInputField(String currentInputField) {
        this.currentInputField = currentInputField;
    }

    public Contact() {

    }

    public Contact(String id) {
        this.id = id;
    }

    public Contact(String nameFull, String phone, String email, String skype) {
        this.nameFull = nameFull;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
    }

    public void showContact() {
        System.out.println("\nContact's info ");
        System.out.printf("ID: %s%n", this.id);
        System.out.printf("Full name: %s%n", this.nameFull);
        System.out.printf("Phone: %s%n", this.phone);
        System.out.printf("Email: %s%n", this.email);
        System.out.printf("Skype: %s%n", this.skype);
        System.out.println("");
    }

    public void showPromptInputContact() {
        if (currentInputField == null) {
            currentInputField = "nameFull";
            System.out.println("\nInput contact's data:");
        }

        switch (currentInputField) {
            case "nameFull":
                System.out.print("Input full name:");
                break;
            case "phone":
                System.out.print("Input phone:");
                break;
            case "email":
                System.out.print("Input email:");
                break;
            case "skype":
                System.out.print("Input skype:");
                break;
        }
    }
}
