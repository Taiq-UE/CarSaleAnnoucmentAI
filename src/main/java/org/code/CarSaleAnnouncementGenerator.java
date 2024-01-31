package org.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarSaleAnnouncementGenerator
{
    private String brand;
    private String model;
    private int year;
    private int price;
    private String message;

    public CarSaleAnnouncementGenerator(JSONReader reader)
    {
        this.brand = reader.getBrand();
        this.model = reader.getModel();
        this.year = reader.getYear();
        this.price = reader.getPrice();
        message = "Generuj przekonujące niedługie ogłoszenie sprzedaży samochodu " + year + " " + brand + " " + model + " za " + price + "zł." +
                "Nie podawaj żadnych danych kontaktowych i nie używaj emoji\\n";
    }

    public String chatGPT() {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = ""; // API key goes here
        String model = "gpt-3.5-turbo";

        try {
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Bearer " + apiKey);
            con.setRequestProperty("Content-Type", "application/json");

            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return extractContentFromResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractContentFromResponse(String response) {
        int startMarker = response.indexOf("content")+11;
        int endMarker = response.indexOf("\"", startMarker);
        return response.substring(startMarker, endMarker);
    }
}
