package com.example.a.foodcam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.text.similarity.JaroWinklerDistance;

import java.util.HashMap;
import java.util.Scanner;

public class LabelParser {
    public static Food jsonToFood(String response, String name) throws BadImageException{
        HashMap<String, Double> nutritionalValues = new HashMap<>();
        JaroWinklerDistance calc = new JaroWinklerDistance();
        String next;
        String nutrient;

        Scanner resp = new Scanner(response);

        while (resp.hasNextLine()) {
            next = resp.nextLine().toLowerCase();
            for (int i = 0; i < Food.nutrients.length; i++) {
                nutrient = Food.nutrients[i];
                if (next.contains(nutrient)) {
                    next = next.replaceFirst("(\\S)*[0-9]*(\\S)*%", "");
                    next = next.replaceAll("[^a-zA-Z0-9.]", "");
                    System.out.println(next);
                    if (next.matches(".*[0-9. ]+9")) {
                        next = next.substring(0, next.lastIndexOf('9'))
                                + "g" + next.substring(next.lastIndexOf('9') + 1);
                    }
                    System.out.println(next);


                    next = next.replaceAll("[^0-9.]", "");
                    if (!next.equals("") && !nutritionalValues.containsKey(i % 2 == 0 ? nutrient : Food.nutrients[i - 1])) {
                        nutritionalValues.put(i % 2 == 0 ? nutrient : Food.nutrients[i - 1], Double.valueOf(next));
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < Food.nutrients.length; i += 2) {
            if (!nutritionalValues.containsKey(Food.nutrients[i])) {
                nutritionalValues.put(Food.nutrients[i], 0.0);
            }
        }

        System.out.println(nutritionalValues);

        return new Food(name, nutritionalValues.get(Food.nutrients[0]),
                nutritionalValues.get(Food.nutrients[2]), nutritionalValues.get(Food.nutrients[4]),
                nutritionalValues.get(Food.nutrients[6]), nutritionalValues.get(Food.nutrients[8]),
                nutritionalValues.get(Food.nutrients[10]));
    }

    private static String getDescription(String response) throws BadImageException{
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();

        JsonElement tree = jsonParser.parse(response);

        if (!tree.isJsonObject()) {
            throw new BadImageException();
        }

        JsonObject object = tree.getAsJsonObject();
        JsonElement temp = object.get("responses");

        if (!temp.isJsonArray()) {
            throw new BadImageException();
        }

        JsonArray array = temp.getAsJsonArray();
        temp = array.get(0);

        if (!temp.isJsonObject()) {
            throw new BadImageException();
        }

        object = temp.getAsJsonObject();
        temp = object.get("textAnnotations");

        if (!temp.isJsonArray()) {
            throw new BadImageException();
        }

        array = temp.getAsJsonArray();
        temp = array.get(0);

        if (!temp.isJsonObject()) {
            throw new BadImageException();
        }

        object = temp.getAsJsonObject();
        JsonElement desc = object.get("description");

        return desc.toString();
    }
}
