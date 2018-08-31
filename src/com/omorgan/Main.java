package com.omorgan;

import java.text.DecimalFormat;
import java.util.Scanner;
import com.omorgan.ClothingStore.*;
import com.omorgan.DataModel.*;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) {

        //Variable for new line
        String newLine = System.getProperty("line.separator");
        Scanner reader = new Scanner(System.in);

        //Welcome user to the game
        System.out.println("Hello player. Welcome to our text based");
        System.out.println("store game. In this game you can buy and sell");
        System.out.println("items to an AI store. You have 5 minutes to");
        System.out.println("make as much money as you can." + newLine);

        System.out.println("What is your username?");
        String uname = reader.next();

        System.out.println(newLine + "Hello " + uname + ", nice to meet you. How old are you?" + newLine);

        System.out.println("How old are you " + uname + "?");
        int age = reader.nextInt();

        //Initialize class
        DataModel dmodel = new DataModel(uname, age);
        dmodel.createDBGameTables();

        while (dmodel.checkIfUserExists(uname)) {
            System.out.println("That username is not available. Please try again.");
            uname = reader.next();
        }

        dmodel.insertUserInfo(uname, age);

        double price = ClothingStore.generatePrice(37.90, 80.09);
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(price));
    }

}
