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
import java.util.List;

/**
 *
 * @author Igor Gayvan
 */
public class DataSource {

    private static final String FOLDER_FOR_CONTACT = "./data";

    public static void loadContactFromFiles(List<Contact> contactList) throws FileNotFoundException, IOException {
        System.out.println("Start read data from files...");
        contactList.clear();
        Contact contact = new Contact();
        File f = new File(FOLDER_FOR_CONTACT); // current directory

        File[] files = f.listFiles();
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);

            int i = 0;
            int cntLine = 0;
            String curLine = "";

            while (fis.available() > 0) {
                int read = fis.read();
                if (read == 10) {
                    switch (cntLine) {
                        case 0:
                            contact.setId(curLine);
                            break;
                        case 1:
                            contact.setNameFull(curLine);
                            break;
                        case 2:
                            contact.setPhone(curLine);
                            break;
                        case 3:
                            contact.setEmail(curLine);
                            break;
                        case 4:
                            contact.setSkype(curLine);
                            break;
                    }

                    curLine = "";
                    cntLine++;
                } else {
                    curLine = curLine + (char) read;
                }
            }

            fis.close();
            contactList.add(contact);
        }
        System.out.println("Finish read data from files\n");
    }

    public void saveContact(List<Contact> contactList, Contact contact) {
        contact.setId(String.valueOf((new Date()).getTime()));

        new File(FOLDER_FOR_CONTACT).mkdir();
        String fileName = FOLDER_FOR_CONTACT + "/" + contact.getId();

        try (FileOutputStream fos = new FileOutputStream(fileName, false)) {
            fos.write(contact.getId().getBytes());
            fos.write('\n');
            fos.write(contact.getNameFull().getBytes());
            fos.write('\n');
            fos.write(contact.getPhone().getBytes());
            fos.write('\n');
            fos.write(contact.getEmail().getBytes());
            fos.write('\n');
            fos.write(contact.getSkype().getBytes());
            fos.write('\n');
            fos.close();

            System.out.printf("Contact %s save!%n%n", contact.getId());
            contactList.add(contact);
        } catch (IOException ex) {
            System.err.println("Невозможно создать файл контакта");
        }
    }

}
