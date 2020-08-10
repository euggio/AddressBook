/*
 * CDA AFPA 2020 B.E.E.
 * No Rights Reserved
 */
package address_book;

import static address_book.AddressBook.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ADDRESS BOOK MANAGER CLASS
 * The address book manager class holds console inputs in the address book's
 * stead 
 * @author euggio
 */
abstract class AddressBookManager {
// --------------------------------- PRINTERS ----------------------------------
    // Printing Welcome message
    static void printWelcome() {
        System.out.println(
            "================================================================\n"
          + "================================================================\n"
          + "========================= CARNET ===============================\n"
          + "============================== D'ADRESSE =======================\n"
          + "================================================================\n"
          + "================================================================");
    }
    
    // Printing two numbers to choose from
    static void printSelectUpToTwo() {
        System.out.println("SAISIR 1 OU 2 :");
    }
    
    // Printing four numbers and four options to choose from 
    static void printSelectUpToFour() {
        System.out.println("SAISIR 1, 2, 3 OU 4:");
        System.out.println("1. AJOUTER UN CONTACT  2. RECHERCHER UN CONTACT "
            + "3. SUPPRIMER UN CONTACT \n4. AFFICHER LE CARNET D'ADRESSE");
    }
    
    // Printing yes and no options
    static void printYesOrNo() {
        System.out.println("1. OUI  2. NON");
    }
    
    static void printHeader() {
        System.out.print(String.format("%-4s", "Type"));
        System.out.print(" " + String.format(idLength(), "ID"));
        System.out.print(" " + String.format(firstNameLength(), "First Name"));
        System.out.print(" " + String.format(lastNameLength(), "Last Name"));
        System.out.println(" " + String.format("%-5s", "PC"));
        System.out.print(" " + String.format("%-10s", "Landline"));
        System.out.print(" " + String.format("%-10s", "Mobile"));
    }
    
    // Printing two options to choose from, mandatory details only or all 
    // details
    static void printEnterFirstDetails() {
        System.out.println("1. AJOUTER SEULEMENT UN PRENOM, LE NOM ET LE "
            + "TELEPHONE FIXE\n  2. AJOUTER D'AUTRES INFORMATIONS EN PLUS");
    }
    
    // Printing two options to choose from, nonempty details or empty details 
    static void printEnterAdditionalDetails() {
        System.out.println("SAISIR LES INFORMATIONS UNE A UNE PUIS APPUYER SUR "
            + "\"ENTREE\" \n OU NE RIEN SAISIR ET APPUYER SUR \"ENTREE\"");
    }
    
    // Printing three numbers and contact type options to choose from 
    static void printSelectContactType() {
        System.out.println("SAISIR LE TYPE DE CONTACT, SOIT 1, 2, OU 3 :");
        System.out.println("1. CONNAISSANCE  2. FAMILLE.  3. AMI");
    }
    
    // Printing error message
    static void printErrorMessage() {
        System.err.println("SAISIE ERRONEE! REESSAYER.");
    }
    
    // Printing mandatory message error
    static void printErrorMandatoryMessage() {
        System.err.println("SAISIE OBLIGATOIRE!");
    }
    
    // Printing search result message
    static void printSearchResult() {
        System.out.println("RESULTAT DE LA RECHERCHE :");
    }
    
    // Printing contact not found message
    static void printContactNotFound() {
        System.out.println("CONTACT INTROUVABLE !");
    }
    
     static void printModifyDetails() {
        System.out.println("Do you want to modify the contact's details?");
    }
    
    // Printing success message on adding an acquaintance
    static void printAcquaintanceAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE AVEC SUCCES A LA FICHE \"CONNAISSANCE\"");
    }
    
    // Printing success message on adding a family
    static void printFamilyAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE AVEC SUCCES A LA FICHE \"FAMILLE\"");
    }
    
    // Printing success message on adding a friend
    static void printFriendAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE AVEC SUCCES A LA FICHE \"AMI\"");
    }
    
    static void printAcquaintanceRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()
            + " was successfully removed to Acquaintance!");
    }
    
    static void printFamilyRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()
            + " was successfully removed to Family!");
    }
    
    static void printFriendRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()
            + " was successfully removed to Friend!");
    }
    
    static void printExitMessage() {
        System.out.println("QUITTER LE CARNET D'ADRESSE ?");
    }
    
    static void printSortByLastName() {
        System.out.println("Sorted by last name:");
    }
    
    static void printSortByPostalCode() {
        System.out.println("Sorted by postal code:");
    }
    
    static void printSortByContactType() {
        System.out.println("Sorted by contact type:");
    }
    
    static void printContact(int index) {
        System.out.println(getAddressBook().get(index)); 
    }
    
    static void printQuantity() {
        System.out.println(getAddressBook().size() + " contacts.");
    }
    
    static void printDisplayMethod() {
        System.out.println("Select 1, 2, or 3");
        System.out.println("1. Sort by last name  2. Sort by postal code  \n"
            + "3. Sort by contact type");
    }
    
    // Prompting for ID
    static String promptID() throws IOException {
        System.out.println("SAISIR LE NUMERO D'ID :");
        return readString();
    }
    
    // Prompting for first name
    static String promptFirstName() throws IOException {
        System.out.println("SAISIR LE PRENOM :");
        return readString();
    }
    
    // Prompting for last name
    static String promptLastName() throws IOException {
        System.out.println("SAISIR LE NOM :");
        return readString();
    }
    
    // Prompting for birthdate
    static String promptBirthDate() throws IOException {
        System.out.println("SAISIR LA DATE DE NAISSANCE AU FORMAT \"dd mm "
            + "yyyy\" \n OU NE RIEN SAISIR AUTREMENT :");
        return readString();
    }
    
    // Printing address message
    static void printEnterAddress() {
        System.out.println("SAISIR L'ADRESSE :");
    }
    
    // Prompting for street number
    static String promptStreetNumber() throws IOException {
        System.out.println("SAISIR LE NUMERO DE RUE :");
        return readString();
    }
    
    // Prompting for street number suffix
    static String promptStreetNumberSuffix() throws IOException {
        System.out.println("SAISIR LE COMPLEMENT DU NUMERO DE RUE S'IL EXISTE :"
            + "");
        return readString();
    }
    
    // Prompting for street name
    static String promptStreetName() throws IOException {
        System.out.println("SAISIR LE NOM DE RUE :");
        return readString();
    }
    
    // Prompting for street name suffix
    static String promptStreetNameSuffix() throws IOException {
        System.out.println("SAISIR LE COMPLEMENT D'ADRESSE S'IL EXISTE :");
        return readString();
    } 
    
    // Prompting for postal code
    static String promptPostalCode() throws IOException {
        System.out.println("SAISIR LE CODE POSTAL :");
        return readString();
    }
    
    // Prompting for city name
    static String promptCity() throws IOException {
        System.out.println("SAISIR LA VILLE :");
        return readString();
    }
    
    static String promptEmail() throws IOException {
        System.out.println("SAISIR L'ADRESSE E-MAIL :");
        return readString();
    }
    
    // Prompting for landline phone number
    static String promptLandlinePhone() throws IOException {
        System.out.println("SAISIR LE NUMERO DE TELEPHONE FIXE :");
        return readString();
    }
    
    // Prompting for mobile phone number
    static String promptMobilePhone() throws IOException {
        System.out.println("SAISIR LE NUMERO DE TELEPHONE MOBILE :");
        return readString();
    }
    
    static void modifyAcquaintance() {
        System.out.println("Choose either 1, 2, ..., or 10:");
        System.out.println("1. Modify first name  2. Modify last name  \n"
              + "3. Modify street number  4. Modify street number suffix  \n"
              + "5. Modify street name  6. Modify street name suffix  \n"
              + "7. Modify postal code  8. Modify city  \n"
              + "9. Modify email  10. Modify landline phone number  \n");
    }
    
    static void modifyFriend() {
        System.out.println("Choose either 1, 2, ..., or 11:");
        System.out.println("1. Modify first name  2. Modify last name  \n"
              + "3. Modify street number  4. Modify street number suffix  \n"
              + "5. Modify street name  6. Modify street name suffix  \n"
              + "7. Modify postal code  8. Modify city  \n"
              + "9. Modify email  10. Modify landline phone number  \n"
              + "11. Modify mobile phone number");
    }
    
    static void modifyFamily() {
        System.out.println("Choose either 1, 2, ..., or 12:");
        System.out.println("1. Modify first name  2. Modify last name  \n"
            + "3. Modify birthdate  4. Modify street number  \n"
            + "5. Modify street number suffix  6. Modify street name  \n"
            + "7. Modify street name suffix  8. Modify postal code  \n"
            + "9. Modify city  10. Modify email  \n"
            + "11. Modify landline phone number  \n"
            + "12. Modify mobile phone number");
    }
    
    static void modifyFirstName(int index) throws IOException {
        System.out.println("Modify first name:");
        getAddressBook().get(index).setFirstName(readString());
    }
    
    static void modifyLastName(int index) throws IOException {
        System.out.println("Modify last name:");
        getAddressBook().get(index).setLastName(readString());
    }
    
    static void modifyBirthDate(int index) throws IOException {
        System.out.println("Modify birthdate:");
        Family family = (Family) getAddressBook().get(index);
        family.setBirthDate(readString());
    }
    
    static void modifyAddress() {
        System.out.println("Modify address:");
    }
    
    static void modifyStreetNumber(int index) throws IOException {
        System.out.println("Modify street number:");
        getAddressBook().get(index).setStreetNumber(readString());
    }
    
    static void modifyStreetNumberSuffix(int index) throws IOException {
        System.out.println("Modify street number suffix:");
        getAddressBook().get(index).setStreetNumberSuffix(
            readString());
    }
    
    static void modifyStreetName(int index) throws IOException {
        System.out.println("Modify street name:");
        getAddressBook().get(index).setStreetName(readString());
    }
    
    static void modifyStreetNameSuffix(int index) throws IOException {
        System.out.println("Modify street name suffix:");
        getAddressBook().get(index).setStreetNameSuffix(
            readString());
    } 
    
    static void modifyPostalCode(int index) throws IOException {
        System.out.println("Modify postal code:");
        getAddressBook().get(index).setPostalCode(readString());
    }
    
    static void modifyCity(int index) throws IOException {
        System.out.println("Modify city:");
        getAddressBook().get(index).setCity(readString());
    }
    
    static void modifyEmail(int index) throws IOException {
        System.out.println("Modify email address:");
        getAddressBook().get(index).setEmail(readString());
    }
    
    static void modifyLandlinePhone(int index) throws IOException {
        System.out.println("Modify landline phone number:");
        getAddressBook().get(index).setLandlinePhone(readString());
    }
    
    static void modifyFamilyMobile(int index) throws IOException {
        System.out.println("Modify mobile phone number:");
        Family family = (Family) getAddressBook().get(index);
        family.setMobilePhone(readString());
    }
    
    static void modifyFriendMobile(int index) throws IOException {
        System.out.println("Modify mobile phone number:");
        Friend friend = (Friend) getAddressBook().get(index);
        friend.setMobilePhone(readString());
    }
    
    static String readString() throws IOException {
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
         
        return bufferedReader.readLine().trim();
    }
}