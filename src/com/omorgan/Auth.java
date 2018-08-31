package com.omorgan;

public class Auth {

    public boolean checkAge(int age) {

        boolean ageGood = false;

        if (age >= 13) {
            System.out.println("That is awesome. I remember when I was " + age + " years old.");
            ageGood = true;
        } else if ( age < 13 ) {
            int ageToBe = age;
            int addToAge = 0;
            while ( ageToBe != 13 ) {
                ageToBe++;
                addToAge++;
            }
            System.out.println("You are not old enough to play.");
            System.out.println("You can return to play this game in " + addToAge + " years.");

            ageGood = false;
        } else {
            System.out.println("You did not enter a valid number. ");
            System.out.println("Please enter numerical values without spaces only.");
        }
        return ageGood;
    }
}
