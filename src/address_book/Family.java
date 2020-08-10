/*
 * CDA AFPA 2020 B.E.E.
 * No Rights Reserved
 */
package address_book;

/**
 * FAMILY CLASS
 * One of the three subclasses of Contact class
 * It contains all of superclass's fields and is different to super only by type
 * name
 * @author euggio
 */
public class Family extends Contact {
// ------------------------------ CONSTRUCTOR (1) ------------------------------
    /**
     * Constructor initializing the corresponding superclass's constructor
     * @param firstName, a first name 
     * @param lastName, a last name 
     * @param birthdate, a birthdate 
     * @param streetNumber, a street number
     * @param streetNumberSuffix, a street number suffix 
     * @param streetName, a street name 
     * @param streetNameSuffix, a street name suffix 
     * @param postalCode, a postal code 
     * @param city, a city 
     * @param email, an email address 
     * @param landlinePhone, a landline phone number 
     * @param mobilePhone, a mobile phone number  
     * @see Contact superclass for more information 
     */
    public Family(String firstName, String lastName, String birthdate, 
            String streetNumber, String streetNumberSuffix, String streetName, 
            String streetNameSuffix, String postalCode, String city, 
            String email, String landlinePhone, String mobilePhone) {
        super(firstName, lastName, birthdate, streetNumber, streetNameSuffix, 
            streetName, streetNameSuffix, postalCode, city, email, 
            landlinePhone, mobilePhone);
    }
}