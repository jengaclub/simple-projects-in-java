package cinema;
import java.util.Scanner;

public class Cinema {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int seatNo = 0;
        int rowNo = 0;
        int noOfBoughtTickets = 0;
        while(true) {
            rowNo = scanner.nextInt();
            System.out.println("Enter the number of seats in each row:");
            seatNo = scanner.nextInt();
            if (seatNo == 0 || rowNo == 0) {
                System.out.println("Sorry! 0 rows or 0 seats not allowed! enter again!");
            }
            else{
                break;
            }
        }
        char[][] cinemaSeats = new char[rowNo + 1][seatNo + 1];
        Cinema cinema = new Cinema();
        cinemaSeats = cinema.fillEmptySeats(rowNo, seatNo, cinemaSeats);
        int choice;
        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            choice = scanner.nextInt();
            switch(choice){
                case 1: cinema.printSeats(cinemaSeats);
                break;

                case 2: cinemaSeats = cinema.calculatePriceAndFillCustomer(cinemaSeats,rowNo,seatNo,scanner);
                noOfBoughtTickets ++;
                break;

                case 3:
                    cinema.printSeats(cinemaSeats);
                    cinema.Statistics(cinemaSeats,rowNo,seatNo,noOfBoughtTickets);
                break;

                case 0:
                    return;
            }
        }
    }
    char[][] fillEmptySeats(int rowNo,int colNo,char[][] cinemaSeats){
        int rowNumber = 1;
        int colNumber = 1;
            for (int i = 0; i <= rowNo; i++) {
                for (int j = 0; (j <= colNo && rowNumber <= rowNo + 1 && colNumber <= colNo + 1); j++) {
                    if (i == 0) {
                        if (j == 0) {
                            cinemaSeats[i][j] = ' ';
                        } else {
                            cinemaSeats[i][j] = (char) (colNumber + '0');
                            colNumber++;
                        }
                    } else if (j == 0) {
                        cinemaSeats[i][j] = (char) (rowNumber + '0');
                        rowNumber++;
                    } else {
                        cinemaSeats[i][j] = 'S';
                    }
                }
            }
            return cinemaSeats;
        }

    char[][] calculatePriceAndFillCustomer(char[][] cinemaSeats,int totalRowNo,int totalSeatNo,Scanner scanner){
        int reqRowNo,reqSeatNo;
        while(true) {
            System.out.println("\nEnter a row number:");
            reqRowNo = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            reqSeatNo = scanner.nextInt();
            if(reqRowNo > totalRowNo || reqSeatNo > totalSeatNo){
                System.out.println("\nWrong input!");
            }
            else if(cinemaSeats[reqRowNo][reqSeatNo] == 'B'){
                System.out.println("\nThat ticket has already been purchased!");
            }
            else{
                break;
            }
        }
        int cost;
        int totalSeats = totalRowNo * totalSeatNo;
        if (totalSeats <= 60)
            cost = 10;
        else {
            int frontHalf = totalRowNo / 2;
            if(reqRowNo <= frontHalf)
                cost = 10;
            else
                cost = 8;
        }
        System.out.println("Ticket price: $"+cost);
            cinemaSeats[reqRowNo][reqSeatNo] = 'B';
        return cinemaSeats;
    }
     void printSeats(char[][] cinemaSeats){
        System.out.println("\nCinema:");
         for (char[] cinemaSeat : cinemaSeats) {
             for (int j = 0; j < cinemaSeats[0].length; j++) {
                 System.out.print(cinemaSeat[j] + " ");
             }
             System.out.print("\n");
         }
     }

     void Statistics(char[][]cinemaSeats,int totalRoNo, int totalColumnNo, int boughtTickets){
        int totalSeats = totalRoNo * totalColumnNo;
        int totalIncome;
        int currentIncome = 0;
        float percentage = ((float)boughtTickets / totalSeats) * 100;
        if(totalSeats < 60){
            totalIncome = totalSeats * 10;
            currentIncome = boughtTickets * 10;
        }
        else {
            int frontHalf = totalRoNo / 2;
            int backHalf = totalRoNo - frontHalf;
            totalIncome = (frontHalf * totalColumnNo * 10) + (backHalf * totalColumnNo * 8);
            for (int i = 1; i <= totalRoNo; i++) {
                for (int j = 1; j <= totalColumnNo; j++) {
                    if (cinemaSeats[i][j] == 'B') {
                        if (i <= frontHalf) {
                            currentIncome += 10;
                        }
                        else {
                            currentIncome += 8;
                        }
                    }
                }
            }
        }
         System.out.println("Number of purchased tickets: " + boughtTickets);
         System.out.print("Percentage: ");
         System.out.printf("%.2f",percentage);
         System.out.print("%\n");
         System.out.println("Current income: $" + currentIncome);
         System.out.println("Total income: $" + totalIncome);
     }
}