import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class TeleBook implements Iterable<Contact> {
    private Map<String, Contact> contacts;

    public TeleBook() {
        contacts = new TreeMap<>();
    }

    public void addContact(Contact contact) throws ExistingNameException {
        if(contacts.containsKey(contact.getName())) {
            throw new ExistingNameException();
        } else {
            contacts.put(contact.getName(), contact);
            System.out.println("Pomyślnie dodano kontakt.");
        }
    
    }

    public void deleteContact(String name) throws NonExistingNameException {
        if(contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Poprawnie usunięto kontakt.");
        } else {
            throw new NonExistingNameException();
        }
        
    }

    public List<Contact> searchForContact(String partialName) {
        List<Contact> contactList = new ArrayList<>();
        for(var contact : contacts.entrySet()) {
            if(contact.getKey().toLowerCase().contains(partialName.toLowerCase())) {
                contactList.add(contact.getValue());
            }
        }
        return contactList;
    }

    public Contact searchForContractByNumber(int number) throws NoSuchElementException {
        for(Contact contact : contacts.values()) {
            if(contact.getPhoneNumber() == number) {
                return contact;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * @return Map<String, Contact> return the contacts
     */
    public Map<String, Contact> getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts toTree
     */
    public void setContacts(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public Iterator<Contact> iterator() {
        return contacts.values().iterator();
    }

}
