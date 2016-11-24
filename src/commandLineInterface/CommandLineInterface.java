package commandLineInterface;


import contactRegister.ContactRegister;

import java.util.Scanner;

public class CommandLineInterface {
    // Instance of ContactRegister
    private ContactRegister reg = new ContactRegister();
    // runCLI() will run while run equals true
    private boolean run = true;

    /**
     * The constructor calls runCLI when instantiated
     */
    public CommandLineInterface() {
        runCLI();
    }

    /**
     * The method prints a welcome message and then enters a while-loop
     * that repeats until the "run" boolean equals to false,
     * <p>
     * In the loop readUserInput() is called and that result is saved into a string,
     * which in turn is split at each "space" character. That array is sent into
     * the readInputCommand method.
     * <p>
     * After the while-loop is broken out of, the list is saved to a file
     * using the saveContactList method in ContactRegister, and the
     * application process finishes.
     */
    private void runCLI() {
        System.out.println("Welcome");
        String input;
        String[] inputSplit;
        while (run) {
            input = readUserInput();
            inputSplit = input.split(" ");
            readInputCommand(inputSplit);
        }
        reg.saveContactList();
        System.out.println("Good bye");
    }

    /**
     * FN-1 Read input commands:
     * WARNING! - method catches no exceptions and will crash if "case" input requirements are't met.
     * Element {0} decides the case(command) result of the switch tree.
     * Element {1} is used for: first name in new contact and the string to match in the search method.
     * Element {2 and 3} are sent as parameters for new contact (lastName and eMail)
     * <p>
     * quit: uses flipSwitch to flip the "run" boolean to break out of the while-loop in runCLI.
     */
    private void readInputCommand(String[] inputArray) {
        reg.sortContactsByFirstName();
        if (inputArray[0].toLowerCase().equals("end") || inputArray[0].toLowerCase().equals("exit")){
            inputArray[0] = "quit"; // Temporary exit commands
        }
        switch (inputArray[0].toLowerCase()) {
            case "add": // feedback done
                reg.addContactToList(inputArray);
                break;
            case "delete": // deleted contact + get id
                reg.removeContactFromList(inputArray[1]);
                break;
            case "list": // listing all existing contacts
                reg.printContactList();
                break;
            case "search": // feedback done
                reg.searchContact(inputArray[1]);
                break;
            case "help": // feedback done
                commandOptionList();
                break;
            case "quit": // feedback done
                flipSwitch();
                break;
            default:
                break;
        }
    }
    private void commandOptionList(){
        System.out.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n",
                "Listing all available input commands:",
                "Add:    add a new contact to list",
                "Delete: remove a contact from list",
                "List:   show all contacts in list",
                "Search: find contact/s in list ",
                "Quit:   exit the application",
                "Help:   to get here, lists all available commands");
    }

    /**
     * FN-5 Exit function:
     * The method flips the run boolean which is the
     * required action to exit the while-loop in runCLI();
     * I couldn't think of a better name.
     *
     * @return - !run
     */
    private boolean flipSwitch() {
        System.out.println("Shutting down application, terminating all active processes.");
        return run = !run;
    }

    /**
     * The method reads user input by using the Scanner class's
     * nextLine method and returns it as string
     *
     * @return - user input
     */
    private String readUserInput() {
        return new Scanner(System.in).nextLine();
    }
}
