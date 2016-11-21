package contactRegister;

import contactListFileHandler.ContactListFileHandler;
import contactObject.Contact;

import java.util.ArrayList;

public class ContactRegister {
    private ContactListFileHandler clf = new ContactListFileHandler();
    private ArrayList<Contact> contactList;

    /**
     * The constructor initiates contactList through a method in
     * ContactListFileHandler, that either returns a previously
     * saved list or a new ArrayList<>()
     */
    public ContactRegister() {
        this.contactList = clf.loadListFromFile();
    }

    /**
     * FN-2: Add new Contact to register
     * <p>
     * The method receives the three strings needed to create a new Contact,
     * each string is added in the order of contact constructor requires
     *
     * @param firstName - FirstName
     * @param lastName  - LastName
     * @param eMail     - E-Mail address
     */
    public void addContactToList(String firstName, String lastName, String eMail) {
        contactList.add(new Contact(firstName, lastName, eMail));
    }

    /**
     * FN-3: List all contacts
     * <p>
     * Using the Iterable interface ".forEach()" the content in the list is printed to the console.
     * A regular for or for-each loop would do the same thing, but with more lines of code.
     */
    public void printContactList() {
        contactList.forEach(System.out::println);
    }

    /**
     * FN-4: Search for contacts in list
     * <p>
     * The method checks each Contact's: first and last name (to lower case)
     * that starts with "query", if either one or both strings matches the query
     * string that contact is printed to the commandline
     * <p>
     * Somewhat more detailed method description
     * <p>
     * The list is streamed to access all containing object, each Contact is filtered with
     * first or last-name.startsWith(query) logic to find matches,
     * Each match is caught by the filter then printed to the commandline, by using the forEach method
     * that is available in the Stream interface, that can iterate through lists.
     * <p>
     * The same result could be done with "if logic" in a for/forEach loop.
     * with more lines of code.
     *
     * @param query - string to match (provided by user)
     */
    public void searchContact(String query) {
        contactList.stream().filter(contact ->
                contact.getFirstName().toLowerCase().startsWith(query) ||
                contact.getLastName().toLowerCase().startsWith(query))
                .forEach(System.out::println);
    }

    /**
     * Part of XFN-2.1: (Save ContactList to file)
     * calls saveListToDisk in contactListFileHandler
     */
    public void saveContactList() {
        clf.saveListToDisk(contactList);
    }
}

