package com.example.a.foodcam;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LabelParser {
    public static Food jsonToFood(String response, int servings) throws BadImageException{
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

        String description = desc.toString();
        System.out.println(description);

        return null;
    }
}
