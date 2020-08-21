package address_book;

import java.io.IOException;

/**
 * ACQUAINTANCE CLASS
 * One of the three subclasses of Contact class; that is an acquaintance is a 
 * contact.
 * It contains all superclass's fields but the birthdate and the mobile phone 
 * fields, which are valued as "N/A" in superclass's reserved constructor 
 * @see Contact superclass for more information
 * @author euggio
 */
class Acquaintance extends Contact {
// ------------------------------ CONSTRUCTOR (1) ------------------------------
    /**
     * Constructor initializing the corresponding superclass's reserved 
     * constructor
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
     * @throws java.io.IOException 
     * @see Contact superclass for more information 
     */
    Acquaintance(String firstName, String lastName, String streetNumber, 
        String streetNumberSuffix, String streetName, String streetNameSuffix, 
        String postalCode, String city, String email, String landlinePhone) 
        throws IOException {       
        super(firstName, lastName, streetNumber, streetNumberSuffix, streetName,
            streetNameSuffix, postalCode, city, email, landlinePhone);
    }
}