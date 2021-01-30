package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static Scanner sc = new Scanner(System.in);
    private static Room room;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = sc.nextInt();
        room = new Room(columns, rows);
        room.generate();
        do {
            System.out.println();
        } while (printMenu());
    }

    private static boolean printMenu() {
        Arrays.stream(Menu.values()).forEach(System.out::println);
        switch (Menu.getById(sc.nextInt())) {
            case SHOW:
                System.out.println(room);
                break;
            case BUY:
                boolean run = true;
                do {
                    System.out.println("Enter a row number:");
                    int row = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    int col = sc.nextInt();
                    try {
                        System.out.println("Ticket price: $" + room.selectSeat(col, row));
                        run = false;
                    } catch (CinemaException e) {
                        System.out.println(e.getMessage());
                    }
                } while (run);
                break;
            case STATISTICS:
                System.out.println(room.statistics());
                break;
            case EXIT:
                return false;
            default:
                System.out.println("Invalid option");
        }
        return true;
    }
}