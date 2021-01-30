package cinema;

public class Room {
    private static final int SMALL_ROOM_SEATS = 60;
    private final int columns;
    private final int rows;
    private final int totalSeats;
    private final Seat[][] seats;
    private int totalPrice = 0;
    private int purchasedTickets = 0;
    private int currentIncome = 0;

    public Room(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        totalSeats = columns * rows;
        seats = new Seat[columns][rows];
    }

    public void generate() {
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (totalSeats <= SMALL_ROOM_SEATS) {
                    seats[col][row] = Seat.FIRST_CLASS;
                } else {
                    int rowNum = row + 1;
                    seats[col][row] = rowNum <= rows / 2 ? Seat.FIRST_CLASS : Seat.SECOND_CLASS;
                }
                totalPrice += seats[col][row].getPrice();
            }
        }
    }

    public String statistics() {
        String text = "Number of purchased tickets: " + purchasedTickets;
        text += String.format("\nPercentage: %.2f%%", purchasedTickets / (double)totalSeats * 100);
        text += "\nCurrent income: $" + currentIncome;
        text += "\nTotal income: $" + totalPrice;
        return text;
    }

    public int selectSeat(int col, int row) throws CinemaException {
        row -= 1;
        col -= 1;
        if (row >= rows || col >= columns) {
            throw new CinemaException("Wrong input");
        }
        Seat seat = seats[col][row];
        if (seat.equals(Seat.FIRST_CLASS_RESERVED) || seat.equals(Seat.SECOND_CLASS_RESERVED)) {
            throw new CinemaException("That ticket has already been purchased!");
        }
        seats[col][row] = seat == Seat.FIRST_CLASS ? Seat.FIRST_CLASS_RESERVED : Seat.SECOND_CLASS_RESERVED;
        currentIncome += seat.getPrice();
        purchasedTickets++;
        return seat.getPrice();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cinema:\n");
        for (int i = 0; i <= columns; i++) {
            if (i == 0) {
                sb.append("  ");
            } else {
                sb.append(i).append(" ");
            }
        }
        sb.append("\n");
        for (int row = 0; row < rows; row++) {
            for (int col = -1; col < columns; col++) {
                if (col == -1) {
                    sb.append(row + 1).append(" ");
                } else {
                    sb.append(seats[col][row]).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
