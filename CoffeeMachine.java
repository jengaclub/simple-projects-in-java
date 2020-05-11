package machine;

import java.util.Scanner;

public class CoffeeMachine {

    public static void printAmount(int water, int milk, int beans, int cups, int money) {
        System.out.println("The coffee machine has:");
        System.out.println(water + " of water");
        System.out.println(milk + " of milk");
        System.out.println(beans + " of coffee beans");
        System.out.println(cups + " of disposable cups");
        System.out.println(money + " of money");
    }

    public static boolean isOutOfResources(int water, int milk, int beans, int cups, String option) {
        if (cups - 1 < 0) {
            System.out.println("Sorry, not enough disposable cups!");
            return true;
        } else {
            switch (option) {
                case "1":
                    if (water - 250 < 0) {
                        System.out.println("Sorry, not enough water!");
                        return true;
                    }
                    if (beans - 16 < 0) {
                        System.out.println("Sorry, not enough coffee beans!");
                        return true;
                    }
                    break;
                case "2":
                    if (water - 350 < 0) {
                        System.out.println("Sorry, not enough water!");
                        return true;
                    }
                    if (milk - 75 < 0) {
                        System.out.println("Sorry, not enough milk!");
                        return true;
                    }
                    if (beans - 20 < 0) {
                        System.out.println("Sorry, not enough coffee beans!");
                        return true;
                    }
                    break;
                case "3":
                    if (water - 200 < 0) {
                        System.out.println("Sorry, not enough water!");
                        return true;
                    }
                    if (milk - 100 < 0) {
                        System.out.println("Sorry, not enough milk!");
                        return true;
                    }
                    if (beans - 12 < 0) {
                        System.out.println("Sorry, not enough coffee beans!");
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String action;
        boolean on = true;

        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        int money = 550;

        while (on) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            action = scanner.nextLine();
            System.out.println(action);
            switch (action) {
                /* ===== BUY OPTION ==== */
                case "buy":
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
                    String buyOption = scanner.nextLine();

                    if (buyOption.equals("1") || buyOption.equals("2") || buyOption.equals("3")) {
                        if (isOutOfResources(water, milk, beans, cups, buyOption)) {

                            break;
                        } else {
                            System.out.println("I have enough resources, making you a coffee!");
                        }
                    }

                    switch (buyOption) {
                        case "1": //espresso
                            water -= 250;
                            beans -= 16;
                            cups--;
                            money += 4;
                            break;
                        case "2": //latte
                            water -= 350;
                            milk -= 75;
                            beans -= 20;
                            cups--;
                            money += 7;
                            break;
                        case "3": //cappuccino
                            water -= 200;
                            milk -= 100;
                            beans -= 12;
                            cups--;
                            money += 6;
                            break;
                        case "back":
                            break;
                        default:
                            break;
                    }
                    break;

                /* ===== FILL OPTION ==== */
                case "fill":
                    System.out.println("Write how many ml of water do you want to add: ");
                    water += scanner.nextInt();
                    System.out.println("Write how many ml of milk do you want to add: ");
                    milk += scanner.nextInt();
                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    beans += scanner.nextInt();
                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    cups += scanner.nextInt();
                    break;

                /* ===== TAKE OPTION ==== */
                case "take":
                    System.out.println("I gave you $" + money);
                    money = 0;
                    break;

                /* ===== REMAINING OPTION ==== */
                case "remaining":
                    printAmount(water, milk, beans, cups, money);
                    break;

                /* ===== REMAINING OPTION ==== */
                case "exit":
                    on = false;
                    break;

            }
        }
    }
}