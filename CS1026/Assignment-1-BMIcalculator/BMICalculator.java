// Simple BMI calculator that uses SimpleInput & SimpleOutput classes

public class BMICalculator {
  public static void main (String[] args) {
  
    // Prompt user to input data
      
    String name = SimpleInput.getString("Welcome! What is your name?");
    double weightPounds = SimpleInput.getNumber("Hello " + name + "!\nPlease enter the following information to calculate your BMI. \n\nWeight (lbs): ");
    double heightInches = SimpleInput.getNumber("Height (in): ");
    
    // Convert units
    
    final double CONVERT_KG = 2.2;
    final double CONVERT_METRES = 0.0254;
    
    double weightKg = weightPounds/CONVERT_KG;
    double heightMetres = heightInches*CONVERT_METRES;
    
    // Calculate BMI
      
    int bmi = (int)(weightKg/(heightMetres*heightMetres));
    
    // Print results
    
    SimpleOutput.showInformation(name + "," + "\nWeight (kg): " + weightKg + "\nHeight (m): " + heightMetres + "\nYour BMI is " + bmi + "." + "\n\nThe normal range for a BMI is 18.5 to 25.");
  }
}