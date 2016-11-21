package contactListFileHandler;

import contactObject.Contact;

import java.io.*;
import java.util.ArrayList;

public class ContactListFileHandler {
    // The contactList is stored to this file, created in the src directory.
    private File savedContacts = new File("src/savedContactList.data");

    /**
     * XFN-2.1: Save ContactList to file (serialization)
     * This method receives an ArrayList of Contact-objects.
     * If the list is empty: the method does noting.
     * If the list isn't empty: a FileOutputStream and ObjectOutputStream
     * is opened, and the entire ArrayList object is written to a file
     * called "ContactList.data"
     * <p>
     * TODO: Compare current list with contents of file, only overwrite if different
     *
     * @param listOfObjects Contact list to save in file
     */
    public void saveListToDisk(ArrayList<Contact> listOfObjects) {
        if (!listOfObjects.isEmpty()) {
            try (FileOutputStream fos = new FileOutputStream(savedContacts);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(listOfObjects);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * XFN-2.1 Load ContactList from file (deserialization)
     * The method is used by ContactRegister to initialize the contactList ArrayList,
     * with an existing list or a new empty list.
     * <p>
     * This method checks if the file "ContactList.data" exists.
     * If true:
     * A FileInputStream is opened and the containing object is read into ObjectInputStream,
     * the object is then cast to a ArrayList<Contact> and returned.
     * If false:
     * the method returns a new ArrayList,
     * Conditional return:
     *
     * @return existing ArrayList<contact> or new ArrayList
     * Suppresses warning "unchecked cast"
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Contact> loadListFromFile() {
        if (savedContacts.exists()) {
            try (FileInputStream is = new FileInputStream(savedContacts);
                 ObjectInputStream ois = new ObjectInputStream(is)) {
                return (ArrayList<Contact>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }
}
