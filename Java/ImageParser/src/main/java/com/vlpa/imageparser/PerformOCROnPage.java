package com.vlpa.imageparser;

import com.aspose.ocr.AsposeOCR;
//import com.aspose.ocr.examples.License.SetLicense;
//import com.aspose.ocr.examples.Utils;

import java.io.IOException;

public class PerformOCROnPage {

    public static void main(String[] args) {
        //SetLicense.main(null);
        // ExStart:1
        // The path to the documents directory.
//        String dataDir = Utils.getSharedDataDir(PerformOCROnPage.class);

        // The image path
        String imagePath = "D:/Vladimir/Repositories/github/Java/ImageParser/src/main/resources/p4.png";

        //Create api instance
        AsposeOCR api = new AsposeOCR();

        // Recognize page by full path to file
        try {
            String result = api.RecognizePage(imagePath);
            System.out.println("Result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ExEnd:1
    }
}