/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Igor Gayvan
 */
public class Contact {

    private long id;
    private String nameFull;
    private String phone;
    private String email;
    private String skype;

    private static final String FOLDER_FOR_CONTACT = "./data";

    private String currentInputField;

    public long getId() {
        return id;
    }

    public void setId(int id) {
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

    public Contact(String nameFull, String phone, String email, String skype) {
        this.nameFull = nameFull;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
    }

    public void showPromptInputContact() {
        if (currentInputField == null) {
            currentInputField = "nameFull";
            System.out.println("Input contact's data:");
        }

        switch (currentInputField) {
            case "nameFull":
                System.out.println("Input full name:");
                break;
            case "phone":
                System.out.println("Input phone:");
                break;
            case "email":
                System.out.println("Input email:");
                break;
            case "skype":
                System.out.println("Input skype:");
                break;
        }
    }

    public void inputContact(String currentFieldValue) {
        switch (currentInputField) {
            case "nameFull":
                nameFull = currentFieldValue;
                currentInputField = "phone";
                break;
            case "phone":
                phone = currentFieldValue;
                currentInputField = "email";
                break;
            case "email":
                email = currentFieldValue;
                currentInputField = "skype";
                break;
            case "skype":
                skype = currentFieldValue;
                currentInputField = null;
                saveContact();
                break;
        }
    }

    public void saveContact() {
        id = (new Date()).getTime();

        new File(FOLDER_FOR_CONTACT).mkdir();
        String fileName = FOLDER_FOR_CONTACT + "/" + String.valueOf(id);

        try (FileOutputStream fos = new FileOutputStream(fileName, false)) {
            fos.write(String.valueOf(this.id).getBytes());
            fos.write('\n');
            fos.write(this.nameFull.getBytes());
            fos.write('\n');
            fos.write(this.phone.getBytes());
            fos.write('\n');
            fos.write(this.email.getBytes());
            fos.write('\n');
            fos.write(this.skype.getBytes());
            fos.write('\n');
            fos.close();

            System.out.printf("Contact %s save!%n%n", id);
        } catch (IOException ex) {
            System.out.println("Невозможно создать файл контакта");
        }
    }

    public void showListContact() {

    }
}
