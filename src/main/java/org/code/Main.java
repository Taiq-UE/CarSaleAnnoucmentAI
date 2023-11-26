package org.code;

//Using JSON format, automatically generate sale announcements for a car dealership.
//JSON file should include: brand, model, year, price
//The program should automatically generate sale description on given JSON info.
//The program should also be able to read the JSON file and print out the sale description using AI such as ChatGPT.
//The program should generate different descriptions.

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main
{
    public static void main(String[] args) throws IOException {
        Path filePath = Paths.get("C:\\Users\\kamil\\IdeaProjects\\CarSaleAnnoucmentAI\\car.json");
        String jsonContent = new String(Files.readAllBytes(filePath));

        JSONReader reader = new JSONReader();
        reader.readJSON(jsonContent);
        System.out.println(reader.toString());

        CarSaleAnnouncementGenerator generator = new CarSaleAnnouncementGenerator(reader);
        String contentToWrite = generator.chatGPT();
        contentToWrite = contentToWrite.replace("\\n", System.lineSeparator());
        System.out.println(contentToWrite);

        String filePath2 = "C:\\Users\\kamil\\IdeaProjects\\CarSaleAnnoucmentAI\\output.txt";
        try (FileWriter fileWriter = new FileWriter(filePath2)) {
            fileWriter.write(contentToWrite);
            System.out.println("Text written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
