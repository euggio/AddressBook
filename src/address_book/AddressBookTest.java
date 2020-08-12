/*
 * CDA AFPA 2020 B.E.E.
 * No Rights Reserved
 */
package address_book;

import static address_book.AddressBook.getAddressBook;
import static address_book.AddressBook.startAddressBook;
import java.io.IOException;

/**
 * CONTACT CLASS TEST
 * Tests the functionality of the address book class, the address book manager
 * class, along with the Acquaintance class, the Family class, and the Friend 
 * class, which are inherited from the Contact class
 * @author cda606
 */
public class AddressBookTest {

    /**
     * Testing functionality 
     * @param args the command line argument
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // Hard coding contacts
        Contact beyonce = new Acquaintance("Beyoncé", "Carter", "38", "bis",
                "Rue de la Pêche", "Bat. B", "95100", "Argenteuil",
                "beyonce@gmail.com", "0148255512");
        Contact justin = new Acquaintance("Justin", "Bieber", "26", "",
                "Rue de la butte blanche", "", "93340", "Le Raincy",
                "justin@gmail.com", "0158968562");
        Contact mariah = new Acquaintance("Mariah", "Carey", "", "", "", "", "",
                "", "0197000253", "");
        Contact rihanna = new Family("Rihanna", "Fenty", "20 02 1988", "", "",
                "", "", "", "", "", "0102548796", "0615241589");
        Contact shakira = new Family("Shakira", "Mebarak Ripoll", "", "", "",
                "", "", "", "", "", "0152486895", "");
        Contact madonna = new Family("Madonna", "Ciccone", "", "", "", "", "",
                "", "", "", "0152654782", "");
        Contact herb = new Friend("Herb", "Alpert", "85", "f", "Rue Mondésir",
                "", "87000", "Limoges", "", "0158964823", "0615897565");
        Contact toby = new Friend("Toby", "Keith", "", "", "", "", "", "", "",
                "0196152368", "");

        // Adding hard coded contacts to address book
        getAddressBook().add(beyonce);
        getAddressBook().add(justin);
        getAddressBook().add(mariah);
        getAddressBook().add(rihanna);
        getAddressBook().add(shakira);
        getAddressBook().add(madonna);
        getAddressBook().add(herb);
        getAddressBook().add(toby);

        startAddressBook();
    }
}