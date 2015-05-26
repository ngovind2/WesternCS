//CS1026B Assignment 3
//This program creates two movies compiled from a sequence of images that fade in and out of black.

import java.awt.Color;
public class FadingMovie {
  public static void main(String[] args) {
    
    //Initialize variables for starting & ending images.
    Picture startingPicture = new Picture(FileChooser.pickAFile());
    Picture endingPicture = new Picture(FileChooser.pickAFile());
   
    //Use conditional statement to ensure both starting & ending pictures are of the same size. 
    if (startingPicture.getWidth() != endingPicture.getWidth() || startingPicture.getHeight() != endingPicture.getHeight()) {
      SimpleOutput.showInformation("Error: Please re-select images of equal size.");
    }
    
    else {
      
      //Get number of intermediate stages via user input. 
      int numIntStages = SimpleInput.getIntNumber("Please enter the number of intermediate stages for each fading process:");
      
      //Create an array containing the pictures to be used in fading processes.
      Picture[] pictureSequence = new Picture[numIntStages*2 + 1];
      pictureSequence[0] = startingPicture; 
      pictureSequence[pictureSequence.length-1] = endingPicture; 
      
      //Execute first loop to create intermediate pictures for fading out process.
      for (int k = 1; k <= numIntStages; k++) {
      Picture intermediate = new Picture(startingPicture.getWidth(), startingPicture.getHeight());
      intermediate.fadeOut(startingPicture, Color.BLACK, numIntStages, k);
      pictureSequence[k] = intermediate;
      }
      
      //Execute second loop to create intermediate pictures for fading in process.
      //k will begin at 0 so as to prevent a second black frame from being created.
      for (int k = 1; k < numIntStages; k++) {
      Picture intermediate = new Picture(endingPicture.getWidth(), endingPicture.getHeight());
      intermediate.fadeIn(endingPicture, Color.BLACK, numIntStages, k);
      pictureSequence[k + numIntStages] = intermediate;
      }
      
      //Get directory name via user input.
      String directoryName = SimpleInput.getString("Please enter the name of the directory in which to store your movie:");
    
      //Initialize variable and create sequence of frames using all images.
      FrameSequencer frameSequencer = new FrameSequencer(directoryName);
      for (int i = 0; i < pictureSequence.length; i++){
        frameSequencer.addFrame(pictureSequence[i]);
      }
      
      //Create a movie that runs through all the frames 
      //Image fades out then fades in as another image.
      MoviePlayer moviePlayer = new MoviePlayer(directoryName);
      moviePlayer.playMovie();
      
      //Create a second sequence of frames that adds the images in reverse order to the same directory.
      for (int i = pictureSequence.length-1; i >= 0; i--){
        frameSequencer.addFrame(pictureSequence[i]);
      }
      
      //Create a second movie that runs through all the frames in the given directory. 
      //Image fades in, fades out as another image, then fades back to the original. 
      MoviePlayer moviePlayer2 = new MoviePlayer(directoryName);
      moviePlayer2.playMovie();
    }
  }
}
