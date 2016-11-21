package contactObject;

import java.io.Serializable;
import java.util.UUID;

public class Contact implements Serializable {
    private String firstName;
    private String lastName;
    private String eMail;
    private UUID uuid;

    /**
     * When the Constructor is called each variable is initiated to
     * the strings supplied by the user, except for uuid.
     * uuid is generated when a new contact is created, with a random uuid.
     *
     * @param firstName - Rather
     * @param lastName  - self
     * @param eMail     - explanatory
     */
    public Contact(String firstName, String lastName, String eMail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.uuid = UUID.randomUUID();
    }

    /**
     * XFN-1: Nicer looking format of contact when printed
     * Each variable is printed on a separate line, the UUID number is split in to two lines with substring.
     *
     * @return formattedToString
     */
    @Override
    public String toString() {
        return String.format("Contact UUID: %s\n  First name: %s\n   Last name: %s\n\t  E-mail: %s\n",
                uuid, firstName, lastName, eMail);
    }

    // Getters necessary for search
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
