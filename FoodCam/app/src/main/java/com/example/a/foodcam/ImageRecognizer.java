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

import java.util.HashMap;
import java.util.List;

public class ImageRecognizer {
    private static HashMap<String, Double> labelTracker = new HashMap<>();
    public static HashMap<String, Double> runImageRecognition(Bitmap mSelectedImage) {
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
        StringBuilder sb = new StringBuilder();
        for (String key : labelTracker.keySet()) {
            sb.append(key + " " +  labelTracker.get(key).toString() + "\n");
        }
        Log.v("sumgood", sb.toString());
        return labelTracker;
    }

    public static void processCloudImageLabelRecognitionResult(List<FirebaseVisionCloudLabel> labels) {
        labelTracker = new HashMap<>();
        for (FirebaseVisionCloudLabel label : labels) {
            labelTracker.put(label.getLabel(), (double) label.getConfidence());
        }
    }
}
