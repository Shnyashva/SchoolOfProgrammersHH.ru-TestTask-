package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amountOfCVsInFirstStack = scanner.nextInt();
        int amountOfCVsInSecondStack = scanner.nextInt();
        int maxAmountOfSalaries = scanner.nextInt();
        scanner.nextLine();
        int[] salariesInFirstStack = new int[amountOfCVsInFirstStack];
        int[] salariesInSecondStack = new int[amountOfCVsInSecondStack];
        fillStacks(amountOfCVsInFirstStack, amountOfCVsInSecondStack, salariesInFirstStack, salariesInSecondStack,
                scanner);
        int sumOfSalaries = calculateSumOfSalaries(maxAmountOfSalaries, salariesInFirstStack, salariesInSecondStack,
                amountOfCVsInFirstStack, amountOfCVsInSecondStack);
        System.out.println(sumOfSalaries);
    }
    public static void fillStacks (int amountOfCVsInFirstStack, int amountOfCVsInSecondStack,
                                   int[] salariesInFirstStack, int[] salariesInSecondStack, Scanner scanner) {
        int biggestStack = findMaxAmountOfCVs(amountOfCVsInFirstStack, amountOfCVsInSecondStack);
        for (int i = 0; i < biggestStack; i++) {
            String scan = scanner.nextLine();
            String firstPart = scan.substring(0, scan.indexOf(" "));
            String secondPart = scan.substring(scan.indexOf(" ") + 1);
            if (((amountOfCVsInFirstStack >= biggestStack) && (i < amountOfCVsInSecondStack)) ||
                    ((amountOfCVsInFirstStack < biggestStack) && (i < amountOfCVsInFirstStack))) {
                salariesInFirstStack[i] = Integer.parseInt(firstPart);
                salariesInSecondStack[i] = Integer.parseInt(secondPart);
            } else if (amountOfCVsInFirstStack > amountOfCVsInSecondStack) {
                salariesInFirstStack[i] = Integer.parseInt(firstPart);
            } else {
                salariesInSecondStack[i] = Integer.parseInt(secondPart);
            }
        }
    }
    public static int calculateSumOfSalaries (int maxAmountOfSalaries, int[] salariesInFirstStack,
                                               int[] salariesInSecondStack, int amountOfCVsInFirstStack,
                                               int amountOfCVsInSecondStack) {
        int sum = 0;
        int i = 0;
        int j = 0;
        int numberOfCVs = 0;
        while (sum < maxAmountOfSalaries) {
            if (salariesInFirstStack[i] <= salariesInSecondStack[j]) {
                if ((sum + salariesInFirstStack[i]) <= maxAmountOfSalaries) {
                    sum += salariesInFirstStack[i];
                    numberOfCVs++;
                } else if ((sum + salariesInFirstStack[i]) > maxAmountOfSalaries) {
                    return numberOfCVs;
                }
                if ((i + 1) < salariesInFirstStack.length) {
                    i++;
                }

            } else if (salariesInFirstStack[i] > salariesInSecondStack[j]) {
                if ((sum + salariesInSecondStack[j]) <= maxAmountOfSalaries) {
                    sum += salariesInSecondStack[j];
                    numberOfCVs++;
                } else if ((sum + salariesInSecondStack[j]) > maxAmountOfSalaries) {
                    return numberOfCVs;
                }
                if ((j + 1) < salariesInSecondStack.length) {
                    j++;
                }
            }
        }
        return numberOfCVs;
    }

    public static int findMaxAmountOfCVs (int amountOfCVsInFirstStack, int amountOfCVsInSecondStack) {
        return Math.max(amountOfCVsInFirstStack, amountOfCVsInSecondStack);
    }
}