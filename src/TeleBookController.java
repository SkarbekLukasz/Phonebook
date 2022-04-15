import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TeleBookController {
    private Scanner scn = new Scanner(System.in);
    private TeleBook tBook = new TeleBook();

    public void mainLoop() {
        try {
            FileOperator.readFromFile(tBook);
        } catch(IOException e) {
            //ignore
        }
        OptionsEnum chosenOption = null;
        do {
            System.out.println("Wybierz jedną z poniższych opcji: ");
            printOptions();
            try{
                chosenOption = chosenFunction();
                performFunction(chosenOption);
            } catch(NoSuchElementException e) {
                System.out.println("Nieprawidłowa opcja");
            }
            
        } while (chosenOption != OptionsEnum.EXIT);
        return;
    }

    private void performFunction(OptionsEnum chosenOption) {
        switch (chosenOption) {
            case ADD: addContact();
            break;
            case FIND_BY_NAME: findByName();
            break;
            case FIND_BY_PHONENUMBER: findByPhoneNumber();
            break;
            case DELETE: delete();
            break;
            case EXIT: close();
            break;
        }
    }

    private void close() {
        scn.close();
        try{
            FileOperator.writeToFIle(tBook);
            System.out.println("Zapisano zmiany.");
        } catch(IOException e) {
            System.err.println("Nie udało się zapisać do pliku.");
        }
        System.out.println("Koniec programu.");
    }

    private void delete() {
        System.out.println("Podaj nazwę kontaktu do usunięcia: ");
        scn.nextLine();
        String name = scn.nextLine();
        try {
            tBook.deleteContact(name);
        } catch(NonExistingNameException e) {
            System.out.println("Brak kontaktu o podanej nazwie.");
        }
        
    }

    private void findByPhoneNumber() {
        int number;
        Contact contact;
        System.out.println("Podaj numer telefonu do wyszukania kontaktu: ");
        number = scn.nextInt();
        try {
            contact = tBook.searchForContractByNumber(number);
            System.out.println(contact);
            
        } catch(NoSuchElementException e) {
            System.out.println("Brak kontaktu o wskazanym numerze.");
        }
    }

    private void findByName() {
        String name;
        List<Contact> contactsList = new ArrayList<>();
        System.out.println("Podaj nazwę kontaktu: ");
        scn.nextLine();
        name = scn.nextLine();
        contactsList = tBook.searchForContact(name);
        if(contactsList.isEmpty()) {
            System.out.println("Nie znaleziono kontaktów o podanej nazwie.");
        } else {
            for(Contact contact : contactsList) {
                System.out.println(contact.toString());
            }
        }
    }

    private void addContact() {
        String name;
        int phoneNumber;
        System.out.println("Podaj nazwę kontaktu: ");
        scn.nextLine();
        name = scn.nextLine();
        System.out.println("Podaj numer telefonu: ");
        phoneNumber = scn.nextInt();
        try {
            tBook.addContact(new Contact(name, phoneNumber));
        } catch(ExistingNameException e) {
            System.out.println("Istnieje kontakt o podanej nazwie.");
        }
    }

    private OptionsEnum chosenFunction() {
        return OptionsEnum.transform(scn.nextInt());
    }

    private void printOptions() {
        int enumLength = OptionsEnum.values().length;
        for (int i = 0; i < enumLength; i++) {
            System.out.println(OptionsEnum.transform(i));
        }
    }
}
