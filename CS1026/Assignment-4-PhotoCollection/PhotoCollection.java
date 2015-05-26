// CS1026B Assignment 4
// This class models a collection of photos labeled with a caption and watermark.

import java.util.*;
import java.io.*;
public class PhotoCollection { 
  
  ///////////////////// fields /////////////////////
  private LabeledPhoto[] photoArray; // This array will hold the LabeledPhoto objects created from a file.
  private int numPhoto = 0; // The number of LabeledPhoto objects.
  
  ///////////////////// constructors /////////////////////
  public PhotoCollection(String fileName) {
    
    /* Initialize variables for objects/arrays using a SimpleReader object.
    Arrays will contain photo information or labeled photos.*/ 
    SimpleReader reader = new SimpleReader(fileName);
    String[] lineArray = reader.readFile();
    numPhoto = reader.getFileLength();
    photoArray = new LabeledPhoto[numPhoto];

    LabeledPhoto labeledPhoto;
    StringTokenizer tokenizer;
    String identifier;
    String day;
    String month;
    String year;
    String category;
    String caption;
    String photoFile;
    int intDay;
    int intMonth;
    int intYear;
    
    // Loop through lineArray & get photo information using StringTokenizer. 
    for (int i = 0; i < numPhoto; i++) {
      tokenizer = new StringTokenizer(lineArray[i]);
      identifier = tokenizer.nextToken();
      day = tokenizer.nextToken();
      month = tokenizer.nextToken();
      year = tokenizer.nextToken();
      category = tokenizer.nextToken();
      caption = tokenizer.nextToken();
      photoFile = tokenizer.nextToken();
    
      // Parse string arguments as integers.
      intDay = Integer.parseInt(day);
      intMonth = Integer.parseInt(month);
      intYear = Integer.parseInt(year);
      
      // Create LabeledPhoto object using photo information & store in photoArray.
      labeledPhoto = new LabeledPhoto(identifier, intDay, intMonth, intYear, category, caption, photoFile);
      photoArray[i] = labeledPhoto; 
    }
  }
  
  ///////////////////// methods /////////////////////
  
  // Display photo list as text, one photo per line.
  public void listPhotoInformation() {
    for (int i = 0; i < photoArray.length; i++) {
      System.out.println(photoArray[i].toString());
    }
  }
  
  // Display all photos in photoArray. 
  public void showPhotos() {      
    for (int i = 0; i < photoArray.length; i++) { 
     photoArray[i].getPhotoPic().show(); 
    }
  }
  
  // Show all photos belonging to the category passed as a parameter.
  public void showPhotos(String theCategory) {
      for (int i = 0; i < photoArray.length; i++) { 
        if (photoArray[i].getCategory().equals(theCategory))
          photoArray[i].getPhotoPic().show(); 
      }   
    }
  
  // Store labeled photos as .jpg files in a directory whose name is passed as a parameter.
  public void storePhotos(String directory) {
    char end = directory.charAt(directory.length() - 1);
    if (end != '/' || end != '\\')
      directory = directory + '/';
    File directoryFile = new File(directory);
    if (!directoryFile.exists())
      directoryFile.mkdirs();
    
    String newDirectory; // Declare variable before looping through photoArray.
    for (int i = 0; i < photoArray.length; i++) {
      newDirectory = directory + photoArray[i].getId() + "_" + photoArray[i].getCategory() + ".jpg";
      photoArray[i].getPhotoPic().write(newDirectory);
    }         
  }

  // Sort photos in photoArray by date via bubble sort technique.   
  public void sortPhotosByDate() {
   LabeledPhoto temp = null;
   for (int i = 0; i < photoArray.length; i++) {
     for (int j = 0; j < photoArray.length-1; j++) {
       if (photoArray[j].isNewer(photoArray[j+1])) {
         temp = photoArray[j];
         photoArray[j] = photoArray[j+1];
         photoArray[j+1] = temp;
       }
     }
   } 
  }
}