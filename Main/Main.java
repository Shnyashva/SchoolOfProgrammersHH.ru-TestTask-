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
        int answer = calculateLargestSumOfElementsInEachStack(salariesInFirstStack, salariesInSecondStack, maxAmountOfSalaries);
        System.out.println(answer);
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
                                               int[] salariesInSecondStack) {
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

    public static int calculateLargestSumOfElementsInEachStack (int[] firstStack, int[] secondStack, int maxSum) {
        //Separate counters and summing
        int sumInFirstStack = 0;
        int sumInSecondStack = 0;
        int sumOfBothStacksOneByOne = 0;
        int counter1;
        int counter2;
        int counter3 = 0;
        for (counter1 = 0; counter1 < firstStack.length; counter1++) {
            if (sumInFirstStack + firstStack[counter1] <= maxSum) {
                sumInFirstStack += firstStack[counter1];
            } else {
                break;
            }
        }
        for (counter2 = 0; counter2 < secondStack.length; counter2++) {
            if  (sumInSecondStack + secondStack[counter2] <= maxSum) {
                sumInSecondStack += secondStack[counter2];
            } else {
                break;
            }
        }
        for (int j = 0; true; j++) {
            if (firstStack[j] < secondStack[j]) {
                if (sumOfBothStacksOneByOne + firstStack[j] <= maxSum) {
                    sumOfBothStacksOneByOne += firstStack[j];
                    counter3++;
                } else {
                    break;
                }
                if (sumOfBothStacksOneByOne + secondStack[j] <= maxSum) {
                    sumOfBothStacksOneByOne += secondStack[j];
                    counter3++;
                } else {
                    break;
                }
            } else {
                if (sumOfBothStacksOneByOne + secondStack[j] <= maxSum) {
                    sumOfBothStacksOneByOne += secondStack[j];
                    counter3++;
                } else {
                    break;
                }
                if (sumOfBothStacksOneByOne + firstStack[j] <= maxSum) {
                    sumOfBothStacksOneByOne += firstStack[j];
                    counter3++;
                } else {
                    break;
                }
            }
        }
        if (counter1 >= counter2 && counter1 >= counter3) {
            return calculateMaxAmountOfCVsStartFromFirstStack(maxSum, firstStack, secondStack);
        } else if (counter2 > counter1 && counter2 > counter3) {
            return calculateMaxAmountOfCVsStartFromSecondStack(maxSum, firstStack, secondStack);
        } else {
            return calculateSumOfSalaries(maxSum, firstStack, secondStack);
        }
    }
    public static int calculateMaxAmountOfCVsStartFromFirstStack(int maxAmountOfSalaries, int[] salariesInFirstStack,
                                                                  int[] salariesInSecondStack) {
        int sum = 0;
        int numberOfCVs = 0;
        while (sum < maxAmountOfSalaries) {
            for (int j : salariesInFirstStack) {
                if ((sum + j) <= maxAmountOfSalaries) {
                    sum += j;
                    numberOfCVs++;
                } else {
                    return numberOfCVs;
                }
            }
            for (int j : salariesInSecondStack) {
                if ((sum + j) <= maxAmountOfSalaries) {
                    sum += j;
                    numberOfCVs++;
                } else {
                    return numberOfCVs;
                }
            }
        }
        return numberOfCVs;
    }

    public static int calculateMaxAmountOfCVsStartFromSecondStack(int maxAmountOfSalaries, int[] salariesInFirstStack,
                                                                 int[] salariesInSecondStack) {
        int sum = 0;
        int numberOfCVs = 0;
        while (sum < maxAmountOfSalaries) {
            for (int j : salariesInSecondStack) {
                if ((sum + j) <= maxAmountOfSalaries) {
                    sum += j;
                    numberOfCVs++;
                } else {
                    return numberOfCVs;
                }
            }
            for (int j : salariesInFirstStack) {
                if ((sum + j) <= maxAmountOfSalaries) {
                    sum += j;
                    numberOfCVs++;
                } else {
                    return numberOfCVs;
                }
            }
        }
        return numberOfCVs;
    }
}