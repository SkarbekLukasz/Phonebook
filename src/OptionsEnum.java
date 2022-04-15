import java.util.NoSuchElementException;

public enum OptionsEnum {
    ADD(0, "Dodaj nowy kontakt"),
    FIND_BY_NAME(1, "Wyszukaj po nazwie"),
    FIND_BY_PHONENUMBER(2, "Wyszukaj po numerze telefonu"),
    DELETE(3, "Usuń kontakt"),
    EXIT(4, "Wyjście z programu");

    private int option;
    private String description;

    OptionsEnum(int option, String description) {
        this.option = option;
        this.description = description;
    }

    /**
     * @return int return the option
     */
    public int getOption() {
        return option;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    public static OptionsEnum transform(int number) throws NoSuchElementException{
        if(number >= values().length || number < 0) {
            throw new NoSuchElementException();
        } else {
            return values()[number];
        }
    }

    @Override
    public String toString() {
        return option + "-" + description;
    }
}
