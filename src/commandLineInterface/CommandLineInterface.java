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
        switch (inputArray[0].toLowerCase()) {
            case "add":
                reg.addContactToList(inputArray[1], inputArray[2], inputArray[3]);
                break;
            case "list":
                reg.printContactList();
                break;
            case "search":
                reg.searchContact(inputArray[1]);
                break;
            case "quit":
                flipSwitch();
                break;
        }
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
