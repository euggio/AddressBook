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
// ------------------------------ MODIFIERS (12) -------------------------------    
    // Modifying birthdate
    static void modifyBirthDate(int index) throws IOException {
        System.out.println("MODIFIER LA DATE DE NAISSANCE :");
        getAddressBook().get(index).setBirthdate(readString());
    }
    
    // Modifying city name
    static void modifyCity(int index) throws IOException {
        System.out.println("MODIFIER LA VILLE :");
        getAddressBook().get(index).setCity(readString());
    }
    
    // Modifying email address
    static void modifyEmail(int index) throws IOException {
        System.out.println("MODIFIER L'E-MAIL :");
        getAddressBook().get(index).setEmail(readString());
    }
    
    // Modifying first name
    static void modifyFirstName(int index) throws IOException {
        System.out.println("MODIFIER LE PRENOM :");
        getAddressBook().get(index).setFirstName(readString());
    }
    
    // Modifying landline phone number
    static void modifyLandlinePhone(int index) throws IOException {
        System.out.println("MODIFIER LE NUMERO DE TELEPHONE FIXE :");
        getAddressBook().get(index).setLandlinePhone(readString());
    }
    
    // Modifying last name
    static void modifyLastName(int index) throws IOException {
        System.out.println("MODIFIER LE NOM :");
        getAddressBook().get(index).setLastName(readString());
    }
    
    // Modifying mobile phone number
    static void modifyMobilePhone(int index) throws IOException {
        System.out.println("MODIFIER LE NUMERO DE TELEPHONE MOBILE :");
        getAddressBook().get(index).setMobilePhone(readString());
    }
    
    // Modifying postal code
    static void modifyPostalCode(int index) throws IOException {
        System.out.println("MODIFIER LE CODE POSTAL :");
        getAddressBook().get(index).setPostalCode(readString());
    }
    
    // Modifying street number
    static void modifyStreetNumber(int index) throws IOException {
        System.out.println("MODIFIER LE NUMERO DE RUE :");
        getAddressBook().get(index).setStreetNumber(readString());
    }
    
    // Modifying street number suffix
    static void modifyStreetNumberSuffix(int index) throws IOException {
        System.out.println("MODIFIER LE COMPLEMENT DU NUMERO DE RUE :");
        getAddressBook().get(index).setStreetNumberSuffix(readString());
    }
    
    // Modifying street name
    static void modifyStreetName(int index) throws IOException {
        System.out.println("MODIFIER LE NOM DE RUE :");
        getAddressBook().get(index).setStreetName(readString());
    }
    
    // Modifying street name suffix
    static void modifyStreetNameSuffix(int index) throws IOException {
        System.out.println("MODIFIER LE COMPLEMENT DE RUE :");
        getAddressBook().get(index).setStreetNameSuffix(
            readString());
    } 

// ------------------------------- PRINTERS (31) -------------------------------
    // Printing success message after adding an acquaintance
    static void printAcquaintanceAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE.E AVEC SUCCES A LA FICHE \"CONNAISSANCE\"");
    }
    
    // Printing success message after removing an acquaintance
    static void printAcquaintanceRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName() + " A ETE "
            + "SUPPRIME.E AVEC SUCCES DE LA FICHE \"CONNAISSANCE\"");
    }
    
    // Printing contact details
    static void printContact(int index) {
        System.out.println(getAddressBook().get(index)); 
    }
    
    // Printing contact not found message
    static void printContactNotFound() {
        System.out.println("CONTACT INTROUVABLE !");
    }
    
    // Printing "enter 1, 2, or 3" and one of three options to choose from: 
    // sort by name, sort by postal code, or sort by contact type
    static void printDisplayMethod() {
        System.out.println("SAISIR 1, 2, OU 3 :");
        System.out.println("1. TRIER PAR NOM  2. TRIER PAR CODE POSTAL\n"
            + "3. TRIER PAR TYPE DE CONTACT");
    }
    
    // Printing two options to choose from, nonempty details or empty details 
    static void printEnterAdditionalDetails() {
        System.out.println("SAISIR LES INFORMATIONS UNE A UNE PUIS APPUYER SUR "
            + "\"ENTREE\" \n OU NE RIEN SAISIR ET APPUYER SUR \"ENTREE\"");
    }
    
    // Printing "enter address"
    static void printEnterAddress() {
        System.out.println("SAISIR L'ADRESSE :");
    }
    
    // Printing one of two options to choose from: mandatory details only or all 
    // details
    static void printEnterFirstDetails() {
        System.out.println("1. AJOUTER SEULEMENT UN PRENOM, LE NOM ET LE "
            + "TELEPHONE FIXE \n2. AJOUTER D'AUTRES INFORMATIONS EN PLUS");
    }
    
    // Printing mandatory error message
    static void printErrorMandatoryMessage() {
        System.err.println("SAISIE OBLIGATOIRE !");
    }
    
    // Printing error message
    static void printErrorMessage() {
        System.err.println("SAISIE ERRONEE ! REESSAYER :");
    }
    
    // Printing "do you want to exit address book?"
    static void printExitMessage() {
        System.out.println("QUITTER LE CARNET D'ADRESSE ?");
    }
    
    // Printing success message after adding a family
    static void printFamilyAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE.E AVEC SUCCES A LA FICHE \"FAMILLE\"");
    }
    
    // Printing success message after removing a family member
    static void printFamilyRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()+ " A ETE "
            + "SUPPRIME.E AVEC SUCCES DE LA FICHE \"FAMILLE\"");
    }
    
    // Printing success message after adding a friend
    static void printFriendAdded() {
        System.out.println(getAddressBook().get(
            getAddressBook().size() - 1).getFullName().toUpperCase() + " A ETE "
            + "AJOUTE.E AVEC SUCCES A LA FICHE \"AMI\"");
    }
    
    // Printing success message after removing a friend
    static void printFriendRemoved(int index) {
        System.out.println(getAddressBook().get(index).getFullName()+ " A ETE "
            + "SUPPRIME.E AVEC SUCCES DE LA FICHE \"AMI\"");
    }
    
    // Printing header on address book's display  
    static void printHeader() {
        System.out.print(String.format("%-4s", "Type"));
        System.out.print(" " + String.format(idLength(), "ID"));
        System.out.print(" " + String.format(firstNameLength(), "PRENOM"));
        System.out.print(" " + String.format(lastNameLength(), "NOM"));
        System.out.print(" " + String.format("%-5s", "CP"));
        System.out.print(" " + String.format("%-10s", "FIXE"));
        System.out.println(" " + String.format("%-10s", "MOBILE"));
    }    
    
    // Printing Acquaintance details to modify
    static void printModifyAcquaintance() {
        System.out.println("SAISIR 1, 2, ..., OU 10 :");
        System.out.println("1. MODIFIER LE PRENOM  2. MODIFIER LE NOM\n"
            + "3. MODIFIER LE NUMERO DE RUE  4. MODIFIER LE COMPLEMENT DU "
            + "NUMERO DE RUE\n"
            + "5. MODIFIER LE NOM DE RUE  6. MODIFIER LE COMPLEMENT DE RUE\n"
            + "7. MODIFIER LE CODE POSTAL  8. MODIFIER LA VILLE\n"
            + "9. MODIFIER L'E-MAIL  10. MODIFIER LE NUMERO DE TELEPHONE FIXE"
            + "\n");
    }
    
    // Printing "modify address"
    static void printModifyAddress() {
        System.out.println("MODIFIER L'ADRESSE :");
    }
    
    // Printing "do you want to modify details?"
     static void printModifyDetails() {
        System.out.println("MODIFIER DES INFORMATIONS ?");
    }
    
    // Printing Family details to modify
    static void printModifyFamily() {
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
    
    // Printing Friend details to modify
    static void printModifyFriend() {
        System.out.println("SAISIR 1, 2, ..., OU 11 :");
        System.out.println("1. MODIFIER LE PRENOM  2. MODIFIER LE NOM\n"
            + "3. MODIFIER LE NUMERO DE RUE  4. MODIFIER LE COMPLEMENT DU "
            + "NUMERO DE RUE\n"
            + "5. MODIFIER LE NOM DE RUE  6. MODIFIER LE COMPLEMENT DE RUE\n"
            + "7. MODIFIER LE CODE POSTAL  8. MODIFIER LA VILLE\n"
            + "9. MODIFIER L'E-MAIL  10. MODIFIER LE NUMERO DE TELEPHONE FIXE\n"
            + "11. MODIFIER LE NUMERO DE TELEPHONE MOBILE");
    }
    
    // Printing search result message
    static void printSearchResult() {
        System.out.println("RESULTAT DE LA RECHERCHE :");
    }
    
    // Printing "enter contact type" and one of three options to choose from: 
    // acquaintance, family, or friend
    static void printSelectContactType() {
        System.out.println("SAISIR LE TYPE DE CONTACT, SOIT 1, 2, OU 3 :");
        System.out.println("1. CONNAISSANCE  2. FAMILLE.  3. AMI");
    }
    
    // Printing "enter 1, ..., or 4" and one of four options to choose from:,
    // add contact, find contact, remove contact, or display address book
    static void printSelectUpToFour() {
        System.out.println("SAISIR 1, 2, 3 OU 4 :");
        System.out.println("1. AJOUTER UN CONTACT  2. RECHERCHER UN CONTACT "
            + "3. SUPPRIMER UN CONTACT \n4. AFFICHER LE CARNET D'ADRESSE");
    }
    
    // Printing "enter 1 or 2"
    static void printSelectUpToTwo() {
        System.out.println("SAISIR 1 OU 2 :");
    }
    
    // Printing address book size or number of contacts
    static void printSize() {
        System.out.println(getAddressBook().size() + " contacts.");
    }
    
    // Printing "sorted by contact type"
    static void printSortByContactType() {
        System.out.println("TRI PAR TYPE DE CONTACT :");
    }
    
    // Printing "sorted by last name"
    static void printSortByLastName() {
        System.out.println("TRI PAR NOM :");
    }
    
    // Printing "sorted by postal code"
    static void printSortByPostalCode() {
        System.out.println("TRI PAR CODE POSTAL :");
    }
    
    // Printing Welcome message
    static void printWelcome() {
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
    
    // Printing yes and no options
    static void printYesOrNo() {
        System.out.println("1. OUI  2. NON");
    }
    
// ----------------------------- PROMPTERS (13) --------------------------------
    // Prompting for birthdate
    static String promptBirthDate() throws IOException {
        System.out.println("SAISIR LA DATE DE NAISSANCE AU FORMAT \"dd mm "
            + "yyyy\" \n OU NE RIEN SAISIR AUTREMENT :");
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
    
    // Prompting for first name
    static String promptFirstName() throws IOException {
        System.out.println("SAISIR LE PRENOM :");
        return readString();
    }
    
    // Prompting for ID
    static String promptID() throws IOException {
        System.out.println("SAISIR LE NUMERO D'ID :");
        return readString();
    }
    
    // Prompting for landline phone number
    static String promptLandlinePhone() throws IOException {
        System.out.println("SAISIR LE NUMERO DE TELEPHONE FIXE :");
        return readString();
    }
    
    // Prompting for last name
    static String promptLastName() throws IOException {
        System.out.println("SAISIR LE NOM :");
        return readString();
    }
    
    // Prompting for mobile phone number
    static String promptMobilePhone() throws IOException {
        System.out.println("SAISIR LE NUMERO DE TELEPHONE MOBILE :");
        return readString();
    }
    
    // Prompting for postal code
    static String promptPostalCode() throws IOException {
        System.out.println("SAISIR LE CODE POSTAL :");
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

// -------------------------------- READER (1)----------------------------------
    // Reading a string
    static String readString() throws IOException {
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
         
        return bufferedReader.readLine().trim();
    }
}