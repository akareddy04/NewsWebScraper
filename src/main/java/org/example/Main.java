package org.example;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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
    private static String scrapeUrlText(String url) {
        try {
            Document document= Jsoup.connect(url).get();
            Element article=document.select("div.article-content").first();
            return article.text();
        }catch(Exception e) {
            System.err.println("An error occurred: "+e.getMessage());
            return "";
        }
    }
    private static String scrapeUrlDate(String url) {
        try {
            Document document=Jsoup.connect(url).get();
            Element article=document.select("span.date").first();
            return article.text();
        }catch(Exception e) {
            System.err.println("An error occurred: "+e.getMessage());
            return "";
        }
    }
    private static String scraperUrlAuthor(String url) {
        try {
            Document document=Jsoup.connect(url).get();
            Element article=document.selectFirst("span.author");
            return article.text();
        }catch(Exception e) {
            System.err.println("An error occurred: "+e.getMessage());
            return "";


        }
    }
    private static String scraperUrlHost(String url) {
        try {
            Document document=Jsoup.connect(url).get();
            java.net.URL javaUrl = new java.net.URL(url);
            return javaUrl.getHost();

        }catch(Exception e) {
            System.err.println(e.getMessage());
            return "";
        }
    }
    private static String gptAnswer(String articleText) {
        
    }


}