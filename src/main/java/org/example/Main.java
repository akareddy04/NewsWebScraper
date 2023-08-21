package org.example;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static OpenAiService service= new OpenAiService("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");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> providedUrls= new ArrayList<String>();
        while(true) {
            System.out.println("Enter the URL's one at a time for the news articles you wish to scrape. Enter 'EXIT' when you are done");
            String input=scanner.nextLine();
            if(input.equalsIgnoreCase("EXIT")) {
                break;
            }
            providedUrls.add(input);
        }
        for(String url: providedUrls) {
            System.out.println(generateReport(url));
            System.out.println();
        }

    }
    private static String generateSummary(String url) {
        StringBuilder summaryBuilder = new StringBuilder();
        String prompt="Please generate a concise summary based on the text content in this url: "+url;
        CompletionRequest completionRequest=CompletionRequest.builder().prompt(prompt).model("text-davinci-002").echo(false).temperature(0.0).maxTokens(4000).build();
       try {
           service.createCompletion(completionRequest).getChoices();
           List<com.theokanning.openai.completion.CompletionChoice> choices=service.createCompletion(completionRequest).getChoices();
           for (com.theokanning.openai.completion.CompletionChoice choice: choices) {
               summaryBuilder.append(choice.getText()).append("\n");
           }
           return summaryBuilder.toString();

       }catch(Exception e) {
           System.err.println("An error occurred while generating the summary: "+e.getMessage());
           return "";
       }

    }
    private static String generateTopic(String url) {
        StringBuilder themeBuilder = new StringBuilder();
        String prompt="Please determine the topic of the article in one word and the reason why: "+url;
        CompletionRequest completionRequest=CompletionRequest.builder().prompt(prompt).model("text-davinci-002").echo(false).temperature(0.0).maxTokens(4000).build();
        try {
            service.createCompletion(completionRequest).getChoices();
            List<com.theokanning.openai.completion.CompletionChoice> choices=service.createCompletion(completionRequest).getChoices();
            for (com.theokanning.openai.completion.CompletionChoice choice: choices) {
                themeBuilder.append(choice.getText()).append("\n");
            }
            return themeBuilder.toString();
        }catch(Exception e) {
            System.err.println("An error occurred while generating the summary: "+e.getMessage());
            return "";
        }
    }
    private static String determineValiditySource(String url) {
        StringBuilder validityBuilder = new StringBuilder();
        String prompt="Please determine if the source of the article is reliable or unreliable and the reason why: "+url;
        CompletionRequest completionRequest=CompletionRequest.builder().prompt(prompt).model("text-davinci-002").echo(false).temperature(0.0).maxTokens(4000).build();
        try {
            service.createCompletion(completionRequest).getChoices();
            List<com.theokanning.openai.completion.CompletionChoice> choices=service.createCompletion(completionRequest).getChoices();
            for (com.theokanning.openai.completion.CompletionChoice choice: choices) {
                validityBuilder.append(choice.getText()).append("\n");
            }
            return validityBuilder.toString();

        }catch(Exception e) {
            System.err.println("An error occurred while generating the summary: "+e.getMessage());
            return "";
        }
    }
    private static String extractKeyInformation(String url) {
        StringBuilder keyInformation = new StringBuilder();
        String prompt="Determine the key information of the following article such as people, events, places, dates, and more: "+url;
        CompletionRequest completionRequest=CompletionRequest.builder().prompt(prompt).model("text-davinci-002").echo(false).temperature(0.0).maxTokens(4000).build();
        try {
            service.createCompletion(completionRequest).getChoices();
            List<com.theokanning.openai.completion.CompletionChoice> choices=service.createCompletion(completionRequest).getChoices();
            for (com.theokanning.openai.completion.CompletionChoice choice: choices) {
                keyInformation.append(choice.getText()).append("\n");
            }
            return keyInformation.toString();

        }catch(Exception e) {
            System.err.println("An error occurred while generating the key information "+e.getMessage());
            return "";
        }
    }
    private static String extractTone(String url) {
        StringBuilder tone = new StringBuilder();
        String prompt="Determine the tone of the article and the reasons why "+url;
        CompletionRequest completionRequest=CompletionRequest.builder().prompt(prompt).model("text-davinci-002").echo(false).temperature(0.0).maxTokens(4000).build();
        try {
            service.createCompletion(completionRequest).getChoices();
            List<com.theokanning.openai.completion.CompletionChoice> choices=service.createCompletion(completionRequest).getChoices();
            for (com.theokanning.openai.completion.CompletionChoice choice: choices) {
                tone.append(choice.getText()).append("\n");
            }
            return tone.toString();

        }catch(Exception e) {
            System.err.println("An error occurred while generating the tone: "+e.getMessage());
            return "";
        }
    }

    private static String generateReport(String url) {
        String summary=generateSummary(url);
        String theme=generateTopic(url);
        String validitySource=determineValiditySource(url);
        String report = "Here is the following report for the article provided with the url" + url + ": " + "\n" +
                "Summary of the article: " + summary + "\n" +
                "Theme of the article: " + theme + "\n" +
                "Validity of the source: " + validitySource + "\n"+
                "Tone of Article: "+extractTone(url)+"\n"+
                "Key Information from the article: "+extractKeyInformation(url)+"\n";
        return report;

    }


}