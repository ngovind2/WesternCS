// CS1026B Assignment 4
// This class allows the user to create a directory of labeled photos sorted by date.

public class CreatePhotoCollections {
  public static void main(String[] args) { 
    
    // Get name of photo information file & name of directory in which to store photos.
    String fileName = FileChooser.pickAFile();
    String directoryName = SimpleInput.getString("Please enter the name of the directory in which to save your photos."); 
    
    // Create new PhotoCollection object to store photos from file. Invoke PhotoCollection methods.
    PhotoCollection photoCollection = new PhotoCollection(fileName); 
    photoCollection.listPhotoInformation();  
    photoCollection.sortPhotosByDate(); 
    photoCollection.listPhotoInformation();
    photoCollection.showPhotos();
    photoCollection.showPhotos("Pets"); // Show photos from category "Pets". 
    photoCollection.storePhotos(directoryName); // Store photos as .jpg files in specified directory.
  }
}