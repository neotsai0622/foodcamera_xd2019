package com.example.a.foodcam;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class TestParse {

    @Test
    public void testAccess() {
        StringBuilder text = new StringBuilder();
        try {
            Scanner in = new Scanner(new FileReader("src/test/java/com/example/a/foodcam/sample.json"));
            while (in.hasNext()) {
                text.append(in.next());
            }
            LabelParser.jsonToFood(text.toString(), 1);
        } catch (FileNotFoundException e) {
            Assert.fail();
        } catch (BadImageException e) {
            Assert.fail();
        } finally {
            System.out.println(text.toString());
        }
        // Assert.assertEquals("ABBEY\nROAD NW8\nCITY OF WESTMINSTER\n", text.toString());
    }
}
