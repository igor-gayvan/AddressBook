/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

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

    private static final String FOLDER_FOR_CONTACT = "./data";

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

    public Contact(String nameFull, String phone, String email, String skype) {
        this.nameFull = nameFull;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
    }

    public void showPromptInputContactId() {
        System.out.println("\nInput contact's id:");
    }

    public void showContact() {
        System.out.println("\nContact's info: ");
        System.out.printf("ID: %s%n", this.id);
        System.out.printf("Full name: %s%n", this.nameFull);
        System.out.printf("Phone: %s%n", this.phone);
        System.out.printf("Email: %s%n", this.email);
        System.out.printf("Skype: %s%n", this.skype);
        System.out.println("");
    }

    public void showContactFromFile(String contactFileName) throws FileNotFoundException, IOException {
        File f = new File(FOLDER_FOR_CONTACT); // current directory

        id = "";
        nameFull = "";
        skype = "";
        email = "";
        phone = "";

        File[] files = f.listFiles();
        for (File file : files) {
            if (file.getName().equals(contactFileName)) {
                FileInputStream fis = new FileInputStream(file);

                int i = 0;
                int cntLine = 0;

                while (fis.available() > 0) {
                    int read = fis.read();
                    if (read == 10) {
                        cntLine++;
                    } else {
                        switch (cntLine) {
                            case 0:
                                id = id + (char) read;
                                break;
                            case 1:
                                nameFull = nameFull + (char) read;
                                break;
                            case 2:
                                phone = phone + (char) read;
                                break;
                            case 3:
                                email = email + (char) read;
                                break;
                            case 4:
                                skype = skype + (char) read;
                                break;
                        }
                    }
                }
                fis.close();
                showContact();
            }
        }
    }

    public void showPromptInputContact() {
        if (currentInputField == null) {
            currentInputField = "nameFull";
            System.out.println("\nInput contact's data:");
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
        id = String.valueOf((new Date()).getTime());

        new File(FOLDER_FOR_CONTACT).mkdir();
        String fileName = FOLDER_FOR_CONTACT + "/" + id;

        try (FileOutputStream fos = new FileOutputStream(fileName, false)) {
            fos.write(this.id.getBytes());
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
            System.err.println("Невозможно создать файл контакта");
        }
    }

   
}
