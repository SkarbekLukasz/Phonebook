import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileOperator {
    private static final String CONTACTS = "contacts.txt";
    
    public static void readFromFile(TeleBook tb) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(CONTACTS));
        Map<String, Contact> contacts = br.lines()
            .map(line -> line.split(";"))
            .map(split -> new Contact(split[0], Integer.valueOf(split[1])))
            .collect(Collectors.toMap(Contact::getName, Function.identity()));
        tb.setContacts(contacts);
        br.close();
    }

    public static void writeToFIle(TeleBook tb) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(CONTACTS));
        for(Contact contact : tb) {
            bw.write(contact.toCSV());
            bw.newLine();
        }
        bw.close();
    }
}
