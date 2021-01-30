package cinema;

import java.util.Arrays;
import java.util.function.Predicate;

public enum Menu {
    SHOW("Show the seats", 1),
    BUY("Buy a ticket", 2),
    EXIT("Exit", 0),
    STATISTICS("Statistics", 3);

    private final String text;
    private final int id;

    Menu(String text, int id) {
        this.text = text;
        this.id = id;
    }

    public static Menu getById(int menuId) throws CinemaException {
        Predicate<Menu> menuPredicate = v -> v.id == menuId;
        return Arrays.stream(values()).filter(menuPredicate).findAny()
                .orElseThrow(()-> new CinemaException("No menu exists with id " + menuId));
    }

    @Override
    public String toString() {
        return id + ". " + text;
    }
}
