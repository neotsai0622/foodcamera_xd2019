package com.example.a.foodcam;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestParse {

    @Test
    public void testBar() {
        StringBuilder text = new StringBuilder();
        try {
            Scanner in = new Scanner(new FileReader("src/test/java/com/example/a/foodcam/bar.txt"));
            while (in.hasNextLine()) {
                text.append(in.nextLine());
                text.append("\n");
            }

            Food expected = new Food("bar", 100, 3, 7, 1, 18, 35);
            Assert.assertEquals(expected, LabelParser.jsonToFood(text.toString(), "bar"));
        } catch (FileNotFoundException e) {
            Assert.fail();
        } catch (BadImageException e) {
            Assert.fail();
        }
    }

    @Test
    public void testYerba() {
        StringBuilder text = new StringBuilder();
        try {
            Scanner in = new Scanner(new FileReader("src/test/java/com/example/a/foodcam/mate.txt"));
            while (in.hasNextLine()) {
                text.append(in.nextLine());
                text.append("\n");
            }

            Food expected = new Food("yerba", 100, 0, 26, 0, 26, 20);
            Assert.assertEquals(expected, LabelParser.jsonToFood(text.toString(), "yerba"));
        } catch (FileNotFoundException e) {
            Assert.fail();
        } catch (BadImageException e) {
            Assert.fail();
        }
    }

    @Test
    public void testJuice() {
        StringBuilder text = new StringBuilder();
        try {
            Scanner in = new Scanner(new FileReader("src/test/java/com/example/a/foodcam/juice.txt"));
            while (in.hasNextLine()) {
                text.append(in.nextLine());
                text.append("\n");
            }

            Food expected = new Food("juice", 90, 0, 22, 0.2, 23, 20);
            Assert.assertEquals(expected, LabelParser.jsonToFood(text.toString(), "juice"));
        } catch (FileNotFoundException e) {
            Assert.fail();
        } catch (BadImageException e) {
            Assert.fail();
        }
    }
}
