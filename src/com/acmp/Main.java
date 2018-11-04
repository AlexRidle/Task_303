package com.acmp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer> numbersAL = new ArrayList<>();
    private static String number;
    private static int sum;

    public static void main(String[] args) throws IOException, CustomException {
        readFile();
        for (int i = 0; i < numbersAL.size(); i++) {
            countSumWithoutNum(i);
        }
        writeFile();
    }

    private static void readFile() throws IOException, CustomException {
        File file = new File("INPUT.txt");
        Scanner inputFile = new Scanner(file);
        number = inputFile.nextLine();
        splitNumber();
        if (2 > numbersAL.size() || numbersAL.size() > 50) {
            throw new CustomException("Please, check INPUT.txt file. 2 <= N <= 50");
        }
    }

    private static void splitNumber() {
        char[] numbers = number.toCharArray();
        int num;
        for (char number : numbers) {
            num = Character.getNumericValue(number);
            numbersAL.add(num);
        }
    }

    private static void countSumWithoutNum(int index) {
        ArrayList<Integer> tempNums = new ArrayList<>(numbersAL);
        tempNums.remove(index);
        if (index != 0) {
            int tempSum = getSum(tempNums);
            sum = tempSum > sum ? tempSum : sum;
        } else {
            sum = getSum(tempNums);
        }
    }

    private static int getSum(ArrayList<Integer> array) {
        int sum = 0;
        boolean isPlus = true;
        for (int i : array) {
            if (isPlus) {
                sum += i;
            } else {
                sum += -i;
            }
            isPlus = !isPlus;
        }
        return sum;
    }

    private static void writeFile() {
        File file = new File("OUTPUT.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }
}

class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }

}