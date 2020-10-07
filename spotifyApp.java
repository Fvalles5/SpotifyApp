import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class spotifyApp {

    public static double tax = 0.0725; // set the tax as it is different from the USD tax


    public static void main(String[] args)throws FileNotFoundException{
        
        
        Scanner scnr = new Scanner(System.in);

        System.out.println("Welcome to Spotify! \nPlease log-in to view your personal account:");

        int attempts = 3; // set attempts to 3 

        System.out.print("Enter Username : ");
        String userName = scnr.nextLine();
        System.out.print("Enter Password : ");
        String userPassword = scnr.nextLine();

        boolean loggedIn;

        String plan = userCheck(userName, userPassword, attempts);
        loggedIn = (!plan.equals(""));

        if (loggedIn == true){
            System.out.println("------------------------------------------");
            System.out.println("Both the username and password are valid.");

                if(plan.equals("Student")){
                    System.out.println("Here are your benefits for you cuerrent " + plan + " plan");
                }

                else if(plan.equals("Family")){
                    System.out.println("Here are your benefits for you cuerrent " + plan + " plan");
                }

                else if(plan.equals("Individual")){
                    System.out.println("Here are your benefits for you cuerrent " + plan + " plan");
                }

                else{ 
                    System.out.println("error");
                }
            }


        else{
            System.out.println("Attempts have run out.");
            System.out.println("The User name and password provided are incorrect : Closing program");
        }



    }

    //----------------------------------------------------- Check user and password in file method -----------------------------------

    public static String userCheck(String userName, String userPassword, int attempts) throws FileNotFoundException{ // takes userName userPassword and attempts as parramters to check the first two and then stop of attempts reaches certain number

        String dataFromFile = ReadFile(); // creates string out of text file so we can use later 


        Scanner lineScnr = new Scanner(dataFromFile); // passes our string through a scanner so we can use it in my method SearchLine

        
        --attempts;

        if (attempts <= 0) { // base case to close after used up attempts reach 0 or less including the first try

            return "";
            
            

        }

        else if (dataFromFile.contains(userName) && dataFromFile.contains(userPassword)){ // chcks if both username and password are in string 
            
            String plan = SearchLine(userName,userPassword,lineScnr);
            return plan;
            

        }

        else{

            Scanner scnr = new Scanner(System.in);

            System.out.println("Sorry no Usernamne and Password with that combination was found in our data base. \nPlease try again. (" + attempts + " attempts remaining)");

            String plan = userCheck(scnr.nextLine(), scnr.nextLine(),attempts);

            return plan;
            
        }
    }

    //-------------------------------------------------- File reader --------------------------------------------

    public static String ReadFile()throws FileNotFoundException{ // passes txt file through a scanner to give us a string 
    
    
    File myFile = new File("database.txt"); 

    Scanner scnr = new Scanner(myFile);

    String dataFromFile = readUntilDone(scnr);
    return dataFromFile;
}

    public static String readUntilDone(Scanner scnr){ // passes an empty string to add lines from the text file later

        return readUntilDone(scnr, "");

    }

    public static String readUntilDone(Scanner scnr, String collect){

        if(!scnr.hasNextLine()){ // if the scanner finds no next line it will stop and return the whole new string back 
            return collect;
        }

        collect = collect + "\n" + scnr.nextLine();

        return readUntilDone(scnr,collect);


    }
    //-------------------------------------------- Scan line by line ---------------------------------

    public static String SearchLine(String user, String password, Scanner file){ // passes our string though a scanner to read line by line once again
            
            String line;

            if (file.hasNextLine()) {

                line = file.nextLine();

                if (line.contains(user) && line.contains(password)){ // finds line that contains the user and password 

                    int space = line.indexOf(' ');
                    String username = line.substring(0,space); // grabs first word to compare to username later

                    if (username.equals(user)){ 

                        int space2 = line.indexOf(' ',space+1);
                        String pass = line.substring(space,space2).trim(); // grabs second word to compare to password later

                        if(pass.equals(password)){

                            String plan = line.substring(line.lastIndexOf(" ") + 1 );
                            return plan; // returns the last word in the line which is the plan 

                    }
            }
        }
                return SearchLine(user,password,file);   

            }
            else {
                return"";
            }
}
}

        




