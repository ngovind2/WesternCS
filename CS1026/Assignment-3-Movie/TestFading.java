//CS1026B Assignment 3
/*This program displays a sequence of five images that become progressively darker and fade into black, 
 * and another sequence of five images that fade from black and become progressively lighter.*/

import java.awt.Color;
public class TestFading {
  public static void main(String[] args) {
    
    //Initializing object variables for fade out images.
    Picture startingPicture = new Picture(FileChooser.pickAFile());
    Picture p1 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
    Picture p2 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
    Picture p3 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
    Picture endingPicture = new Picture(startingPicture.getWidth(), startingPicture.getHeight());

    //Apply fading out effect & display image at each of the 4 stages. Pictures will fade to black. 
    startingPicture.show();
    p1.fadeOut(startingPicture, Color.BLACK, 4, 1);
    p1.show();
    p2.fadeOut(startingPicture, Color.BLACK, 4, 2);
    p2.show();
    p3.fadeOut(startingPicture, Color.BLACK, 4, 3);
    p3.show();
    endingPicture.fadeOut(startingPicture, Color.BLACK, 4, 4);
    endingPicture.show();
    
    //Initializing new object variables for fade in images.
    Picture p4 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
    Picture p5 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
    Picture p6 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
    Picture endingPicture2 = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
        
    //Apply fading in effect & display each image at every stage. Pictures will fade from black.
    endingPicture2.fadeIn(startingPicture, Color.BLACK, 4, 0);
    endingPicture2.show();
    p4.fadeIn(startingPicture, Color.BLACK, 4, 1);
    p4.show();
    p5.fadeIn(startingPicture, Color.BLACK, 4, 2);
    p5.show();
    p6.fadeIn(startingPicture, Color.BLACK, 4, 3);
    p6.show();
    startingPicture.show();
  }
}