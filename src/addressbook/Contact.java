/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

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

    public Contact() {

    }

    public Contact(String nameFull, String phone, String email, String skype) {
        this.nameFull = nameFull;
        this.phone = phone;
        this.email = email;
        this.skype = skype;
    }

    public void saveContact() throws FileNotFoundException, IOException {
        this.id = (new Date()).getTime();

        String fileName = "./data/" + String.valueOf(this.id);

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
        } catch (IOException ex) {
            System.out.println("Невозможно создать файл контакта");
        }
    }

    public static void inputContact() {
//        Contact cn = new Contact();

        System.out.println("Input full name:");
    }
}
