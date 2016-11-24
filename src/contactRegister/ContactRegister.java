package contactRegister;

import contactListFileHandler.ContactListFileHandler;
import contactObject.Contact;

import java.util.List;

public class ContactRegister {
    private ContactListFileHandler clf = new ContactListFileHandler();
    private List<Contact> contactList;

    /**
     * The constructor initiates contactList through a method in
     * ContactListFileHandler, that either returns a previously
     * saved list if such a list exists or a new ArrayList<>() if not.
     */
    public ContactRegister() {
        this.contactList = clf.loadListFromFile();
    }

    /**
     * FN-2: Add new Contact to register
     * <p>
     * The method receives the three strings needed to create a new Contact,
     * each string is added in the order of Contact constructor requires
     * them to be received.
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
     * Using the forEach() method in the ArrayList class the content in the list is printed to the console.
     * A regular for or for-each loop would do the same thing, but with more code.
     */
    public void printContactList() {
        contactList.forEach(System.out::println);
    }

    /**
     * FN-4: Search for contacts in list
     * <p>
     * The method checks the first and last name (to lower case) of each Contact object in the list,
     * that starts with whatever the search query is, if either one or both strings matches the query
     * the matching Contact is printed to the commandline.
     * <p>
     * Somewhat more detailed method description:
     * A stream of the ArrayList is opened to access all containing Contacts, the first and last names (to lower case)
     * of each Contact are filtered through the startsWith() method to find names starting with the query string.
     * Every match is caught by the filter andprinted to the commandline, by using the forEach method
     * that is available in the Stream interface, that can iterate through lists.
     * <p>
     * The same result could be done with "if logic" in a for/forEach loop.
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

