package address_book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * ADDRESS BOOK CLASS
 * Holds French contacts and adds, finds, modifies, and 
 * also removes contacts.
 * It allows user to add same contact as many times as s/he wishes to
 * @author euggio
 */
final class AddressBook {
    /*
     * Takes from MAIN an array of hard-coded default contacts, which are used 
     * by developer to populate his own database.
     * Also used to open address book.
     * Commented-out statement removes those default contacts and is also 
     * supposed to be uncommented when program is shared 
     */
    AddressBook(Contact... contacts) throws IOException {
        printWelcome();
        openAddressBook();
        addressBook.addAll(Arrays.asList(contacts));
        // addressBook.removeAll(Arrays.asList(contacts));
    }
    
    // Parameterless constructor
    AddressBook() {
    }   
        
    // Gets the address book's value
    ArrayList<Contact> getAddressBook() {
        return addressBook;
    }
    
    /*
     * Checks whether first digit is equal to 0, second digit to 6 or 7, length 
     * to 10, based on French mobile phone number format
     * Also puts in space every two digits
     */
    String checkMobilePhone(String mobilePhone) throws IOException {        
        if ("".equals(mobilePhone) || mobilePhone == null) return mobilePhone =
            "INCONNU";
        
        boolean incorrect = true;
        while (incorrect) {
            if (!mobilePhone.matches("^(0)[67](\\d){8}$")) {
                printErrorMessage();
                mobilePhone = promptMobilePhone();
            } else incorrect = false;
        }
        return spacePhone(mobilePhone);
    }
    
    // Checks whether birthdate is valid or unknown
    String checkBirthdate(String birthdate) throws IOException {
        boolean incorrect = true;
        if ("".equals(birthdate) || birthdate == null) birthdate = "INCONNUE";
        else {
            while (incorrect) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.
                        ofPattern("dd MM yyyy");
                    LocalDate.parse(birthdate, formatter);
                    incorrect = false;
                } catch (DateTimeParseException ex) {
                    ex.printStackTrace();
                    printErrorMessage();
                    birthdate = promptBirthDate();
                }
            }
        }
        return birthdate;
    }
    
    // Checks whether street number is valid or unknown and contains digits 
    // only
    String checkStreetNumber(String streetNumber) throws IOException {
        boolean incorrect = true;
        if ("".equals(streetNumber) || streetNumber == null) streetNumber =
            "INCONNU";
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
    
    // Checks whether postal code is valid or unknown and contains 5 digits 
    // only
    String checkPostalCode(String postalCode) throws IOException {
        boolean incorrect = true;
        if ("".equals(postalCode) || postalCode == null) postalCode = "INCONNU";
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
    
    // Returns valid or unknown email address 
    String checkEmail(String email) throws IOException {
        if ("".equals(email) || email == null) return "INCONNU";
          
        boolean incorrect = true;
        while (incorrect) {
            if (!email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+"
                + "[\\w]$")) {
                printErrorMessage();
                email = promptEmail();
            } else incorrect = false;
        }    
        return email;
    }
    
    // Retrieves first name's max length for formatting purposes
    String firstNameMaxLength() {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getFirstName().length() > max) 
                max = addressBook.get(i).getFirstName().length();
        }
        
        if (max <= "PRENOM".length()) return "%-" + "PRENOM".length() + 
            "s";
        else return "%-" + max + "s";
    }
    
    // Retrieves ID's max length for formatting purposes
    String idMaxLength() {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getId() > max) 
                max = Integer.toString(addressBook.get(i).getId()).length();
        }
        
        if (max <= "ID".length()) return "%-" + "ID".length() + "s"; 
        else return "%-" + max + "s";
    }
    
    // Retrieves last name's max length for formatting purposes
    String lastNameMaxLength() {
        int max = Integer.MIN_VALUE; 
        for (int i = 0; i < addressBook.size(); i++) {
            if (addressBook.get(i).getLastName().length() > max) 
                max = addressBook.get(i).getLastName().length();
        }
        
        if (max <= "NOM".length()) return "%-" + "NOM".length() + 
            "s";
        else return "%-" + max + "s";
    }
    
    /*
     * Sets first name to being nonempty and nonnull.
     * Also sets first letter to uppercase
     */
    String setFirstNameAsMandatory(String firstName) throws IOException {
        boolean incorrect = true;
        while (incorrect) {
            if ("".equals(firstName) || firstName == null) {
                printMandatoryInputErrorMessage();
                firstName = promptFirstName();
            } else incorrect = false;
        }
        return firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
    }
    
    /*
     * Sets landline phone number to being nonempty and nonnull.
     * Sets first digit to being equal to 0, second digit to being equal to
     * 1, ..., or 5, or 9, and length to being equal to 10 based on French 
     * landline phone number format
     * Also puts in space every two digits
     */
    String setLandlinePhoneAsMandatory(String landlinePhone) 
            throws IOException {
        boolean incorrect = true;
        while (incorrect) {
            if ("".equals(landlinePhone) || landlinePhone == null) {
                printMandatoryInputErrorMessage();
                landlinePhone = promptLandlinePhone();
            } else {
                incorrect = false;
                while (incorrect) {                    
                    if (!landlinePhone.matches("^(0)[1-59](\\d){8}$")) {
                        printErrorMessage();
                        landlinePhone = promptLandlinePhone();
                    } else incorrect = false;
                }
            }
        }
        return spacePhone(landlinePhone);
    }
    
    /*
     * Sets last name to being nonempty and nonnull.
     * Also sets it to uppercase  
     */
    String setLastNameAsMandatory(String lastName) throws IOException {
        boolean incorrect = true;
        while (incorrect) {
            if ("".equals(lastName) || lastName == null) {
                printMandatoryInputErrorMessage();
                lastName = promptLastName();
            } else incorrect = false;
        }
        return lastName.toUpperCase();
    }
    
// ------------------------------- PRIVATES ------------------------------------
    
// --------------------- ADDRESS BOOK PRIMARY FUNCTIONS ------------------------    
    /*
     * Opens address book and allows for adding contacts, searching for 
     * contacts, removing contacts, and displaying address book
     */
    private void openAddressBook() throws IOException {
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
                continueAddressBook();
                break;
        }
    }
    
    // Adds contacts starting with last name
    private void addContact() throws IOException {
        printSelectUpToTwo();
        printEnterFirstDetails();
        
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
                        addressBook.add(new Acquaintance(mandatoryDetails[0], 
                            mandatoryDetails[1], "", "", "", "", "", "", "", 
                            mandatoryDetails[2]));
                        printAcquaintanceAdded();
                        break;

                    case 2:                        
                        addressBook.add(new Family(mandatoryDetails[0], 
                            mandatoryDetails[1], "", "", "", "", "", "", "", "",
                            mandatoryDetails[2], ""));
                        printFamilyAdded();
                        break;
                    case 3:                        
                        addressBook.add(new Friend(mandatoryDetails[0], 
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
                        addressBook.add(new Acquaintance(mandatoryDetails[0], 
                            mandatoryDetails[1], commonDetails[0], 
                            commonDetails[1], commonDetails[2], 
                            commonDetails[3], commonDetails[4], 
                            commonDetails[5], commonDetails[6], 
                            mandatoryDetails[2]));
                        printAcquaintanceAdded();
                        break;
                    case 2:
                        birthdate = checkBirthdate(promptBirthDate());
                        mobilePhone = checkMobilePhone(promptMobilePhone());
                        addressBook.add(new Family(mandatoryDetails[0],
                            mandatoryDetails[1], birthdate, commonDetails[0], 
                            commonDetails[1], commonDetails[2], 
                            commonDetails[3], commonDetails[4], 
                            commonDetails[5], commonDetails[6], 
                            mandatoryDetails[2], mobilePhone));
                        printFamilyAdded();
                        break;
                    case 3:
                        mobilePhone = checkMobilePhone(promptMobilePhone());
                        addressBook.add(new Friend(mandatoryDetails[0], 
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
    
    // Closes address book
    private void closeAddressBook() {
        System.exit(0);
    }
    
    // Keeps address book open 
    private void continueAddressBook() throws IOException {
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
    
    // Displays contacts' details or address book
    private void displayContact() throws IOException {
        printSortOptions();
        
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
    
    // Searches for contacts
    private void findContact(String lastName) throws IOException {
        int count = 0;
        int index = 0;
        String id = "";
        ArrayList<Integer> indexes = new ArrayList<>();
        printSearchResult();
        
        // Retrieves index and counting duplicates
        for (int i = 0; i < addressBook.size(); i++) { 
            if (lastName.equalsIgnoreCase(addressBook.get(i).getLastName())) {
                index = i;
                indexes.add(i);
                count++;
            }
        }
        
        // Actions taken when contact is not found and when duplicates are found
        switch (count) {
            case 0:
                printContactNotFound();
                openAddressBook();
                break;
            case 1:
                printHeader();
                System.out.println(addressBook.get(indexes.size()).toString());
                break;
            default:
                printHeader();
                for (int i : indexes)
                    System.out.println(addressBook.get(i).toString());
                // Asks for ID when duplicates are found
                // Checks whether ID is contained in arraylist indexes
                id = controlID(promptID(), indexes);
                // Retrieves index from ID
                for(int i = 0; i < addressBook.size(); i++) {
                    if (id.equals(addressBook.get(i).getId())) {
                        index = i;
                    }
                }   break;
        }
              
        // Modifies details based on type
        printModifyDetails();
        if ("Acquaintance".equals(addressBook.get(index).getClass().
            getSimpleName())) {
            printModifyAcquaintance();
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
        } else if ("Family".equals(addressBook.get(index).getClass().
            getSimpleName())) {
            printModifyFamily();
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
            printModifyFriend();
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
    
    // Removes contacts
    private void removeContact(String lastNameEntered){
        int index = -1;
        int i = 0;
        for(; i < addressBook.size(); i++){ 
            if(lastNameEntered.equals(addressBook.get(i).
                getLastName())) { 
                
                // Stores to-be-removed contact for database updating
                ArrayList<Contact> removed = new ArrayList<>();
                removed.add(addressBook.get(i));
                
                addressBook.remove(i);
                index = i;
            }
        }
        
        if (index == -1) printContactNotFound();
        else {
            if ("Acquaintance".equals(addressBook.get(i).
                getClass().getSimpleName())) 
                printAcquaintanceRemoved(i);
            else if ("Family".equals(addressBook.get(i).
                getClass().getSimpleName())) 
                printFamilyRemoved(i);
            else printFriendRemoved(i);
        }
    }

// ------------------------------- HELPERS -------------------------------------    
    /*
     * Prompts for common and nonmandatory details, that is, street number, 
     * street number suffix, street name, street name suffix, postal code, city,
     * and email address
     */
    private String[] commonDetails() throws IOException {
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
    
    // Checks whether objects contains digits only
    private boolean containsDigitsOnly(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    // Checks whether ID chosen by user is among those in the search result
    private String controlID(String id, ArrayList<Integer> indexes) 
        throws IOException {
        // Setting ID to contain digits only among specific ID's
        boolean incorrect = true;
        boolean condition;
        condition = indexes.contains(id) && (!"".equals(id) || id != null || 
            containsDigitsOnly(id));

            while (incorrect) {            
                if (!condition) { 
                    printErrorMessage();
                    id = promptID();
                } else incorrect = false;
            }
        return id;
    }
    
    // Prompts for mandatory details, that is, first name, last name, and 
    // landline phone number
    private String[] mandatoryDetails() throws IOException {
        String firstName = promptFirstName();        
        String lastName = promptLastName();        
        String landlinePhone = promptLandlinePhone();        
        String[] mandatoryDetails = {firstName, lastName, landlinePhone};        
        return mandatoryDetails;
    }
    
    // Checks whether one in eleven options is chosen
    private int restrictUpToEleven() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 11;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Checks whether one in four options is chosen
    private int restrictUpToFour() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 4;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Checks whether one in ten options is chosen
    private int restrictUpToTen() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 10;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
   
    // Checks whether one in three options is chosen
    private int restrictUpToThree() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 3;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Checks whether one in twelve options is chosen
    private int restrictUpToTwelve() throws IOException {
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 12;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Checks whether one in two options is chosen
    private int restrictUpToTwo() throws IOException {        
        String readString;
        int readInt = 0;
        boolean condition;
        boolean incorrect = true;        
        while (incorrect) {
            readString = readString();
            boolean isDigit = containsDigitsOnly(readString);
            if (isDigit) {
                readInt = Integer.parseInt(readString);
                condition = readInt < 1 && readInt > 2;
                if (condition) printErrorMessage();
                else incorrect = false;
            } else printErrorMessage();
        }   
        return readInt;
    }
    
    // Sorts by contact type
    private void sortByContactType(){
        Collections.sort(addressBook, (contact_1, contact_2) -> {
            // Converts English type names to French type initials
            // C for Connaissance (Acquaintance), F for Famille (Family), and A
            // for Ami (Friend)
            String type_1;
            switch (contact_1.getClass().getSimpleName()) {
                case "Acquaintance":
                    type_1 = "C";
                    break;
                case "Family":
                    type_1 = "F";
                    break;
                default:
                    type_1 = "A";
                    break;
            }
            
            String type_2;
            switch (contact_2.getClass().getSimpleName()) {
                case "Acquaintance":
                    type_2 = "C";
                    break;
                case "Family":
                    type_2 = "F";
                    break;
                default:
                    type_2 = "A";
                    break;
            }
            
            int compared = type_1.compareTo(type_2);
            if (compared == 0) compared = Integer.compare(contact_1.getId(),
                    contact_2.getId());
            
            return compared;
        });
        printSortByContactType();
        printHeader();
        for (int i=0; i < addressBook.size(); i++) 
            printContact(i);
        
        printSize();
    }
    
    // Sorts by last name
    private void sortByLastName(){
        Collections.sort(addressBook, (contact_1, contact_2) -> 
        {
            int compared = contact_1.getLastName().compareTo(contact_2.
                getLastName());
            if (compared == 0) compared = Integer.compare(contact_1.getId(),
                    contact_2.getId());
            
            return compared;
        });
        printSortByLastName();
        printHeader();
        for (int i = 0; i < addressBook.size(); i++) printContact(i);
        
        printSize(); 
    }
    
    // Sorts by postal code
    private void sortByPostalCode(){
        Collections.sort(addressBook, (contact_1, contact_2) ->
        {
            int compared = contact_1.getPostalCode().compareTo(contact_2.
                getPostalCode());
            if (compared == 0) compared = Integer.compare(contact_1.getId(),
                    contact_2.getId());
            
            return compared;
        });
        printSortByPostalCode();
        printHeader();
        for (int i=0; i < addressBook.size(); i++) 
            printContact(i);
        
        printSize();
    }
    
    // Puts space after every two digits in a phone number
    private String spacePhone(String phone) { 
        StringBuilder phoneEntered = new StringBuilder();
        for (int i = 2; i <= phone.length(); i += 2) {
            phoneEntered.append(phone.substring(i - 2, i - 1));
            phoneEntered.append(phone.substring(i - 1, i));
            phoneEntered.append(" ");
        }
        return phoneEntered.toString();
    }
    
    // ------------------------------ CONSOLE INPUTS -------------------------------
// ------------------------------ MODIFIERS (12) -------------------------------    
    // Modifies birthdate
    private void modifyBirthDate(int index) throws IOException {
        System.out.println("MODIFIER LA DATE DE NAISSANCE :");
        getAddressBook().get(index).setBirthdate(readString());
    }
    
    // Modifies city name
    private void modifyCity(int index) throws IOException {
        System.out.println("MODIFIER LA VILLE :");
        getAddressBook().get(index).setCity(readString());
    }
    
    // Modifies email address
    private void modifyEmail(int index) throws IOException {
        System.out.println("MODIFIER L'E-MAIL :");
        getAddressBook().get(index).setEmail(readString());
    }
    
    // Modifies first name
    private void modifyFirstName(int index) throws IOException {
        System.out.println("MODIFIER LE PRENOM :");
        getAddressBook().get(index).setFirstName(readString());
    }
    
    // Modifies landline phone number
    private void modifyLandlinePhone(int index) throws IOException {
        System.out.println("MODIFIER LE NUMERO DE TELEPHONE FIXE (SANS LES "
            + "ESPACES) :");
        getAddressBook().get(index).setLandlinePhone(readString());
    }
    
    // Modifies last name
    private void modifyLastName(int index) throws IOException {
        System.out.println("MODIFIER LE NOM :");
        getAddressBook().get(index).setLastName(readString());
    }
    
    // Modifies mobile phone number
    private void modifyMobilePhone(int index) throws IOException {
        System.out.println("MODIFIER LE NUMERO DE TELEPHONE MOBILE (SANS LES "
            + "ESPACES) :");
        getAddressBook().get(index).setMobilePhone(readString());
    }
    
    // Modifies postal code
    private void modifyPostalCode(int index) throws IOException {
        System.out.println("MODIFIER LE CODE POSTAL :");
        getAddressBook().get(index).setPostalCode(readString());
    }
    
    // Modifies street number
    private void modifyStreetNumber(int index) throws IOException {
        System.out.println("MODIFIER LE NUMERO DE RUE :");
        getAddressBook().get(index).setStreetNumber(readString());
    }
    
    // Modifies street number suffix
    private void modifyStreetNumberSuffix(int index) throws IOException {
        System.out.println("MODIFIER LE COMPLEMENT DU NUMERO DE RUE :");
        getAddressBook().get(index).setStreetNumberSuffix(readString());
    }
    
    // Modifies street name
    private void modifyStreetName(int index) throws IOException {
        System.out.println("MODIFIER LE NOM DE RUE :");
        getAddressBook().get(index).setStreetName(readString());
    }
    
    // Modifies street name suffix
    private void modifyStreetNameSuffix(int index) throws IOException {
        System.out.println("MODIFIER LE COMPLEMENT DE RUE :");
        getAddressBook().get(index).setStreetNameSuffix(
            readString());
    } 

// ------------------------------- PRINTERS (31) -------------------------------
    // Prints success message after adding an acquaintance
    private void printAcquaintanceAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE.E AVEC SUCCES A LA FICHE \"CONNAISSANCE\"");
    }
    
    // Prints success message after removing an acquaintance
    private void printAcquaintanceRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName() + " A ETE "
            + "SUPPRIME.E AVEC SUCCES DE LA FICHE \"CONNAISSANCE\"");
    }
    
    // Prints contact details
    private void printContact(int index) {
        System.out.println(getAddressBook().get(index)); 
    }
    
    // Prints contact not found message
    private void printContactNotFound() {
        System.out.println("CONTACT INTROUVABLE !");
    }
    
    /*
     * Prints "enter 1, 2, or 3" and one of three options to choose from: 
     * sort by name, sort by postal code, or sort by contact type
     */
    private void printSortOptions() {
        System.out.println("SAISIR 1, 2, OU 3 :");
        System.out.println("1. TRIER PAR NOM  2. TRIER PAR CODE POSTAL\n"
            + "3. TRIER PAR TYPE DE CONTACT");
    }
    
    // Prints two options to choose from, nonempty details or empty details 
    private void printEnterAdditionalDetails() {
        System.out.println("SAISIR LES INFORMATIONS UNE A UNE PUIS APPUYER SUR "
            + "\"ENTREE\" \n OU NE RIEN SAISIR ET APPUYER SUR \"ENTREE\"");
    }
    
    // Prints "enter address"
    private void printEnterAddress() {
        System.out.println("SAISIR L'ADRESSE :");
    }
    
    /*
     * Prints one of two options to choose from: mandatory details only or all 
     * details
     */
    private void printEnterFirstDetails() {
        System.out.println("1. AJOUTER SEULEMENT UN PRENOM, UN NOM ET UN "
            + "TELEPHONE FIXE \n2. AJOUTER D'AUTRES INFORMATIONS EN PLUS");
    }
    
    // Prints "mandatory input" error message
    private void printMandatoryInputErrorMessage() {
        System.err.println("SAISIE OBLIGATOIRE !");
    }
    
    // Prints error message
    private void printErrorMessage() {
        System.err.println("SAISIE ERRONEE ! REESSAYER :");
    }
    
    // Prints "do you want to exit address book?"
    private void printExitMessage() {
        System.out.println("QUITTER LE CARNET D'ADRESSE ?");
    }
    
    // Prints success message after adding a family
    private void printFamilyAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE.E AVEC SUCCES A LA FICHE \"FAMILLE\"");
    }
    
    // Prints success message after removing a family member
    private void printFamilyRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()+ " A ETE "
            + "SUPPRIME.E AVEC SUCCES DE LA FICHE \"FAMILLE\"");
    }
    
    // Prints success message after adding a friend
    private void printFriendAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE.E AVEC SUCCES A LA FICHE \"AMI\"");
    }
    
    // Prints success message after removing a friend
    private void printFriendRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()+ " A ETE "
            + "SUPPRIME.E AVEC SUCCES DE LA FICHE \"AMI\"");
    }
    
    // Prints header on address book's display  
    private void printHeader() {
        System.out.print(String.format("%-4s", "Type"));
        System.out.print(" " + String.format(idMaxLength(), "ID"));
        System.out.print(" " + String.format(firstNameMaxLength(), "PRENOM"));
        System.out.print(" " + String.format(lastNameMaxLength(), "NOM"));
        System.out.print(" " + String.format("%-7s", "CP"));
        System.out.print(" " + String.format("%-14s", "FIXE"));
        System.out.println(" " + String.format("%-14s", " MOBILE"));
    }    
    
    // Prints Acquaintance details to modify
    private void printModifyAcquaintance() {
        System.out.println("SAISIR 1, 2, ..., OU 10 :");
        System.out.println("1. MODIFIER LE PRENOM  2. MODIFIER LE NOM\n"
            + "3. MODIFIER LE NUMERO DE RUE  4. MODIFIER LE COMPLEMENT DU "
            + "NUMERO DE RUE\n"
            + "5. MODIFIER LE NOM DE RUE  6. MODIFIER LE COMPLEMENT DE RUE\n"
            + "7. MODIFIER LE CODE POSTAL  8. MODIFIER LA VILLE\n"
            + "9. MODIFIER L'E-MAIL  10. MODIFIER LE NUMERO DE TELEPHONE FIXE"
            + "\n");
    }
    
    // Prints "modify address"
    private void printModifyAddress() {
        System.out.println("MODIFIER L'ADRESSE :");
    }
    
    // Prints "do you want to modify details?"
    private void printModifyDetails() {
        System.out.println("MODIFIER DES INFORMATIONS ?");
    }
    
    // Prints Family details to modify
    private void printModifyFamily() {
        System.out.println("SAISIR 1, 2, ..., OU 12 :");
        System.out.println("1. MODIFIER LE PRENOM  2. MODIFIER LE NOM\n"
            + "3. MODIFIER LA DATE DE NAISSANCE  4. MODIFIER LE NUMERO DE RUE\n"
            + "5. MODIFIER LE COMPLEMENT DU NUMERO DE RUE  6. MODIFIER LE NOM "
            + "DE RUE\n"
            + "7. MODIFIER LE COMPLEMENT DE RUE  8. MODIFIER LE CODE POSTAL\n"
            + "9. MODIFIER LA VILLE  10. MODIFIER L'E-MAIL\n"
            + "11. MODIFIER LE NUMERO DE TELEPHONE FIXE  12. MODIFIER LE NUMERO"
            + " DE TELEPHONE MOBILE");
    }
    
    // Prints Friend details to modify
    private void printModifyFriend() {
        System.out.println("SAISIR 1, 2, ..., OU 11 :");
        System.out.println("1. MODIFIER LE PRENOM  2. MODIFIER LE NOM\n"
            + "3. MODIFIER LE NUMERO DE RUE  4. MODIFIER LE COMPLEMENT DU "
            + "NUMERO DE RUE\n"
            + "5. MODIFIER LE NOM DE RUE  6. MODIFIER LE COMPLEMENT DE RUE\n"
            + "7. MODIFIER LE CODE POSTAL  8. MODIFIER LA VILLE\n"
            + "9. MODIFIER L'E-MAIL  10. MODIFIER LE NUMERO DE TELEPHONE FIXE\n"
            + "11. MODIFIER LE NUMERO DE TELEPHONE MOBILE");
    }
    
    // Prints search result message
    private void printSearchResult() {
        System.out.println("RESULTAT DE LA RECHERCHE :");
    }
    
    /*
     * Prints "enter contact type" and one of three options to choose from: 
     * acquaintance, family, or friend
     */
    private void printSelectContactType() {
        System.out.println("SAISIR LE TYPE DE CONTACT, SOIT 1, 2, OU 3 :");
        System.out.println("1. CONNAISSANCE  2. FAMILLE.  3. AMI");
    }
    
    /*
     * Prints "enter 1, ..., or 4" and one of four options to choose from:,
     * add contact, find contact, remove contact, or display address book
     */
    private void printSelectUpToFour() {
        System.out.println("SAISIR 1, 2, 3 OU 4 :");
        System.out.println("1. AJOUTER UN CONTACT  2. RECHERCHER UN CONTACT  "
            + "3. SUPPRIMER UN CONTACT \n4. AFFICHER LE CARNET D'ADRESSE");
    }
    
    // Prints "enter 1 or 2"
    private void printSelectUpToTwo() {
        System.out.println("SAISIR 1 OU 2 :");
    }
    
    // Prints address book size or number of contacts
    private void printSize() {
        System.out.println(getAddressBook().size() + " CONTACT.S.");
    }
    
    // Prints "sorted by contact type"
    private void printSortByContactType() {
        System.out.println("TRI PAR TYPE DE CONTACT :");
        System.out.println("A POUR AMI, C POUR CONNAISSANCE, ET F POUR "
            + "FAMILLE");
    }
    
    // Prints "sorted by last name"
    private void printSortByLastName() {
        System.out.println("TRI PAR NOM :");
    }
    
    // Prints "sorted by postal code"
    private void printSortByPostalCode() {
        System.out.println("TRI PAR CODE POSTAL :");
    }
    
    // Prints Welcome message
    private void printWelcome() {
        System.out.println(
          "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
        + "::::\n"
        + "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
        + "::::\n"
        + "::::::::::::::::::::::::::::::: CARNET :::::::::::::::::::::::::::::"
        + "::::\n"
        + ":::::::::::::::::::::::::::::::::::: D'ADRESSE :::::::::::::::::::::"
        + "::::\n"
        + "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
        + "::::\n"
        + "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"
        + "::::");
    }
    
    // Prints yes and no options
    private void printYesOrNo() {
        System.out.println("1. OUI  2. NON");
    }
    
// ----------------------------- PROMPTERS (13) --------------------------------
    // Prompts for birthdate
    private String promptBirthDate() throws IOException {
        System.out.println("SAISIR LA DATE DE NAISSANCE AU FORMAT \"dd mm "
            + "yyyy\" \nOU NE RIEN SAISIR AUTREMENT :");
        return readString();
    }
    
    // Prompts for city name
    private String promptCity() throws IOException {
        System.out.println("SAISIR LA VILLE :");
        return readString();
    }
    
    // Prompts for email address
    private String promptEmail() throws IOException {
        System.out.println("SAISIR L'ADRESSE E-MAIL :");
        return readString();
    }
    
    // Prompts for first name
    private String promptFirstName() throws IOException {
        System.out.println("SAISIR LE PRENOM :");
        return readString();
    }
    
    // Prompts for ID
    private String promptID() throws IOException {
        System.out.println("CONTACTS MULTIPLES. SAISIR UN DES NUMEROS D'ID "
            + "AFFICHES POUR PLUS DE PRECISION :");
        return readString();
    }
    
    // Prompts for landline phone number
    private String promptLandlinePhone() throws IOException {
        System.out.println("SAISIR LE NUMERO DE TELEPHONE FIXE (LES DIX "
            + "CHIFFRES SANS LES ESPACES) :");
        return readString();
    }
    
    // Prompts for last name
    private String promptLastName() throws IOException {
        System.out.println("SAISIR LE NOM :");
        return readString();
    }
    
    // Prompts for mobile phone number
    private String promptMobilePhone() throws IOException {
        System.out.println("SAISIR LE NUMERO DE TELEPHONE MOBILE (LES DIX "
            + "CHIFFRES SANS LES ESPACES) :");
        return readString();
    }
    
    // Prompts for postal code
    private String promptPostalCode() throws IOException {
        System.out.println("SAISIR LE CODE POSTAL :");
        return readString();
    }
    
    // Prompts for street name
    private String promptStreetName() throws IOException {
        System.out.println("SAISIR LE NOM DE RUE :");
        return readString();
    }
    
    // Prompts for street name suffix
    private String promptStreetNameSuffix() throws IOException {
        System.out.println("SAISIR LE COMPLEMENT D'ADRESSE S'IL EXISTE :");
        return readString();
    }
    
    // Prompts for street number
    private String promptStreetNumber() throws IOException {
        System.out.println("SAISIR LE NUMERO DE RUE :");
        return readString();
    }
    
    // Prompts for street number suffix
    private String promptStreetNumberSuffix() throws IOException {
        System.out.println("SAISIR LE COMPLEMENT DU NUMERO DE RUE S'IL EXISTE :"
            + "");
        return readString();
    }

// -------------------------------- READER (1)----------------------------------
    // Reads a string from standard input
    private String readString() throws IOException {
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));         
        return bufferedReader.readLine().trim();
    }
    
// ---------------------------- FIELD (1) --------------------------------------
    // Class Field
    private final ArrayList<Contact> addressBook = new ArrayList<>();
}