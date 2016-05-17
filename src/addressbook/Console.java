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
    private List<ActiontListener> exitListeners;

    public Console(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
        this.exitListeners = new ArrayList<>();
    }

    public void addExitListener(ActiontListener exitListener) {
        exitListeners.add(exitListener);
    }

    public void working() {
        while (true) {
            String input = scanner.nextLine();

            switch (input.toLowerCase()) {
                case "exit":
                    for (ActiontListener exitListener : exitListeners) {
                        exitListener.exitAction();
                    }
                    break;
            }
        }
    }

}
