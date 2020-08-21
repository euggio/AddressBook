package address_book;

import java.io.IOException;

/**
 * MAIN
 * Starts program
 * @author euggio
 */
public class AddressBookMain {

    /**
     * Creates and adds hard-coded contacts to address book 
     * @param args the command line argument
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException { 
        new AddressBook(
            new Acquaintance("Beyoncé", "Carter", "38", "bis",
                "Rue de la Pêche", "Bat. B", "95100", "Argenteuil",
                "beyonce@gmail.com", "0148255512"),
            new Acquaintance("Justin", "Bieber", "26", "",
                "Rue de la butte blanche", "", "93340", "Le Raincy",
                "justin@gmail.com", "0158968562"),
            new Acquaintance("Mariah", "Carey", "", "", "", "", "",
                "", "", "0197000253"),
            new Family("Rihanna", "Fenty", "20 02 1988", "", "",
                "", "", "", "", "", "0102548796", "0615241589"),
            new Family("Shakira", "Mebarak Ripoll", "", "", "",
                "", "", "", "", "", "0152486895", ""),
            new Family("Madonna", "Ciccone", "", "", "", "", "",
                "", "", "", "0152654782", ""),
            new Friend("Herb", "Alpert", "85", "f", "Rue Mondésir",
                "", "87000", "Limoges", "", "0158964823", "0615897565"),
            new Friend("Toby", "Keith", "", "", "", "", "", "", "",
                "0196152368", "")
        );
    }
}