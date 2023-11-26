package org.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//JSON file reader class
//JSON file should include: brand, model, year, price
public class JSONReader
{
    private String brand;
    private String model;
    private int year;
    private int price;

    public JSONReader(String brand, String model, int year, int price)
    {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public JSONReader()
    {
        this.brand = "";
        this.model = "";
        this.year = 0;
        this.price = 0;
    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }

    public int getYear()
    {
        return year;
    }

    public int getPrice()
    {
        return price;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public void readJSON(String json)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(json);
            this.brand = node.get("brand").asText();
            this.model = node.get("model").asText();
            this.year = node.get("year").asInt();
            this.price = node.get("price").asInt();
        }
        catch (Exception e)
        {
            System.out.println("Error reading JSON file. Details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String toString()
    {
        return "Brand: " + this.brand + "\nModel: " + this.model + "\nYear: " + this.year + "\nPrice: " + this.price;
    }
}
