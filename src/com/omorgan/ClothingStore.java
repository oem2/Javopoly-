package com.omorgan;

import java.sql.*;
import java.util.Map;
import java.util.Date;
import java.util.Timer;
import java.util.Random;
import java.util.HashMap;
import java.util.TimerTask;
import java.text.DecimalFormat;

public class ClothingStore {



    public static void priceList() {

        //HashMap for storing prices of items in store
        Map<String, Double> itemPrices = new HashMap<>();

        //Add items to store
        itemPrices.put("Black Denim Jeans", 75.99);
        itemPrices.put("White Cotton Socks", 7.99);
        itemPrices.put("Brown Linen Shorts", 64.89);
        itemPrices.put("Navy Blue Wool Suit", 599.99);
        itemPrices.put("Tan Wool Sports Jacket", 399.99);
        itemPrices.put("White Linen Suit", 279.99);

        //store quantity of items in store
        int blackDenimJeans = 12;
        int whiteCottonSocks = 42;
        int brownLinenShorts = 5;
        int navyBlueWoolSuit = 56;
        int tanWoolSportsJacket = 83;
        int whiteLinenSuit = 23;
    }

    public static double generatePrice(double min, double max) {
        double diff = max - min;
        DecimalFormat df = new DecimalFormat("#.##");
        double randomValue = min + Math.random() * diff;

        return randomValue;
    }


}
