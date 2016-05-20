/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Igor Gayvan
 */
public class DataSource {
    private static final String FOLDER_FOR_CONTACT = "./data";

    public static void loadContactFromFiles(List<Contact> contactList) throws FileNotFoundException, IOException {
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
    }

}
