package com.example.a.foodcam;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabel;
import com.google.firebase.ml.vision.cloud.label.FirebaseVisionCloudLabelDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ImageRecognizer {
    private static List<String> blacklist = new ArrayList<String>(Arrays.asList("Food", "Cuisine", "Dish", "Plate", "Hand", "Finger"));
    private static HashMap<String, Double> labelTracker = new HashMap<>();
    private static String result = "Failed";
    public static String runImageRecognition(Bitmap mSelectedImage) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mSelectedImage);

        FirebaseVisionCloudLabelDetector detector = FirebaseVision.getInstance().getVisionCloudLabelDetector();

        detector.detectInImage(image)
                .addOnSuccessListener (
                        new OnSuccessListener<List<FirebaseVisionCloudLabel>>() {
                            @Override
                            public void onSuccess(List<FirebaseVisionCloudLabel> labels) {
                                processCloudImageLabelRecognitionResult(labels);
                            }
                        }
                )
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                e.printStackTrace();
                            }
                        });
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        return result;//labelTracker;
    }

    public static void processCloudImageLabelRecognitionResult(List<FirebaseVisionCloudLabel> labels) {
        labelTracker = new HashMap<>();
        double confidence = 0;
        for (FirebaseVisionCloudLabel label : labels) {
            labelTracker.put(label.getLabel(), (double) label.getConfidence());
            if (label.getConfidence() > confidence) {
                confidence = label.getConfidence();
                result = label.getLabel();
            }

        }
    }
}
