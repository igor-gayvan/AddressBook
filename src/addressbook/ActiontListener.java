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
public interface ActiontListener {

    public void exitAction();

    public void addContactAction();

    public void showListContactsAction();

    public void showContactAction();

    public void showPromptInputContactAction();

    public void showPromptInputContactIdAction();

    public void refreshDataAction();

    public void sortByPhoneAction();

    public void sortByAnyFieldAction();

    public void sortByAnyField();
}
