package aia;

import java.util.Scanner;

public class drawing{

    public static String command;
    public static int width = 0;
    public static int height = 0;
    public static char[][] cvs = new char[height][width];

    public static void main(String[] args) {

        //Create Guidelines and looping while input != Q
        System.out.println("Guidelines : ");
        System.out.println("Command C = Create New Canvas");
        System.out.println("Command L = Create New Line");
        System.out.println("Command R = Create New Rectangle");
        System.out.println("Command B = Create New Bucket Fill");
        System.out.println("Command Q = Quit");

        do {
            input();
        }
        while(!command.equals("Q"));
    }

    /**
     * Create method with OOP Implementation
     */
    public static void input() {
        Scanner in3 = new Scanner(System.in);
        System.out.print("Type your Command : ");
        command=in3.nextLine();

        if(command.equals("C")){
            sizeCanvas();
            printCanvas();
        }
        else if(command.equals("L")){
            line();
        }
        else if(command.equals("R")){
            rectangle();
        }
        else if(command.equals("B")){
            bucket();
        }
        else if(command.equals("Q")){
            System.out.println("Thank you!");
        }
        else{
            System.out.println("Your command is incorrect! See the guidelines!");
        }
    }

    //Type customize size canvas
    public static void sizeCanvas() {

        Scanner in = new Scanner(System.in);

        System.out.print("Type Width, Height  : ");
        width = in.nextInt();
        height = in.nextInt();

        cvs = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cvs[i][j] = ' ';
            }
        }
    }

    public static void printCanvas(){
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print("|");
            for (int j = 0; j < width; j++) {
                System.out.print(cvs[i][j]);
            }
            System.out.println("|");
        }
        for (int i = 0; i < width + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void line() {

        boolean validInput=false;
        Scanner in2 = new Scanner(System.in);

        do{
            System.out.print("Type coordinat X1, Y1, X2, Y2 : ");
            int x1 = in2.nextInt();
            int y1 = in2.nextInt();
            int x2 = in2.nextInt();
            int y2 = in2.nextInt();

            //Print Vertical & Horizontal Line
            if (x1 == x2 || y1 == y2) {
                if (x1 <= width && x2 <= width && y1 <= height && y2 <= height) {
                    for (int i = y1 - 1; i < y2; i++) {
                        for (int j = x1 - 1; j < x2; j++) {
                            cvs[i][j] = 'x';
                        }
                    }
                    printCanvas();
                    validInput=true;
                } else {
                    System.out.println("Your input is not Valid !");
                }
            } else if (x1 != x2 && y1 != y2) {
                System.out.println("This program only supported vertical and horizontal line.");
            }
        }
        while (!validInput==true);
    }

    public static void rectangle(){

        boolean validInput=false;
        Scanner in2 = new Scanner(System.in);

        do {
            System.out.print("Type Coordinat X1, Y1, X2, Y2 : ");
            int x1 = in2.nextInt();
            int y1 = in2.nextInt();
            int x2 = in2.nextInt();
            int y2 = in2.nextInt();

            //Print Rectangle Line
            if (x1 <= width && x2 <= width && y1 <= height && y2 <= height) {
                for (int i = y1 - 1; i < y2; i++) {
                    for (int j = x1 - 1; j < x2; j++) {
                        if (i == y1 - 1 || i == y2 - 1 || j == x1 - 1 || j == x2 - 1) {
                            cvs[i][j] = 'x';
                        }
                    }
                }
                printCanvas();
                validInput=true;
            } else {
                System.out.println("Your input is not Valid !");
            }
        }
        while (!validInput==true);
    }

    public static void bucket(){

        System.out.println("This menu is under development! See you soon!");

    }
}

