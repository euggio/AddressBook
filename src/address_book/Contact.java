/*
 * CDA AFPA 2020 B.E.E.
 * No Rights Reserved
 */
package address_book;

import static address_book.AddressBook.*;
import java.io.IOException;
import java.util.Objects;

/**
 * CONTACT CLASS
 * Used as superclass for the Acquaintance class, the Family class, and the 
 * Friend class.
 * The first name and the last name, as well as the landline phone, are 
 * mandatory informations for a contact.
 * Postal codes, as well as phone numbers are implemented as French objects
 * @author euggio
 */
class Contact {
// -------------------------- CONSTRUCTORS (3) ---------------------------------
    /**
     * This constructor takes as parameters variables corresponding to all 
     * instance fields, except for the birthdate, mobile phone, and id fields.
     * It works as a base constructor reserved for the Acquaintance subclass
     * @param firstName, a first name 
     * @param lastName, a last name 
     * @param streetNumber, a street number 
     * @param streetNumberSuffix, a street number suffix, as bis in 13 bis
     * @param streetName, a street name 
     * @param streetNameSuffix, a street name suffix, as building number 
     * and/or apartment number, etc.
     * @param postalCode, a postal code or zip code 
     * @param city, a city 
     * @param email, an email address 
     * @param landlinePhone, a landline phone number
     * @see Acquaintance subclass for more information
     */
    Contact(String firstName, String lastName, String streetNumber, 
        String streetNumberSuffix, String streetName, String streetNameSuffix, 
        String postalCode, String city, String email, String landlinePhone) 
            throws IOException { 
                
        this.firstName = controlMandatoryFirstName(firstName);
        this.lastName = controlMandatoryLastName(lastName);        
        this.birthdate = "N/A"; // Not Applicable as to the Acquaintance class
        this.streetNumber = controlStreetNumber(streetNumber);

        if ("".equals(streetNumberSuffix) || streetNumberSuffix == null) this.
            streetNumberSuffix = "INCONNU";
        else this.streetNumberSuffix = streetNumberSuffix;        
        
        if ("".equals(streetName) || streetName == null) this.streetName = 
            "INCONNU";
        else this.streetName = streetName;             
        
        if ("".equals(streetNameSuffix) || streetNameSuffix == null) this.
            streetNameSuffix = "INCONNU";
        else this.streetNameSuffix = streetNameSuffix;
        
        this.postalCode = controlPostalCode(postalCode);

        if ("".equals(city) || city == null) this.city = "INCONNUE";
        else this.city = city;
        
        this.email = controlEmail(email);
        this.landlinePhone = controlMandatoryLandlinePhone(landlinePhone);
        this.mobilePhone = "N/A"; // Not Applicable as to the Acquaintance class
    }
    
    /**
     * This constructor takes as parameters variables corresponding to all 
     * instance fields, except for the birthdate and id fields.
     * It works as a base constructor reserved for the Friend subclass
     * @param firstName, a first name 
     * @param lastName, a last name 
     * @param streetNumber, a street number 
     * @param streetNumberSuffix, a street number suffix, as bis in 13 bis
     * @param streetName, a street name 
     * @param streetNameSuffix, a street name suffix, as building number 
     * and/or apartment number, etc.
     * @param postalCode, a postal code or zip code 
     * @param city, a city 
     * @param email, an email address 
     * @param landlinePhone, a landline phone number
     * @param mobilePhone, a mobile phone number
     * @see Friend subclass for more information
     */
    Contact(String firstName, String lastName, String streetNumber, 
        String streetNumberSuffix, String streetName, String streetNameSuffix, 
        String postalCode, String city, String email, String landlinePhone, 
        String mobilePhone) throws IOException { 
        
        /// Empty strings and nulls are translated to INCONNUE, except for 
        // mandatory details, such as first names, last names, and landline 
        // phone numbers
        this.firstName = controlMandatoryFirstName(firstName);
        this.lastName = controlMandatoryLastName(lastName);
        this.birthdate = "N/A"; // Not Applicable as to the Friend subclass
        this.streetNumber = controlStreetNumber(streetNumber);

        if ("".equals(streetNumberSuffix) || streetNumberSuffix == null) this.
            streetNumberSuffix = "INCONNU";
        else this.streetNumberSuffix = streetNumberSuffix;        
        
        if ("".equals(streetName) || streetName == null) this.streetName = 
            "INCONNU";
        else this.streetName = streetName;             
        
        if ("".equals(streetNameSuffix) || streetNameSuffix == null) this.
            streetNameSuffix = "INCONNU";
        else this.streetNameSuffix = streetNameSuffix;
        
        this.postalCode = controlPostalCode(postalCode);

        if ("".equals(city) || city == null) this.city = "INCONNUE";
        else this.city = city;

        this.email = controlEmail(email);
        this.landlinePhone = controlMandatoryLandlinePhone(landlinePhone);
        this.mobilePhone = controlMobilePhone(mobilePhone);     
    }
    
    /**
     * This constructor takes as parameters variables corresponding to all 
     * instance fields, except for the id field.
     * It works as a base constructor reserved for the Family subclass
     * @param firstName, a first name 
     * @param lastName, a last name 
     * @param birthdate, a birthdate
     * @param streetNumber, a street number 
     * @param streetNumberSuffix, a street number suffix, as bis in 13 bis
     * @param streetName, a street name 
     * @param streetNameSuffix, a street name suffix, as building number 
     * and/or apartment number, etc.
     * @param postalCode, a postal code or zip code 
     * @param city, a city 
     * @param email, an email address 
     * @param landlinePhone, a landline phone number
     * @param mobilePhone, a mobile phone number
     * @see Family subclass for more information
     */
    Contact(String firstName, String lastName, String birthdate, 
        String streetNumber, String streetNumberSuffix, String streetName, 
        String streetNameSuffix, String postalCode, String city, String email, 
        String landlinePhone, String mobilePhone) throws IOException { 
        
        // Empty strings and nulls are translated to Unknown, except for 
        // mandatory details, such as first names, last names, and landline 
        // phone numbers
        this.firstName = controlMandatoryFirstName(firstName);
        this.lastName = controlMandatoryLastName(lastName);
        this.birthdate = controlBirthdate(birthdate);
        this.streetNumber = controlStreetNumber(streetNumber);

        if ("".equals(streetNumberSuffix) || streetNumberSuffix == null) this.
            streetNumberSuffix = "INCONNU";
        else this.streetNumberSuffix = streetNumberSuffix;        
        
        if ("".equals(streetName) || streetName == null) this.streetName = 
            "INCONNU";
        else this.streetName = streetName;             
        
        if ("".equals(streetNameSuffix) || streetNameSuffix == null) this.
            streetNameSuffix = "INCONNU";
        else this.streetNameSuffix = streetNameSuffix;
        
        this.postalCode = controlPostalCode(postalCode);

        if ("".equals(city) || city == null) this.city = "INCONNUE";
        else this.city = city;

        this.email = controlEmail(email);
        this.landlinePhone = controlMandatoryLandlinePhone(landlinePhone);
        this.mobilePhone = controlMobilePhone(mobilePhone);     
    } 
    
// ---------------------------- OVERRIDES (3) ----------------------------------
    /**
     * Generating a description 
     * @return the description 
     */
    @Override
    public final String toString() {   
        // Allows to display first name, ID, landline controlPhone number, and, for 
        // sorting purposes, contact type, last name, and postal code
        String format = "";
        if ("Acquaintance".equals(getClass().getSimpleName())) format = "C";
        else if ("Family".equals(getClass().getSimpleName())) format = "F";
        else format = "A";
        
        return  String.format("%-4s", format)
            + " " + String.format(idLength(), Integer.toString(id))
            + " " + String.format(firstNameLength(), firstName)
            + " " + String.format(lastNameLength(), lastName)
            + " " + String.format("%-7s", postalCode)
            + " " + String.format("%-10s", landlinePhone)
            + " " + String.format("%-10s", mobilePhone);
    }    
    
    /**
     * Generating equality for Contact
     * @param otherObject the other Contact to whom to compare
     * @return true or false
     */
    @Override
    public final boolean equals(Object otherObject) {
        // Testing equality of memory addresses 
        if (this == otherObject) return true;
        
        // Testing whether otherObject is null
        if (otherObject == null) return false;
        
        // Testing whether otherObject is a contact.
        // Being an acquaintance, a friend, or a family does not matter
        if (!(otherObject instanceof Contact)) return false;
        
        // ... Hence the equality testing of mandatory fields only
        Contact other = (Contact) otherObject;
        return Objects.equals(firstName, other.firstName) &&
               Objects.equals(lastName, other.lastName) &&
               Objects.equals(landlinePhone, other.landlinePhone);
    }

    /**
     * Generating a hash code 
     * @return the hash code 
     */
    @Override
    public final int hashCode() {
        // Only mandatory fields are considered for consistency with the equals
        // method
        return Objects.hash(lastName, firstName, landlinePhone);
    }
    
// ---------------------------- GETTERS (13) -----------------------------------
    /**
     * Getting an id number
     * @return the id number 
     */
    int getId() {
        return id;
    }
    
    /**
     * Getting a first name
     * @return the first name
     */
    String getFirstName() {
        return firstName;
    }
    
    /**
     * Getting a last name
     * @return the last name
     */
    String getLastName() {
        return lastName;
    }
    
    /**
     * Getting a bithdate
     * @return the birthdate
     */
    String getBirthdate() {
        return birthdate;
    }
    
    /**
     * Getting a full name
     * @return the full name
     */
    String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Getting a street number
     * @return the street number
     */
    String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Getting a street number suffix
     * @return the street number suffix
     */
    String getStreetNumberSuffix() {
        return streetNumberSuffix;
    }

    /**
     * Getting a street name
     * @return the street name
     */
    String getStreetName() {
        return streetName;
    }

    /**
     * Getting a street name suffix
     * @return the street name suffix
     */
    String getStreetNameSuffix() {
        return streetNameSuffix;
    }

    /**
     * Getting a postal code
     * @return the postal code
     */
    String getPostalCode() {
        return postalCode;
    }

    /**
     * Getting a city name
     * @return the city name
     */
    String getCity() {
        return city;
    }
    
    /**
     * Getting a full address 
     * @return the full address 
     */
    String getAddress() {
        if ("INCONNU".equals(streetNumber)) return "INCONNU";
        else {
            if ("INCONNU".equals(streetNumberSuffix)) 
                streetNumberSuffix = " ";
            else streetNumberSuffix = " " + streetNumberSuffix + " ";
        
            if ("INCONNU".equals(streetNameSuffix)) streetNameSuffix = " ";
            else streetNameSuffix = " " + streetNameSuffix + " "; 
        
        return streetNumber + streetNumberSuffix + streetName +
            streetNameSuffix + postalCode + " " + city;
        }
    }

    /**
     * Getting an controlEmail address
     * @return the controlEmail address
     */
    String getEmail() {
        return email;
    }

    /**
     * Getting a landline controlPhone number
     * @return the landline controlPhone number
     */
    String getLandlinePhone() {
        return landlinePhone;
    }
    
    /**
     * Getting a mobile controlPhone number
     * @return the mobile controlPhone number
     */
    String getMobilePhone() {
        return mobilePhone;
    }

// ---------------------------- SETTERS (10) -----------------------------------
    /**
     * Setting the first name
     * @param firstName, a first name
     */
    void setFirstName(String firstName) throws IOException {
        this.firstName = controlMandatoryFirstName(firstName);
    }
    
    /**
     * Setting the last name
     * @param lastName, a last name 
     */
    void setLastName(String lastName) throws IOException {
        this.lastName = controlMandatoryLastName(lastName);
    }

    /**
     * Setting the birthdate
     * @param birthdate, a birthdate
     * @throws java.io.IOException
     */
    void setBirthdate(String birthdate) throws IOException {
        this.birthdate = controlBirthdate(birthdate);
    }
    
    /**
     * Setting the street number 
     * @param streetNumber, the street number 
     * @throws java.io.IOException 
     */
    void setStreetNumber(String streetNumber) throws IOException {
        this.streetNumber = controlStreetNumber(streetNumber);
    }
    
    /**
     * Setting the street number suffix 
     * @param streetNumberSuffix, the street number suffix 
     */
    void setStreetNumberSuffix(String streetNumberSuffix) {
        // Allowing user to enter empty string as s/he changes her/his mind
        if ("".equals(streetNumberSuffix) || streetNumberSuffix == null) this.
            streetNumberSuffix = "INCONNU";
        else this.streetNumberSuffix = streetNumberSuffix;
    }

    /**
     * Setting the street name 
     * @param streetName, the street name 
     */
    void setStreetName(String streetName) {
        // Allowing user to enter empty string as s/he changes her/his mind
        if ("".equals(streetName) || streetName == null) this.streetName = 
            "INCONNU";
        else this.streetName = streetName;
    }
    
    /**
     * Setting the street name suffix 
     * @param streetNameSuffix, the street name suffix 
     */
    void setStreetNameSuffix(String streetNameSuffix) {
        /// Allowing user to enter empty string as s/he changes her/his mind
        if ("".equals(streetNameSuffix) || streetNameSuffix == null) this.
            streetNameSuffix = "INCONNU";
        else this.streetNameSuffix = streetNameSuffix;
    }
    
    /**
     * Setting the postal code 
     * @param postalCode, the postal code 
     * @throws java.io.IOException 
     */
    void setPostalCode(String postalCode) throws IOException {
        this.postalCode = controlPostalCode(postalCode);
    }

    /**
     * Setting the street city 
     * @param city, the city 
     */
    void setCity(String city) {
        // Allowing user to enter empty string as s/he changes her/his mind
        if ("".equals(city) || city == null) this.city = "INCONNUE";
        else this.city = city;
    }
    
    /**
     * Setting the email address 
     * @param email, an email address 
     * @throws java.io.IOException 
     */
    void setEmail(String email) throws IOException {
        this.email = controlEmail(email);
    }

    /**
     * Setting the landline phone number 
     * @param landlinePhone, a landline phone number 
     * @throws java.io.IOException 
     */
    void setLandlinePhone(String landlinePhone) throws IOException {
        this.landlinePhone = controlMandatoryLandlinePhone(landlinePhone);
    }       
    
    /**
     * Setting the mobile phone number
     * @param mobilePhone, the mobile phone
     * @throws java.io.IOException 
     */
    void setMobilePhone(String mobilePhone) throws IOException {
        this.mobilePhone = controlMobilePhone(mobilePhone);
    }
    
// ---------------------------- FIELDS (12) ------------------------------------
    // Static Field 
    private static int nextId = 1;
    
    // Instance Fields
    private final int id = nextId++; 
    private String firstName;
    private String lastName;
    private String birthdate;
    private String streetNumber;
    private String streetNumberSuffix;
    private String streetName;
    private String streetNameSuffix;
    private String postalCode;
    private String city;
    private String email;
    private String landlinePhone;
    private String mobilePhone;
}