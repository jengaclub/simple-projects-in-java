package tictactoe;
import java.util.Scanner;
import java.io.*;

public class Main {

    //**FUNCTION FOR CHECKING IF THAT POSITION IS EMPTY***//
    public boolean hasEmptyIndex(int i, int j, char[][] array) {
        return array[i][j] == ' '; //returns true if that position is empty
    }
    //****************************************************//

    //***FUNCTION FOR PRINTING THE ARRAY****//
    public void printArray(char[][] array) {
        int i, j;
        System.out.println("---------");
        for (i = 0; i < 3; i++) {
            System.out.print("| ");
            for (j = 0; j < 3; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }
    //**************************************//

    //********MAIN FUNCTION***************//
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i, j = 0;
        char[][] array = new char[3][3];
        Main a = new Main();
        //filling the char array with empty values at first
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                array[i][j] = ' ';
            }
        }
        //prints the array for the first time
        a.printArray(array);

        //taking the value from the user
        String input;
        String[] split;
        boolean endGame = false;
        int num1 = 0, num2 = 0;
        int count = 1;
        while (!endGame) {
            while (true) {
                System.out.println("Enter the coordinates");
                input = scanner.nextLine();
                if (!input.matches(".*\\d.*")) {
                    System.out.println("You should enter numbers!");
                } else {
                    break;
                }
            }

            split = input.split(" ");//splits the string into numbers


            num1 = Integer.parseInt(split[0]);
            num2 = Integer.parseInt(split[1]);
            if (num1 > 3 || num1 < 1 || num2 > 3 || num2 < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                int reali = 0, realj = 0;
                if (num1 == 1) {
                    if (num2 == 2) {
                        reali = 1;
                    } else if (num2 == 1) {
                        reali = 2;
                    }
                } else if (num1 == 2) {
                    if (num2 == 3) {
                        realj = 1;
                    } else if (num2 == 2) {
                        reali = 1;
                        realj = 1;
                    } else if (num2 == 1) {
                        reali = 2;
                        realj = 1;
                    }
                } else if (num1 == 3) {
                    if (num2 == 3) {
                        realj = 2;
                    } else if (num2 == 2) {
                        reali = 1;
                        realj = 2;
                    } else if (num2 == 1) {
                        reali = 2;
                        realj = 2;
                    }
                }
                boolean ans = a.hasEmptyIndex(reali, realj, array);
                if (!ans) {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if(count%2 == 1) {
                        array[reali][realj] = 'X';
                        count++;
                        a.printArray(array);
                        if(a.gameStatus(array))
                        {
                            endGame = true;
                        }
                    }
                    else
                    {
                        array[reali][realj] = 'O';
                        count++;
                        a.printArray(array);
                        if(a.gameStatus(array))
                        {
                            endGame = true;
                        }
                    }
                }
            }
        }
    }
//***************************MAIN ENDS********************************************//

//*********FUNCTION FOR RECOGNISING GAME STATUS************************************//
    public boolean gameStatus(char[][] array) {
        int i, j = 0;
        int countX = 0, countO = 0;//counts the number of x and o's
        //boolean variables
        boolean isEmpty = false;
        int isX = 0, isO = 0;
        boolean gameStatus;

        //**CHECKING IF THERE ARE EMPTY SPACES AND COUNTING X'S AND O'S**//
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (array[i][j] == 'X') {
                    countX++;
                } else if (array[i][j] == 'O') {
                    countO++;
                } else if (array[i][j] == ' ') {
                    isEmpty = true;
                }
            }
        }

        // ** CHECKS FOR WINNING OF X **//
        int count = 0;
        boolean done = false;
        for (i = 0; i < 3 && !done; i++) {
            for (j = 0; j < 3; j++) {
                if (array[i][j] == 'X') {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    done = true;
                    isX = 1;
                    break;
                }
            }
            count = 0;
        }
        count = 0;
        done = false;
        for (i = 0; i < 3 && !done; i++) {
            for (j = 0; j < 3; j++) {
                if (array[j][i] == 'X') {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    done = true;
                    isX = 1;
                    break;
                }
            }
            count = 0;
        }//checks for cols

        if (array[0][0] == 'X' && array[1][1] == 'X' && array[2][2] == 'X')//right diagonal
        {
            isX = 1;
        } else if (array[0][2] == 'X' && array[1][1] == 'X' && array[2][0] == 'X') {
            isX = 1;
        }
        //********************************//

        //********CHECKS FOR WINNING OF O********************//
        count = 0;
        done = false;
        for (i = 0; i < 3 && !done; i++) {
            for (j = 0; j < 3; j++) {
                if (array[i][j] == 'O') {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    done = true;
                    isO = 1;
                    break;
                }
            }
            count = 0;
        }
        count = 0;
        done = false;
        for (i = 0; i < 3 && !done; i++) {
            for (j = 0; j < 3; j++) {
                if (array[j][i] == 'O') {
                    count++;
                } else {
                    count = 0;
                }
                if (count == 3) {
                    done = true;
                    isO = 1;
                    break;
                }
            }
            count = 0;
        }//checks for cols
        if (array[0][0] == 'O' && array[1][1] == 'O' && array[2][2] == 'O')//right diagonal
        {
            isO = 1;
        } else if (array[0][2] == 'O' && array[1][1] == 'O' && array[2][0] == 'O') {
            isO = 1;
        }
        //**********************************************************************/

        //*******CONDITIONS FOR THE CASES*****************//
        if (isX == 1 && isO == 1 || ((countX - countO) >= 2) || ((countO - countX) >= 2)) {
            return false;
        } else if (isX == 0 && isO == 0) {
            if (isEmpty) {
                return false;
            } else {
                System.out.println("Draw");
                return true;
            }
        } else if (isX == 1) {
            System.out.println("X wins");
            return true;
        } else if (isO == 1) {
            System.out.println("O wins");
            return true;
        }
        return false;
    }
    //*****************************************************************************//
}
//END OF CLASS

