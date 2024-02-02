/*
 * A program to find unsafe banks using a 2D array
 */

import java.util.Scanner;
import java.util.Arrays;

public class BankSafety {

    // declare and initialise constant variables
    static final int BANK_ID = 0;
    static final int BANK_LOANS = 1;

    public static void main(String[] args) {

        // declare and initialise 2D array
        double[][][] banksArray = new double[][][] {
                // row = {curt balance, #loans}, {loan#1, amount}, {loan#2, amount}
                { { 25, 2 }, { 1, 100.5 }, { 4, 320.5 } },
                { { 125, 2 }, { 2, 40 }, { 3, 85 } },
                { { 175, 2 }, { 0, 125 }, { 3, 75 } },
                { { 75, 1 }, { 0, 125 }, },
                { { 181, 0 }, { 2, 125 }, }
        };

        // display length of array
        System.out.println("Number of Banks in array: " + banksArray.length);

        // display values in array
        for (int i = 0; i < banksArray.length; i++) {
            for (int k = 0; k < banksArray[i].length; k++) {
                for (int j = 0; j < banksArray[i][k].length; j++) {
                    System.out.print(banksArray[i][k][j]);
                    System.out.print(" ");
                }
                System.out.print("  ");
            }
            System.out.println();
        }

        // declare and initialise variables
        int limit = 201;
        boolean[] unsafeBank = checkBanks(banksArray, limit);

        // display unsafe banks
        System.out.print("The following banks are unsafe: ");
        boolean unsafe = false;

        // iterate through array value, calling checkBanks() method each iteration
        // if checkBanks() = true
        for (int i = 0; i < unsafeBank.length; i++) {
            if (unsafeBank[i]) {
                System.out.print(i + " ");
                unsafe = true;
            }
        }
        // if checkBanks() = false
        if (!unsafe) {
            System.out.println("No banks are unsafe");
        }
    }

    // checkBanks() method
    public static boolean[] checkBanks(double[][][] bankAssets, int limit) {

        boolean[] unsafeBank = new boolean[bankAssets.length];
        boolean safe = false;

        // loop through each bank
        while (!safe) {
            // reset safe value each loop
            safe = true;
            // iterate through each bank to calculate each banks total balance
            for (int banks = 0; banks < bankAssets.length; banks++) {
                double totalBalance = bankAssets[banks][0][BANK_ID];

                // iterate through each bank thats borrowed money from current bank
                for (int loanedBanks = 1; loanedBanks < bankAssets[banks].length; loanedBanks++) {
                    int index = (int) bankAssets[banks][loanedBanks][BANK_ID];

                    // if loaned bank is safe, then add loan to total balance
                    if (!unsafeBank[index]) {
                        totalBalance += bankAssets[banks][loanedBanks][BANK_LOANS];
                    }
                }
                // if total balance is greater than limit AND bank is safe
                if (totalBalance < limit && !unsafeBank[banks]) {
                    unsafeBank[banks] = true;
                    safe = false;
                }
            }
        }
        return unsafeBank;
    }
}
