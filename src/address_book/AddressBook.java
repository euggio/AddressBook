package address_book;

import static address_book.AddressBookManager.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ADDRESS BOOK CLASS
 * This address book class holds French contacts and adds, finds, modifies, and 
 * also removes contacts.
 * It allows user to add same contact as many times as s/he wishes to
 * @author euggio
 */
final class AddressBook {
    /**
     * Constructor initializing the field value
     * Adding contact can be only done via this constructor
     * @param contact the contact to be added 
     */
    AddressBook (Contact contact) {
        addressBook.add(contact);
    }
    
    // Initialization block
    static 
    {
        printWelcome();
        try {
            openAddressBook();
        } catch (IOException ex) {
            Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, 
            null, ex);
        }
    }
    
    /**
     * Opening the address book
     * This method allows for adding contacts, searching for contacts, 
     * removing contacts, and displaying the address book
     * @throws IOException
     */
    static void openAddressBook() throws IOException {
        printSelectUpToFour();
         
        switch (restrictUpToFour()) {
            case 1:
                addContact();
                continueAddressBook();
                break;
            case 2:
                findContact(promptLastName());
                continueAddressBook();
                break;
            case 3:
                removeContact(promptLastName());
                continueAddressBook();
                break;
            case 4:
                displayContact();
                break;
        }
    }
    
    /**
     * Adding contacts starting with name
     * @throws IOException
     */
    private static void addContact() throws IOException {
        printSelectUpToTwo();
        printEnterFirstDetails();
        
        AddressBook acquaintance;
        AddressBook family;
        AddressBook friend;
        
        String[] mandatoryDetails;
        String[] commonDetails;
        String mobilePhone;
        String birthdate;
        
        switch (restrictUpToTwo()) {
            case 1:
                mandatoryDetails = mandatoryDetails();
                printSelectContactType();
                
                switch (restrictUpToThree()) {
                    case 1:                        
                        acquaintance = new AddressBook(new Acquaintance(
                            mandatoryDetails[0], mandatoryDetails[1], "", "", 
                            "", "", "", "", "", mandatoryDetails[2]));
                        printAcquaintanceAdded();
                        break;

                    case 2:                        
                        family = new AddressBook(new Family(mandatoryDetails[0], 
                            mandatoryDetails[1], "", "", "", "", "", "", "", "",
                            mandatoryDetails[2], ""));
                        printFamilyAdded();
                        break;
                    case 3:                        
                        friend = new AddressBook(new Friend(mandatoryDetails[0], 
                            mandatoryDetails[1], "", "", "", "", "", "", "", 
                            mandatoryDetails[2], ""));
                        printFriendAdded();
                        break;
                }       
                break;
            case 2:
                mandatoryDetails = mandatoryDetails();
                commonDetails = commonDetails();
                printSelectContactType();
                switch (restrictUpToThree()) {
                    case 1:
                        acquaintance = new AddressBook(new Acquaintance(
                            mandatoryDetails[0], mandatoryDetails[1], 
                            commonDetails[0], commonDetails[1], 
                            commonDetails[2], commonDetails[3], 
                            commonDetails[4], commonDetails[5], 
                            commonDetails[6], mandatoryDetails[2]));
                        printAcquaintanceAdded();
                        break;
                    case 2:
                        birthdate = controlBirthdate(promptBirthDate());
                        mobilePhone = controlMobilePhone(promptMobilePhone());
                        family = new AddressBook(new Family(mandatoryDetails[0],
                            mandatoryDetails[1], birthdate, commonDetails[0], 
                            commonDetails[1], commonDetails[2], 
                            commonDetails[3], commonDetails[4], 
                            commonDetails[5], commonDetails[6], 
                            mandatoryDetails[2], mobilePhone));
                        printFamilyAdded();
                        break;
                    case 3:
                        mobilePhone = controlMobilePhone(promptMobilePhone());
                        friend = new AddressBook(new Friend(mandatoryDetails[0], 
                            mandatoryDetails[1], commonDetails[0], 
                            commonDetails[1], commonDetails[2], 
                            commonDetails[3], commonDetails[4], 
                            commonDetails[5], commonDetails[6], 
                            mandatoryDetails[2], mobilePhone));
                        printFriendAdded();
                        break;
                }
                break;       
        }        
    }
    
    /**
     * Searching for contacts
     * @param lastName, the last name entered for search by user
     * @throws java.io.IOException
     */
    private static void findContact(String lastName) throws IOException
    {
        int count = 0;
        int index = 0;
        String id = "";
        ArrayList<Integer> indexes = new ArrayList<>();
        printSearchResult();
        
        // Displaying contacts, retrieving index and counting duplicates
        for (int i = 0; i < addressBook.size(); i++) { 
            if (lastName.equals(addressBook.get(i).getLastName())) { 
                System.out.println(addressBook.get(i).toString());
                index = i;
                indexes.add(i);
                count++;
            }
        }
        
        // Actions taken when contact is not found and when duplicates are found 
        if (count == 0) {
            printContactNotFound();
            openAddressBook();
        }
        else if (count > 1) {
            promptID(); // Asking for ID when duplicates are found
            // Checking whether ID is contained in arraylist indexes
            id = controlID(promptID(), indexes); 
            // Retrieving index from ID
            for(int i = 0; i < addressBook.size(); i++) { 
                if (id.equals(addressBook.get(i).getId())) { 
                    index = i;
                }
            }
        }
              
        // Modify details based on type
        printModifyDetails();
        if ("Acquaintance".equals(addressBook.get(index).getClass().
            getSimpleName())) {
            modifyAcquaintance();
            switch(restrictUpToTen()) {
                case 1:
                    modifyFirstName(index);
                    break;
                case 2: 
                    modifyLastName(index);
                    break;
                case 3:
                    modifyStreetNumber(index);
                    break;
                case 4:
                    modifyStreetNumberSuffix(index);
                    break;
                case 5:
                    modifyStreetName(index);
                    break;
                case 6:
                    modifyStreetNameSuffix(index);
                    break;
                case 7:
                    modifyPostalCode(index);
                    break;
                case 8:
                    modifyCity(index);
                    break;
                case 9:
                    modifyEmail(index);
                    break;
                case 10:
                    modifyLandlinePhone(index);
                    break;
            }
        } else if ("Family".equals(AddressBook.getAddressBook().get(index).
            getClass().getSimpleName())) {
            modifyFamily();
            switch (restrictUpToTwelve()) {
                case 1:
                    modifyFirstName(index);
                    break;
                case 2: 
                    modifyLastName(index);
                    break;
                case 3:
                    modifyBirthDate(index);
                    break;
                case 4:
                    modifyStreetNumber(index);
                    break;
                case 5:
                    modifyStreetNumberSuffix(index);
                    break;
                case 6:
                    modifyStreetName(index);
                    break;
                case 7:
                    modifyStreetNameSuffix(index);
                    break;
                case 8:
                    modifyPostalCode(index);
                    break;
                case 9:
                    modifyCity(index);
                    break;
                case 10:
                    modifyEmail(index);
                    break;
                case 11:
                    modifyLandlinePhone(index);
                    break;
                case 12:
                    modifyMobilePhone(index);
                    break;
            }
        } else {
            modifyFriend();
            switch(restrictUpToEleven()) {
                case 1:
                    modifyFirstName(index);
                    break;
                case 2: 
                    modifyLastName(index);
                    break;
                case 3:
                    modifyStreetNumber(index);
                    break;
                case 4:
                    modifyStreetNumberSuffix(index);
                    break;
                case 5:
                    modifyStreetName(index);
                    break;
                case 6:
                    modifyStreetNameSuffix(index);
                    break;
                case 7:
                    modifyPostalCode(index);
                    break;
                case 8:
                    modifyCity(index);
                    break;
                case 9:
                    modifyEmail(index);
                    break;
                case 10:
                    modifyLandlinePhone(index);
                    break;
                case 11:
                    modifyMobilePhone(index);
                    break;
            }
        }
    }
    
    /**
     * Removing contacts
     * @param lastNameEntered, the last name entered to be removed by user
     */
    private static void removeContact(String lastNameEntered){
        int index = -1;
        int i = 0;
        for(; i < getAddressBook().size(); i++){ 
            if(lastNameEntered.equals(getAddressBook().get(i).
                getLastName())) { 
                
                // Storing to-be-removed contact for database updating
                ArrayList<Contact> removed = new ArrayList<>();
                removed.add(getAddressBook().get(i));
                
                getAddressBook().remove(i);
                index = i;
            }
        }
        
        if (index == -1) printContactNotFound();
        else {
            if ("Acquaintance".equals(AddressBook.getAddressBook().get(i).
                getClass().getSimpleName())) 
                printAcquaintanceRemoved(i);
            else if ("Family".equals(AddressBook.getAddressBook().get(i).
                getClass().getSimpleName())) 
                printFamilyRemoved(i);
            else printFriendRemoved(i);
        }
    }
    
    // Options for keeping address book open 
    private static void continueAddressBook() throws IOException {
        printExitMessage();
        printSelectUpToTwo();
        printYesOrNo();
        
        switch (restrictUpToTwo()) {
            case 1:
                closeAddressBook();
                break;
            case 2:
                openAddressBook();
                break;
        }
    }
    
    private static void displayContact() throws IOException {
        printDisplayMethod();
        
        switch (restrictUpToThree()) {
            case 1:
                sortByLastName();
                break;
            case 2:
                sortByPostalCode();
                break;
            case 3:
                sortByContactType();
                break;
        }
    }
    
    // Exiting the address book
    private static void closeAddressBook() {
        System.exit(0);
    }
    
    // --------------------------- GETTER (1) ----------------------------------
    /**
     * Getting default address book's details
     * @return the address book details
     */
    static ArrayList<Contact> getAddressBook() {
        return addressBook;
    }
    
    // Checking whether one in two options is chosen
    private static int restrictUpToTwo() throws IOException {        
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit == true) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 2;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Checking whether one in three options is chosen
    private static int restrictUpToThree() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit == true) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 3;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Checking whether one in four options is chosen
    private static int restrictUpToFour() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit == true) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 4;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    private static int restrictUpToTen() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit == true) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 10;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    private static int restrictUpToEleven() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit == true) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 11;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    private static int restrictUpToTwelve() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit == true) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 12;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Prompting for mandatory details, that is, first name, last name, and 
    // landline phone number
    private static String[] mandatoryDetails() throws IOException {
        String firstName = promptFirstName();        
        String lastName = promptLastName();        
        String landlinePhone = promptLandlinePhone();        
        String[] mandatoryDetails = {firstName, lastName, landlinePhone};        
        return mandatoryDetails;
    }
    
    // Prompting for common and nonmandatory details, that is, street number, 
    // street number suffix, street name, street name suffix, postal code, city,
    // and email address
    private static String[] commonDetails() throws IOException {
        String streetNumber = "";
        String streetNumberSuffix = "";
        String streetName = "";
        String streetNameSuffix = "";
        String postalCode = "";
        String city = "";
        String email = "";
        
        printEnterAdditionalDetails();
        printEnterAddress();
        printSelectUpToTwo();
        printYesOrNo();   

        switch (restrictUpToTwo()) {
            case 1:
                streetNumber = promptStreetNumber();                 
                streetNumberSuffix = promptStreetNumberSuffix();                                
                streetName = promptStreetName();              
                streetNameSuffix = promptStreetNameSuffix();
                postalCode = promptPostalCode();                                               
                city = promptCity();
                break;
            case 2:
                // Empty string values
                break;
        }
        
        email = promptEmail();
        
        String[] commonDetails = {streetNumber, 
            streetNumberSuffix, streetName, 
            streetNameSuffix, postalCode, 
            city, email};
        
        return commonDetails;
    }
    
    static boolean containsDigitsOnly(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    
    static String controlMandatoryFirstName(String firstName) 
            throws IOException {
        // Empty string or null is not allow for this mandatory detail
        boolean incorrect = true;
        while (incorrect) {
            if ("".equals(firstName) || firstName == null) {
                printErrorMandatoryMessage();
                firstName = promptFirstName();
            } else incorrect = false;
        }
        return firstName;
    }
    
    static String controlMandatoryLastName(String lastName) 
            throws IOException {
        // Empty string or null is not allow for this mandatory detail
        boolean incorrect = true;
        while (incorrect) {
            if ("".equals(lastName) || lastName == null) {
                printErrorMandatoryMessage();
                lastName = promptLastName();
            } else incorrect = false;
        }
        return lastName;
    }
    
    static String controlMandatoryLandlinePhone(String landlinePhone) 
            throws IOException {
        // Empty string or null is not allow for this mandatory detail.
        // Setting landline phone number's first digit to be equal to 0, second 
        // digit to be equal to 1, ..., or 5, or 8, or 9, and length to be equal
        // to 10
        boolean incorrect = true;
        boolean condition;
        while (incorrect) {
            if ("".equals(landlinePhone) || landlinePhone == null) {
                printErrorMandatoryMessage();
                landlinePhone = promptFirstName();
            } else {
                incorrect = false;
                while (incorrect) {
                    condition = containsDigitsOnly(landlinePhone) == false && 
                    landlinePhone.length() != 10                       || 
                    !"0".equals(landlinePhone.substring(0, 1))         || 
                    !"1".equals(landlinePhone.substring(1, 2))         || 
                    !"2".equals(landlinePhone.substring(1, 2))         || 
                    !"3".equals(landlinePhone.substring(1, 2))         || 
                    !"4".equals(landlinePhone.substring(1, 2))         || 
                    !"5".equals(landlinePhone.substring(1, 2))         ||
                    !"8".equals(landlinePhone.substring(1, 2))         || 
                    !"9".equals(landlinePhone.substring(1, 2));
                    if (condition) {
                        printErrorMessage();
                        landlinePhone = promptLandlinePhone();
                    } else incorrect = false;
                }
            }
        }
        return landlinePhone;
    }
    
    static String controlMobilePhone(String mobilePhone) throws 
        IOException {       
        // Setting mobile phone number's first digit to be equal to 0, second
        // digit to be equal to 6 or 7, and length to be equal to 10 digits
        boolean incorrect = true;
        boolean condition; 
        if ("".equals(mobilePhone) || mobilePhone == null) mobilePhone =
            "Unknown";
        else {
            while (incorrect) {
                condition = containsDigitsOnly(mobilePhone) == false && 
                    mobilePhone.length() != 10                       || 
                    !"0".equals(mobilePhone.substring(0, 1))         || 
                    !"6".equals(mobilePhone.substring(1, 2))         || 
                    !"7".equals(mobilePhone.substring(1, 2));
                if (condition) {
                    printErrorMessage();
                    mobilePhone = promptMobilePhone();
                } else incorrect = false;
            }
        }
        return mobilePhone;
    }
    
    static String controlBirthdate(String birthdate) throws IOException {
        // Checking whether birthdate has the adequate format set in the 
        // isDateValid method
        boolean incorrect = true;
        if ("".equals(birthdate) || birthdate == null) birthdate = "Unknown";
        else {
            while (incorrect) {
                if (isDateValid(birthdate) == true) incorrect = false;
                else {
                    printErrorMessage();
                    birthdate = promptBirthDate();
                }
            }
        }
        return birthdate;
    }
    
    static String controlStreetNumber(String streetNumber) throws IOException {
        // Setting street number string to contain digits only
        boolean incorrect = true;
        if ("".equals(streetNumber) || streetNumber == null) streetNumber =
            "Unknown";
        else {
            while (incorrect) {            
                if (containsDigitsOnly(streetNumber) == false) { 
                    printErrorMessage();
                    streetNumber = promptStreetNumber();
                } else incorrect = false;
            }
        }
        return streetNumber;
    }
    
    static String controlPostalCode(String postalCode) throws IOException {
        // Setting postal code to contain 5 digits only
        boolean incorrect = true;
        if ("".equals(postalCode) || postalCode == null) postalCode = "Unknown";
        else {
            while (incorrect) {             
                if (containsDigitsOnly(postalCode) == false) {
                    printErrorMessage();  
                    postalCode = promptPostalCode();
                } else incorrect = false;
            }
        }
        return postalCode;
    }
    
    static String controlEmail(String email) throws IOException {
        // Setting controlEmail to be at least 6 characters long, to contain and
        // to not start with the @ character, and to end with [.][two- or three-
        // character string]
        boolean incorrect = true;
        boolean condition;
        if ("".equals(email) || email == null) email = "Unknown";
        else {
            while (incorrect) {
                condition = email.length() >= 6         && 
                    ((!"".equals(email.substring(0, 1)) ||
                    !"@".equals(email.substring(0, 1))) && 
                    email.contains("@")                 && 
                    (".".equals(email.substring(email.length() - 3, email.
                    length() - 2))                      || 
                    ".".equals(email.substring(email.length() - 4, email.
                    length() - 3))));
                if (!condition) {
                    printErrorMessage();
                    email = promptEmail();
                } else incorrect = false;
            }
        }            
        return email;
    }
    
    static String controlID(String id, ArrayList<Integer> indexes) 
        throws IOException {
        // Setting ID to contain digits only among specific ID's
        boolean incorrect = true;
        boolean condition;
        condition = indexes.contains(id) && (!"".equals(id) || id != null || 
            containsDigitsOnly(id) == true);

            while (incorrect) {            
                if (!condition) { 
                    printErrorMessage();
                    id = promptID();
                } else incorrect = false;
            }
        return id;
    }
    static boolean isDateValid(String dateEntered) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd mm "
                + "yyyy");
            LocalDate date = LocalDate.parse(dateEntered, formatter);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }
    
    static String lastNameLength() {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getLastName().length() > max) 
                max = addressBook.get(i).getLastName().length();
        }
        
        if (max <= "Last Name".length()) return "%-" + "Last Name".length() + 
            "s";
        else return "%-" + max + "s";
    }
    
    static String firstNameLength() {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getFirstName().length() > max) 
                max = addressBook.get(i).getFirstName().length();
        }
        
        if (max <= "First Name".length()) return "%-" + "First Name".length() + 
            "s";
        else return "%-" + max + "s";
    }
    
    static String idLength() {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getId() > max) 
                max = Integer.toString(addressBook.get(i).getId()).length();
        }
        
        if (max <= "ID".length()) return "%-" + "ID".length() + "s"; 
        else return "%-" + max + "s";
    }
    
    private static void sortByLastName(){
        Collections.sort(addressBook, new SortByLastName());
        printSortByLastName();
        printHeader();
        for (int i=0; i < addressBook.size(); i++) 
            printContact(i);
        
        printQuantity(); 
    }
    
    private static void sortByPostalCode(){
        Collections.sort(addressBook, new SortByPostalCode());
        printSortByPostalCode();
        printHeader();
        for (int i=0; i < addressBook.size(); i++) 
            printContact(i);
        
        printQuantity();
    }
    
    private static void sortByContactType(){
        Collections.sort(addressBook, new SortByContactType());
        printSortByContactType();
        printHeader();
        for (int i=0; i < addressBook.size(); i++) 
            printContact(i);
        
        printQuantity();
    }
    
    private static class SortByLastName implements Comparator<Contact> {
        /**
        * Comparing two Contacts by last name
        * @param contact_1 the first contact in comparison
        * @param contact_2 the second contact in comparison
        * @return the integer resulted from comparison
        */
        @Override
        public int compare(Contact contact_1, Contact contact_2) {
            return contact_1.getLastName().compareTo(contact_2.getLastName());
        }
    }
    
    private static class SortByPostalCode implements Comparator<Contact> {
        /**
        * Comparing two Contacts by postal code
        * @param contact_1 the first contact in comparison
        * @param contact_2 the second contact in comparison
        * @return the integer resulted from comparison
        */
        @Override
        public int compare(Contact contact_1, Contact contact_2) {
            return contact_1.getPostalCode().compareTo(contact_2.
                getPostalCode());
        }
    }
    
    private static class SortByContactType implements Comparator<Contact> {
        /**
        * Comparing two Contacts by contact type
        * @param contact_1 the first contact in comparison
        * @param contact_2 the second contact in comparison
        * @return the integer resulted from comparison
        */
        @Override
        public int compare(Contact contact_1, Contact contact_2) {
            return contact_1.getClass().getSimpleName().compareTo(contact_2.
                getClass().getSimpleName());
        }
    }
            
    // Address Book Instance Field
    private static final ArrayList<Contact> addressBook = new ArrayList<>();
}
