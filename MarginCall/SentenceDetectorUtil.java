package margincall;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceDetectorUtil {
 private SentenceModel model = null;
 SentenceDetectorME sentenceDetector = null;

 public SentenceDetectorUtil(String modelFile) {
  Objects.nonNull(modelFile);
  initSentenceModel(modelFile);
  initSentenceDetectorME();
 }

 private void initSentenceDetectorME() {
  sentenceDetector = new SentenceDetectorME(model);
 }

 private SentenceModel initSentenceModel(String file) {
  InputStream modelIn;
  try {
   modelIn = new FileInputStream(file);
  } catch (FileNotFoundException e) {
   System.out.println(e.getMessage());
   return null;
  }

  try {
   model = new SentenceModel(modelIn);
  } catch (IOException e) {
   e.printStackTrace();
  } finally {
   if (modelIn != null) {
    try {
     modelIn.close();
    } catch (IOException e) {
    }
   }
  }
  return model;
 }

 public String[] getSentencesFromFile(String inputFile) {
  String data = FileUtils.getFileDataAsString(inputFile);
  return sentenceDetector.sentDetect(data);
 }

 public String[] getSentences(String data) {
  return sentenceDetector.sentDetect(data);
 }

}