import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.text.*;

/**
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param width the width of the desired picture
   * @param height the height of the desired picture
   */
  public Picture(int width, int height)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }

  
  // Written by Niv Govindaraju  

  /*****************************************************************
   * Mirror a picture vertically
   *****************************************************************/
  public void mirrorVertical(){
    //Declare local variables
    int width = this.getWidth();
    int mirrorPoint = width/2;
    Pixel leftPix = null;
    Pixel rightPix = null;
    
    //Loop through all rows, only half way
    for (int y=0;y<this.getHeight();y++){
      for (int x=0; x<mirrorPoint; x++){
        leftPix = getPixel(x,y);
        rightPix = getPixel(width-1-x,y);
        rightPix.setColor(leftPix.getColor()); //Swap colours of left and right
      }
    }  
  }
  
  
  /*****************************************************************
   * Method to make a picture greyscale
   *****************************************************************/
  public void goodGrey(){
    //Declare local variables; get an array of pixels out of the picture
    Pixel[] pixelArray = this.getPixels();
    Pixel pix = null;
    int luminance = 0;
    double redV = 0;
    double blueV= 0;
    double greenV= 0;
    
    //Loop through all pixels in the array
    for (int i=0; i<pixelArray.length;i++){
      pix = pixelArray[i];
      redV = pix.getRed() * 0.299;
      blueV = pix.getBlue() * 0.587;
      greenV = pix.getGreen() * 0.114;
      luminance = (int)(redV + greenV + blueV);
      //Change pixel colour to the new colour created using luminance
      pix.setColor(new Color(luminance, luminance, luminance));
    }
  }
  
/***************************************
** Copy picture ***
****************************************/

public void copyPicture (Picture sourcePicture) 
{ 
   Pixel sourcePixel = null; 
   Pixel targetPixel = null; 
   // loop through the columns 
   for (int sourceX = 0, targetX = 0; 
        sourceX < sourcePicture.getWidth(); 
sourceX++, targetX++)    
   {    // loop through the rows 
     for (int sourceY = 0, targetY = 0; 
          sourceY < sourcePicture.getHeight(); 
sourceY++, targetY++)    
     { 
       // set the target pixel color to the source pixel color 
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY); 
       targetPixel = this.getPixel(targetX,targetY); 
       targetPixel.setColor(sourcePixel.getColor()); 
     } 
   } 
 }


/**
  * Modified version of method from page 154 of the textbook for copying pictures
  */

 public void copyPictureTo(Picture sourcePicture, int xStart, int yStart)
 {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   
   //loop through the columns
   try{
   for (int sourceX = 0, targetX = xStart;
        sourceX < sourcePicture.getWidth();
        sourceX++, targetX++)
   {
     //loop through the rows
     for (int sourceY = 0,
          targetY = yStart;
          sourceY < sourcePicture.getHeight();
          sourceY++, targetY++)
     {
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY);
       targetPixel = this.getPixel(targetX,targetY);
       targetPixel.setColor(sourcePixel.getColor());
     } 
   }
  }catch(IndexOutOfBoundsException ex){System.out.println("Either xStart or yStart is out of bounds");System.exit(0);}
} 


/****************************
Crop picture
*****************************/
public void copyPictureTo(Picture sourcePicture, int startX, int startY,  
                           int endX, int endY, int targetStartX, int targetStartY) 
  { 
    Pixel sourcePixel = null; 
    Pixel targetPixel = null; 
    // loop through the x values 
    for (int x = startX, tx = targetStartX;  x < endX && x < sourcePicture.getWidth() && tx < this.getWidth();  x++, tx++) 
    { 
      // loop through the y values 
      for (int y = startY, ty = targetStartY; y < endY && y < sourcePicture.getHeight() &&  ty < this.getHeight();  y++, ty++)  
      {

// copy the source color to the target color 
        sourcePixel = sourcePicture.getPixel(x,y); 
        targetPixel = this.getPixel(tx,ty); 
        targetPixel.setColor(sourcePixel.getColor()); 
      } 
    } 
  }
  
  /*****************************************************************
   * Method to make a negative of a picture
   *****************************************************************/
  public void negate(){
    //Local variables
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int redV, blueV, greenV = 0;
    //Loop through all pixels in the picture
    for (int i=0; i<pixelArray.length;i++){
      pixel = pixelArray[i];
      redV=pixel.getRed();
      greenV=pixel.getGreen();
      blueV = pixel.getBlue();
      //Set the color to the negative of current color
      pixel.setColor(new Color(255-redV, 255-greenV, 255-blueV));
    }
  }
  
  
  /*****************************************************************
   * Method to create a fake sunset
   *****************************************************************/
  public void makeSunset(){
    //Create local variables and get array of pixels
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int i=0;
    
    //Loop through all pixels
    while (i<pixelArray.length){
      pixel = pixelArray[i]; //get out current picture
      value = pixel.getBlue(); //get the blue value
      pixel.setBlue((int)(value*0.7)); //set a new blue
      value = pixel.getGreen(); //get the green value
      pixel.setGreen((int)(value*0.7)); //set the new green value
      i++; //increment counter
    }
  }
  
  
/*****************************************************************
* Method to decrease the red in a picture
*****************************************************************/
  public void decreaseRed(){
    //Declare variables and get an array with all the pixels
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    int index = 0;
    //Loop through all pixels
    while (index < pixelArray.length){
      pixel = pixelArray[index]; //get pixel at each spot in the array in turn
      value = pixel.getRed(); //get the red value
      value=(int)(value*0.5); //change the red value by half
      pixel.setRed(value); //set a new red value
      index= index + 1; //increment so next time you get new pixel
    }
  }
  
/*****************************************************************
* Method to change red in a picture
*****************************************************************/
  public void changeRed(double howMuch){
    Pixel[] pixelArray = this.getPixels();
    Pixel pixel = null;
    int value = 0;
    for (int i= 0; i < pixelArray.length; i++){
      pixel = pixelArray[i]; 
      value = pixel.getRed(); 
      value=(int)(value*howMuch);
      pixel.setRed(value);
    }
  }
  
/*****************************************************************
* Method to clear blue in a picture
*****************************************************************/
  
  public void clearBlue2() {
    Pixel pixelObj;
    for (int y=0; y < this.getHeight(); y++){
       for (int x=0; x < this.getWidth(); x++){
        pixelObj = this.getPixel(x,y);
        pixelObj.setBlue(0);
      } 
    } 
  }
  
/*****************************************************************
* Method to mirror picture vertically, from left to right
*****************************************************************/
  
  public void mirrorVertical2() {
    int mirrorPoint = this.getWidth()/2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    
    // loop through the rows
    for (int y = 0; y < this.getHeight(); y++) {
      //loop from column 0 to just before the mirror point
      for (int x = 0; x < mirrorPoint; x++) {
        leftPixel = this.getPixel(x,y); 
        rightPixel = this.getPixel(this.getWidth() - 1 - x, y);
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
/*****************************************************************
* Method to mirror picture vertically, from right to left
*****************************************************************/
  
  public void mirrorVerticalRightToLeft() {
    int mirrorPoint = this.getWidth()/2;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    
    // loop through the rows
    for (int y = 0; y < this.getHeight(); y++) {
      //loop from column 0 to just before the mirror point
      for (int x = 0; x < mirrorPoint; x++) {
        leftPixel = this.getPixel(x,y); 
        rightPixel = this.getPixel(this.getWidth() - 1 - x, y);
        leftPixel.setColor(rightPixel.getColor());
      }
    }
 }
    
/*****************************************************************
 * Method to mirror picture diagonally
 *****************************************************************/
 public void mirrorDiagonal() {
    int mirrorPoint = 0;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    
    for (int y = 0; y < this.getHeight(); y++) {
      for (int x = 0; x < this.getWidth(); x++) {
        rightPixel = this.getPixel(x,y);
        leftPixel = this.getPixel(y,x); 
        leftPixel.setColor(rightPixel.getColor());
      }
    }
  }
 
/*******************************************************************
* Method to make red objects stand out against grayscale background
*******************************************************************/
 
 public void redEmphasis(int ratio) { 
   
   //Declare variables to be used in loop
   Pixel pixelObj; 
   int gray;
   //These constants will be used for grayscale ratios 
   final double RED_RATIO = 0.3; 
   final double GREEN_RATIO = 0.7;
   final double BLUE_RATIO = 0.1;
   //This constant will be used as a test to limit the red in white/light-coloured pixels from being emphasized
   final double GREEN_LIMIT = 100;
   
   //Nested for loops will run through all pixels of a picture
   for (int x = 0; x < this.getWidth(); x++) {
     for (int y = 0; y < this.getHeight(); y++) {
       pixelObj = this.getPixel(x,y);
       
       //Pixels that are strongly red will become emphasized by clearing green & blue channels
       //White pixels will not become emphasized since value of green channel will be too high
       if (pixelObj.getRed() > ratio && pixelObj.getGreen() < GREEN_LIMIT) {
         pixelObj.setGreen(0);
         pixelObj.setBlue(0);
       } 
       //Pixels that aren't strongly red will become grayscaled by taking average intensity of all 3 channels
       else {
         gray = ((int)(pixelObj.getRed()*RED_RATIO + pixelObj.getGreen()*GREEN_RATIO + pixelObj.getBlue()*BLUE_RATIO))/3;
         pixelObj.setColor(new Color(gray,gray,gray));
       }
     }
   }
 }
 
/*******************************************************************
* Method to create fake vintage photo effect
*******************************************************************/
 
 public void vintage() {
   //Create an array of references to pixel objects derived from an image
   Pixel[] picArray = this.getPixels();

   //Define variables to be used in loop
   Pixel pixelObj;
   int index = 0;
   int red;
   int green;
   int blue;
   
   //These constants will be used to calculate vintage ratios
   final double RED_RATIO1 = 0.39;
   final double RED_RATIO2 = 0.35;
   final double RED_RATIO3 = 0.27;
       
   final double GREEN_RATIO1 = 0.77;
   final double GREEN_RATIO2 = 0.69;
   final double GREEN_RATIO3 = 0.53;

   final double BLUE_RATIO1 = 0.19;
   final double BLUE_RATIO2 = 0.17;
   final double BLUE_RATIO3 = 0.13;

   //Loop through the pixel array using while loop
   while (index < picArray.length) {
     pixelObj = picArray[index];

     //Get values of each channel
     red = pixelObj.getRed();
     green = pixelObj.getGreen();
     blue = pixelObj.getBlue();
     
     //Set values of each channel to vintage intensity 
     pixelObj.setRed((int)(red*RED_RATIO1 + green*GREEN_RATIO1 + blue*BLUE_RATIO1));
     pixelObj.setGreen((int)(red*RED_RATIO2 + green*GREEN_RATIO2 + blue*BLUE_RATIO2));
     pixelObj.setBlue((int)(red*RED_RATIO3 + green*GREEN_RATIO3 + blue*BLUE_RATIO3));
     
     //Increment index
     index++;
   }
 }
 
 
/*******************************************************************
* Method to create copy all pixels except white pixels
*******************************************************************/

 public void copyExceptWhite (Picture sourcePicture, int xStart, int yStart) 
 {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   
   //loop through the columns
   try{
   for (int sourceX = 0, targetX = xStart;
        sourceX < sourcePicture.getWidth();
        sourceX++, targetX++)
   {
     //loop through the rows
     for (int sourceY = 0,
          targetY = yStart;
          sourceY < sourcePicture.getHeight();
          sourceY++, targetY++)
     {
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY);       
       if (sourcePixel.getRed() != 255 || sourcePixel.getGreen() != 255 || sourcePixel.getBlue() != 255) 
       {
         targetPixel = this.getPixel(targetX,targetY);
         targetPixel.setColor(sourcePixel.getColor());
       }
     } 
   }
  }catch(IndexOutOfBoundsException ex){System.out.println("Either xStart or yStart is out of bounds");System.exit(0);}
}
 
/*************************************************************************
* Method to clear lesser values of blue & emphasize greater values of blue
*************************************************************************/ 
 public void blueExtremes () {
   Pixel pixelObj; 
   for (int x = 0; x < this.getWidth(); x++) {
     for (int y = 0; y < this.getHeight(); y++) {
       pixelObj = this.getPixel(x,y);
       if (pixelObj.getBlue() < 128) {
         pixelObj.setBlue(0);
       } 
       else {
         pixelObj.setBlue(255);
       }
     }
   }
 }
 
/*****************************
* Method to copy left rottion
******************************/
 public Picture copyLeftRotation(){ 
  
  Picture targetPicture = new Picture(this.getHeight(), this.getWidth()); 
  
  for (int sourceX = 0; sourceX < this.getWidth(); sourceX++){ 
    for (int sourceY = 0; sourceY < this.getHeight(); sourceY++){ 
      
      // Rotation to the left
      int targetX = sourceY; 
      int targetY = (this.getWidth() - 1) - sourceX; 
      
      Pixel sourcePixel = this.getPixel(sourceX, sourceY); 
      Pixel targetPixel = targetPicture.getPixel(targetX, targetY); 
      targetPixel.setColor(sourcePixel.getColor()); 
    } 
  } 
  return targetPicture; 
}

/******************************
* Method to copy right rotation
******************************/
 
public Picture copyRightRotation(){ 
  
  Picture targetPicture = new Picture(this.getHeight(), this.getWidth()); 
 
  for (int sourceX = 0; sourceX < this.getWidth(); sourceX++){ 
    for (int sourceY = 0; sourceY < this.getHeight(); sourceY++){ 
      
      // Rotation to the right
      int targetX = (this.getHeight()-1) - sourceY; 
      int targetY = sourceX; 
      
      Pixel sourcePixel = this.getPixel(sourceX, sourceY); 
      Pixel targetPixel = targetPicture.getPixel(targetX, targetY); 
      targetPixel.setColor(sourcePixel.getColor()); 
    } 
  } 
  return targetPicture; 
}

/***************************************
* Method to count number of white pixels
***************************************/ 
 public int countWhitePixels() {
  Pixel pixelObj; 
  int counter = 0; 
  
  for (int x = 0; x < this.getWidth(); x++) {
    for (int y = 0; y < this.getHeight(); y++) {
      pixelObj = this.getPixel(x,y);
      
      if (pixelObj.getRed() == 255 && pixelObj.getGreen() == 255 && pixelObj.getBlue() == 255)
        counter = counter + 1;
    }
  }
  return counter;
}
 
/*******************************************
* Method to count number of non-white pixels
*******************************************/ 
 
 public int countNonWhitePixels() {
  Pixel pixelObj; 
  int counter = 0; 
  
  for (int x = 0; x < this.getWidth(); x++) {
    for (int y = 0; y < this.getHeight(); y++) {
      pixelObj = this.getPixel(x,y);
      
      if (pixelObj.getRed() != 255 || pixelObj.getGreen() != 255 || pixelObj.getBlue() != 255)
        counter = counter + 1;
    }
  }
  return counter;
}
 
/******************************
* Method to compare image sizes
******************************/ 
 public boolean equalPictureSize(Picture otherPicture) {
   int width = this.getWidth();
   int height = this.getHeight(); 
   boolean answer = false; 
   if (width == otherPicture.getWidth() && height == otherPicture.getHeight())
     answer = true;
   return answer;
 }

/*******************************
* Method to copy image left half
*******************************/
 public void copyLeftHalf (Picture sourcePicture) {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   // loop through the columns 
   for (int sourceX = 0, targetX = 0; sourceX < sourcePicture.getWidth()/2; sourceX++, targetX++) {
   // loop through the rows 
     for (int sourceY = 0, targetY = 0; sourceY < sourcePicture.getHeight(); sourceY++, targetY++) {
   // set the target pixel color to the source pixel color 
       sourcePixel = sourcePicture.getPixel(sourceX,sourceY); 
       targetPixel = this.getPixel(targetX,targetY);
       targetPixel.setColor(sourcePixel.getColor()); 
     }
   }
 }

/*******************************
* Method to copy image left half
*******************************/
 public Picture copyLeftHalf () {
   Pixel sourcePixel = null;
   Pixel targetPixel = null;
   Picture targetPic = new Picture(this.getWidth()/2, this.getHeight());
   // loop through the columns 
   for (int sourceX = 0, targetX = 0; sourceX < this.getWidth()/2; sourceX++, targetX++) {
   // loop through the rows 
     for (int sourceY = 0, targetY = 0; sourceY < this.getHeight(); sourceY++, targetY++) {
   // set the target pixel color to the source pixel color 
       sourcePixel = this.getPixel(sourceX,sourceY); 
       targetPixel = targetPic.getPixel(targetX,targetY);
       targetPixel.setColor(sourcePixel.getColor()); 
     }
   }
   return targetPic;
 }
 
//Assignment 3
/**************************************************************
* Method to cause a picture to fade out into a specified colour
**************************************************************/
 
public void fadeOut(Picture startPicture, Color fadeTo, int numStages, int k) {

  //User is prompted to use images of the same size.
  if (this.getWidth() != startPicture.getWidth() || this.getHeight() != startPicture.getHeight()) {
    SimpleOutput.showInformation("Please use images of the same size.");
  }
  else {
    //Loop through x- & y- coordinates of picture.
    for (int x = 0; x < startPicture.getWidth(); x++) {
      for (int y = 0; y < startPicture.getHeight(); y++) {
        
        //Initialize variable for a pixel from starting picture.
        Pixel startPixel = startPicture.getPixel(x,y);
        
        //Get RGB values from starting picture pixel.
        int startRed = startPixel.getRed();
        int startGreen = startPixel.getGreen();
        int startBlue = startPixel.getBlue();
        
        //Get RGB values from chosen colour.
        int endRed = fadeTo.getRed();
        int endGreen = fadeTo.getGreen();
        int endBlue = fadeTo.getBlue();
        
        //Calculate RGB values for intermediate/ending pictures based on formula.
        int redValue = startRed + ((endRed - startRed)/numStages) * k;
        int greenValue = startGreen + ((endGreen - startGreen)/numStages) * k;
        int blueValue = startBlue + ((endBlue - startBlue)/numStages) * k;
        
        //Initialize pixelObj to refer to a pixel from target picture.
        Pixel pixelObj = this.getPixel(x,y);
        
        //Set RGB values for target picture pixel
        pixelObj.setRed(redValue);
        pixelObj.setGreen(greenValue);
        pixelObj.setBlue(blueValue);
      }
    }
  }
}

/*************************************************************
* Method to cause a picture to fade in from a specified colour
*************************************************************/

public void fadeIn(Picture endPicture, Color fadeFrom, int numStages, int k) {
  
  //User is prompted to use images of the same size.
  if (this.getWidth() != endPicture.getWidth() || this.getHeight() != endPicture.getHeight()) {
    SimpleOutput.showInformation("Please use images of the same size.");
  }
  else {
    //Loop through x- & y- coordinates of picture.
    for (int x = 0; x < endPicture.getWidth(); x++) {
      for (int y = 0; y < endPicture.getHeight(); y++) {
        
        //Initialize variable for a pixel from ending picture.
        Pixel endPixel = endPicture.getPixel(x,y);
        
        //Get RGB values from chosen colour.
        int endRed = endPixel.getRed();
        int endGreen = endPixel.getGreen();
        int endBlue = endPixel.getBlue();
        
        //Get RGB values from starting picture pixel.
        int startRed = fadeFrom.getRed();
        int startGreen = fadeFrom.getGreen();
        int startBlue = fadeFrom.getBlue();
        
        //Calculate RGB values for starting/intermediate pictures based on formula.
        int redValue = startRed + ((endRed - startRed)/numStages) * k;
        int greenValue = startGreen + ((endGreen - startGreen)/numStages) * k;
        int blueValue = startBlue + ((endBlue - startBlue)/numStages) * k;
        
        //Initialize pixelObj to refer to a pixel from target picture.
        Pixel pixelObj = this.getPixel(x,y);
        
        //Set RGB values for target picture pixel.
        pixelObj.setRed(redValue);
        pixelObj.setGreen(greenValue);
        pixelObj.setBlue(blueValue);
      }
    }
  }
}


// Assignment 4
/** This method will draw a the given text, using the font size specified
    * in a bold font at the yPos given and centered in the image
    * @param text : the text to write
    * @param fontSize : the size of font to use
    * @param yPos : the vertical position of the baseline of the first character
    */
  public void drawCenteredString(String text, int yPos, int fontSize){
    Graphics2D g = (Graphics2D)this.getGraphics();
    
    g.setFont(new Font("Arial",Font.BOLD,fontSize));
    g.setColor(Color.BLACK);
    int stringLen = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();  
    int xPos = this.getWidth()/2 - stringLen/2;  
    
    g.drawString(text, xPos, yPos);
  }
  
  /** This method will draw the given text, using the font size specified
    * in italics in the bottom right corner of the image
    * @param text : the text to write
    * @param fontSize: the size of font to use
    */
  public void drawInfoWatermark(String text, int fontSize){
    
    //Get the graphics object of this image
    Graphics2D g = (Graphics2D)this.getGraphics();
    
    //Set up transparency (alpha channel)
    AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
    g.setComposite(alpha);
    g.setColor(Color.white);  
    g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
    
    //Font setup
    g.setFont(new Font("Arial", Font.ITALIC, fontSize));   
    
    //use a pad at the edge
    final int PAD = 20;
    int stringLen = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();  
    int stringHeight = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
    //write text onto the image
    g.drawString(text, this.getWidth()-stringLen-PAD, this.getHeight()-stringHeight);  
  }
}
// end of class Picture, put all new methods before this

 