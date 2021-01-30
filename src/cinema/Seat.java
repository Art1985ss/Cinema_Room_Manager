package cinema;

public enum Seat {
    FIRST_CLASS('S', 10),
    SECOND_CLASS('S', 8),
    FIRST_CLASS_RESERVED('B', 10),
    SECOND_CLASS_RESERVED('B', 8);

    private final char symbol;
    private final int price;

    Seat(char symbol, int price) {
        this.symbol = symbol;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}
