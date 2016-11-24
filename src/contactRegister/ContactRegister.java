package contactRegister;

import contactListFileHandler.ContactListFileHandler;
import contactObject.Contact;

import java.util.ArrayList;
import java.util.Collections;
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
     * XFN-3: Sort contacts in alphabetical order
     * called each time switch is entered.
     */
    public void sortContactsByFirstName() {
        Collections.sort(contactList, (contact1, contact2) -> contact1.getFirstName().toLowerCase().compareTo(contact2.getFirstName().toLowerCase()));
    }

    /**
     * FN-2: Add new Contact to register
     */
    public void addContactToList(String[] inputArray) {
        try {
            contactList.add(new Contact(inputArray[1], inputArray[2], inputArray[3]));
            System.out.println(inputArray[1] + inputArray[2] +" was added to your contacts");
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getStackTrace();
            System.out.println("Could not add contact to list, ");
        }
    }

    /**
     * FN-3: List all contacts
     */
    public void printContactList() {
        System.out.println("Listing all contacts:");
        contactList.forEach(System.out::println);
        System.out.println("You have: " + contactList.size() + " contact/s saved");
    }

    /**
     * FN-4: Search for contacts in list
     */
    public void searchContact(String query) {
        List<Contact> tmpList = new ArrayList<>();
        contactList.stream().filter(contact -> contact.getFirstName().toLowerCase().startsWith(query) || contact.getLastName().toLowerCase().startsWith(query)
        ).forEach(tmpList::add);

        String message = tmpList.size() + " contact/s matched your search";
        System.out.println(message);
        if (!tmpList.isEmpty()) {
            tmpList.forEach(System.out::println);
            tmpList.clear();
        }
    }
    // deleted contact + get id
    public void removeContactFromList(String idStringToMatch){
        contactList.stream().filter(contact -> contact.getUUIDtoString().equals(idStringToMatch)).anyMatch(contact -> contactList.remove(contact));

    }

    /**
     * Part of XFN-2.1: (Save ContactList to file)
     * calls saveListToDisk in contactListFileHandler
     */
    public void saveContactList() {
        clf.saveListToDisk(contactList);
    }
}

