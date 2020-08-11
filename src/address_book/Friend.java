/*
 * CDA AFPA 2020 B.E.E.
 * No Rights Reserved
 */
package address_book;

import java.io.IOException;

/**
 * FRIEND CLASS
 * One of the three subclasses of Contact class; that is a friend is a contact.
 * It contains all of superclass's fields but the birthdate field, which is 
 * valued as "N/A" in superclass's reserved constructor 
 * @see Contact superclass for more information
 * @author euggio
 */
class Friend extends Contact { 
// ------------------------------ CONSTRUCTOR (1) ------------------------------
    /**
     * Constructor initializing the corresponding superclass's reserved 
     * constructor
     * @param firstName, a first name 
     * @param lastName, a last name 
     * @param streetNumber, a street number  
     * @param streetNumberSuffix, a street number suffix 
     * @param streetName, a street name 
     * @param streetNameSuffix, a street name suffix 
     * @param postalCode, a postal code 
     * @param city, a city 
     * @param email, an email address 
     * @param landlinePhone, a landline phone number 
     * @param mobilePhone, a mobile phone number 
     * @throws java.io.IOException 
     * @see Contact superclass for more information
     */
    Friend(String firstName, String lastName, String streetNumber, 
        String streetNumberSuffix, String streetName, String streetNameSuffix, 
        String postalCode, String city, String email, String landlinePhone, 
        String mobilePhone) throws IOException {
        super(firstName, lastName, streetNumber, streetNameSuffix, streetName, 
        streetNameSuffix, postalCode, city, email, landlinePhone, mobilePhone);
    }
}