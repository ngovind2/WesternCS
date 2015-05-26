// CS1026B Assignment 4
// This class models an image's information in order to generate a new labeled photo with a caption and watermark. 
 
public class LabeledPhoto {
  
  ///////////////////// fields /////////////////////
  private String id;
  private String category;
  private int year;
  private int month; 
  private int day;
  private String caption; 
  private String photoFile;
  private Picture photoPic; 
  
  // Constant static attributes
  public final static int CAPTION_SIZE = 50;
  public final static int CAPTION_FONT = 20; 
  public final static int WATERMARK_FONT = 12;
  public final static int ORIGIN = 0;
  public final static int TEXT_START = 30;
    
  ///////////////////// constructors /////////////////////
  public LabeledPhoto(String theId, int theDay, int theMonth, int theYear, 
                      String theCategory, String theCaption, String thePhotoFile) {
    
    // Initialize attributes.
    this.id = theId;
    this.day = theDay;
    this.month = theMonth;
    this.year = theYear;
    this.category = theCategory;
    this.caption = theCaption;
    this.photoFile = thePhotoFile;
    
    // Create a larger labeled photo with a caption and watermark.
    createLabeledPhoto();
  }
    
  ///////////////////// methods /////////////////////
  
  // Allows user to create a labeled photo from an original image. Image has a caption and watermark. 
  private void createLabeledPhoto() {
    Picture original = new Picture(photoFile);
    photoPic = new Picture(original.getWidth(), original.getHeight() + CAPTION_SIZE); 
    photoPic.copyPictureTo(original, ORIGIN, CAPTION_SIZE);
    
    // Caption and watermark are added via Picture methods.
    photoPic.drawCenteredString(caption, TEXT_START, CAPTION_FONT);
    String watermark = new String(category + " " + day + "/" + month + "/" + year);
    photoPic.drawInfoWatermark(watermark, WATERMARK_FONT);
  }
  
  // Accessor methods for all attributes:
  public String getId() { return id; }
  public String getCategory() { return category; }
  public int getYear() { return year; }
  public int getMonth() { return month; }
  public int getDay() { return day; }
  public String getCaption() { return caption; }
  public String getPhotoFile() { return photoFile; }
  public Picture getPhotoPic() { return new Picture(photoPic); }
  
  // Modifier methods:
  
  // Allows users to set a new category and update image with corresponding watermark.
  public void setCategory(String newCategory) {
    category = newCategory;
    this.createLabeledPhoto();
  }
  
  // Allows users to update the labeled photo with a new caption. 
  public void setCaption(String newCaption) {
    caption = newCaption;
    this.createLabeledPhoto(); 
  }
  
  // Prints information about the image to the console. 
  public String toString() {
    String output = caption + "(" + id + ", " + category + ", " + photoFile + ", " + day + "/" + month + "/" + year + ")";
    return output;
  }
  
  // Allows users to compare the recency of two images. Returns true if otherImage was photographed more recently. 
  public boolean isNewer(LabeledPhoto otherImage) {
    if (year < otherImage.getYear()) {
      return true;
    }
    else if (year == otherImage.getYear()) { 
      if (month < otherImage.getMonth()) {
        return true;
      }
      else if (month == otherImage.getMonth() && day < otherImage.getDay()) {
        return true;
      }
    }
        return false;
  }
  
    // Main method designed to test class.  
    public static void main(String[] args) {
      
      // Declare & initialize variables with sample parameters.
      String id = "Test photo";
      int day = 1; 
      int month = 2;
      int year = 2014;
      String category = "Test images";
      String caption = "This is a test caption.";
      String photoFile = FileChooser.pickAFile();
      
      // Create a labeled photo using above parameters. 
      LabeledPhoto labeledPhoto = new LabeledPhoto(id, day, month, year, category, caption, photoFile);
    
      // Invoke all accessor methods and print return values.
      System.out.println(labeledPhoto.getId());
      System.out.println(labeledPhoto.getCategory());
      System.out.println(labeledPhoto.getYear());
      System.out.println(labeledPhoto.getMonth());
      System.out.println(labeledPhoto.getDay());
      System.out.println(labeledPhoto.getCaption());
      System.out.println(labeledPhoto.getPhotoFile());
      
      // Invoke accessor method to get & display labeled photo. 
      labeledPhoto.getPhotoPic().show();
      
      // Print photo information. 
      System.out.println(labeledPhoto.toString());
      
      // Declare & initialize variables with new sample parameters.
      id = "Second test photo";
      day = 3; 
      month = 4;
      year = 2015;
      caption = "This is another test caption.";
      photoFile = FileChooser.pickAFile();
      
      // Create a second labeled photo using above parameters
      LabeledPhoto labeledPhoto2 = new LabeledPhoto(id, day, month, year, category, caption, photoFile);
      
      // Print photo information.
      System.out.println(labeledPhoto2.toString());
      
      // Compare dates of above images to determine recency. 
      System.out.println(labeledPhoto.isNewer(labeledPhoto2));
      System.out.println(labeledPhoto2.isNewer(labeledPhoto));
      
    } // end of main method
} // end of LabeledPhoto class definition