package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> providedUrls= new ArrayList<String>();
        System.out.println("Enter the URL's one at a time for the news articles you wish to scrape. Enter 'EXIT' when you are done");
        while(true) {
            String input=scanner.nextLine();
            if(input.equalsIgnoreCase("EXIT")) {
                break;
            }
            providedUrls.add(input);
        }
    }
}