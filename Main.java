import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amountOfCVsInFirstStack = scanner.nextInt();
        int amountOfCVsInSecondStack = scanner.nextInt();
        int maxAmountOfSalaries = scanner.nextInt();
        scanner.nextLine();
        int theBiggestStack = Math.max(amountOfCVsInFirstStack, amountOfCVsInSecondStack);
        int[] salariesInFirstStack = new int[amountOfCVsInFirstStack];
        int[] salariesInSecondStack = new int[amountOfCVsInSecondStack];
        fillStacks(amountOfCVsInFirstStack, amountOfCVsInSecondStack, salariesInFirstStack, salariesInSecondStack,
                scanner, theBiggestStack);
        int maxAmountOfCVs = calculateMaxAmountOfCVs(salariesInFirstStack, salariesInSecondStack, maxAmountOfSalaries);
        System.out.println(maxAmountOfCVs);
    }

    public static void fillStacks(int amountOfCVsInFirstStack, int amountOfCVsInSecondStack,
                                  int[] salariesInFirstStack, int[] salariesInSecondStack, Scanner scanner,
                                  int biggestStack) {
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

    public static int calculateMaxAmountOfSummedElementsInFirstStack(int maxSum, int[] firstStack) {
        int sum = 0;
        int counter;
        for (counter = 0; counter < firstStack.length; counter++) {
            if (sum + firstStack[counter] <= maxSum) {
                sum += firstStack[counter];
            } else {
                return counter;
            }
        }
        return counter;
    }

    public static int calculateMaxAmountOfSummedElementsInSecondStack(int maxSum, int[] secondStack) {
        int sum = 0;
        int counter;
        for (counter = 0; counter < secondStack.length; counter++) {
            if (sum + secondStack[counter] <= maxSum) {
                sum += secondStack[counter];
            } else {
                return counter;
            }
        }
        return counter;
    }

    public static int calculateMaxAmountOfSummedElementsInBothStacks(int maxSum, int[] firstStack, int[] secondStack) {
        int sum = 0;
        int i = 0;
        int j = 0;
        int counter = 0;
        while (sum <= maxSum) {
            if (firstStack[i] < secondStack[j]) {
                if (sum + firstStack[i] <= maxSum) {
                    sum += firstStack[i];
                    counter++;
                } else {
                    return counter;
                }
                if (sum + secondStack[j] <= maxSum) {
                    sum += secondStack[j];
                    counter++;
                } else {
                    return counter;
                }
            } else {
                if (sum + secondStack[j] <= maxSum) {
                    sum += secondStack[j];
                    counter++;
                } else {
                    return counter;
                }
                if (sum + firstStack[i] <= maxSum) {
                    sum += firstStack[i];
                    counter++;
                } else {
                    return counter;
                }
            }
            if (i + 1 < firstStack.length) {
                i++;
            } else {
                if (j + 1 < secondStack.length) {
                    j++;
                    for (int k = j; k < secondStack.length; k++) {
                        if (sum + secondStack[k] <= maxSum) {
                            sum += secondStack[k];
                            counter++;
                        } else {
                            return counter;
                        }
                    }
                }
                return counter;
            }
            if (j + 1 < secondStack.length) {
                j++;
            } else {
                if (i + 1 < firstStack.length) {
                    for (int k = i; k < firstStack.length; k++) {
                        if (sum + firstStack[k] <= maxSum) {
                            sum += firstStack[k];
                            counter++;
                        } else {
                            return counter;
                        }
                    }
                }
                return counter;
            }
        }
        return counter;
    }


    public static int calculateMaxAmountOfCVs(int[] firstStack, int[] secondStack, int maxSum) {
        int MaxAmountOfSummedElementsInFirstStack = calculateMaxAmountOfSummedElementsInFirstStack(maxSum, firstStack);
        int MaxAmountOfSummedElementsInSecondStack =
                calculateMaxAmountOfSummedElementsInSecondStack(maxSum, secondStack);
        int MaxAmountOfSummedElementsInBothStacks =
                calculateMaxAmountOfSummedElementsInBothStacks(maxSum, firstStack, secondStack);

        if (MaxAmountOfSummedElementsInFirstStack >= MaxAmountOfSummedElementsInSecondStack &&
                MaxAmountOfSummedElementsInFirstStack >= MaxAmountOfSummedElementsInBothStacks) {
            return calculateMaxAmountOfCVsStartFromFirstStack(maxSum, firstStack, secondStack);
        } else if (MaxAmountOfSummedElementsInSecondStack > MaxAmountOfSummedElementsInFirstStack &&
                MaxAmountOfSummedElementsInSecondStack > MaxAmountOfSummedElementsInBothStacks) {
            return calculateMaxAmountOfCVsStartFromSecondStack(maxSum, firstStack, secondStack);
        } else {
            return MaxAmountOfSummedElementsInBothStacks;
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
