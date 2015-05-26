//CS1026B Assignment 2
//This program manipulates 5 pictures and pastes them into a collage.

public class MakeCollage {
  public static void main(String[] args) {
    
    //Create a blank background for collage
    Picture canvas = new Picture(1000,1000);
    
    //Apply filters & copy onto collage background
    Picture picture = new Picture(FileChooser.pickAFile());
    canvas.copyPictureTo(picture,0,0);
    
    Picture picture1 = new Picture(FileChooser.pickAFile());
    picture1.vintage();
    canvas.copyPictureTo(picture1,500,0);
    
    Picture picture2 = new Picture(FileChooser.pickAFile());
    picture2.negate();
    canvas.copyPictureTo(picture2,750,0);
  
    Picture picture3 = new Picture(FileChooser.pickAFile());
    picture3.redEmphasis(150);
    canvas.copyPictureTo(picture3,500,200);
    
    Picture picture4 = new Picture(FileChooser.pickAFile());
    picture4.decreaseRed();
    canvas.copyPictureTo(picture4,0,600);
    
    //Display & save collage to a file
    canvas.repaint();
    canvas.write("/Users/Niv/Desktop/myCollage.jpg");
  }
}