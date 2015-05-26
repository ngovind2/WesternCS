//CS1026B Assignment 2
//This program invokes methods that act as photo filters (red emphasis & vintage effects). 

import java.awt.Color;
public class TestMethods {
  public static void main(String[] args) {
    
    Picture picture1 = new Picture(FileChooser.pickAFile());
    picture1.show();
    
    //Use redEmphasis method on redDoor image with suggested ratio of 150
    picture1.redEmphasis(150);
    picture1.explore();
    
    Picture picture2 = new Picture(FileChooser.pickAFile());
    picture2.show();
    //Use vintage method on eiffel image
    picture2.vintage();
    picture2.explore();   
  }
}