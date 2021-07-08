import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class LockerMenu {
	
	File users;
	File file;
	Scanner in;
	
	public LockerMenu() {
		
		in = new Scanner(System.in);
		
		file = new File("LockMe");
		Boolean bool = file.mkdir();
		if(bool) {
			
			users = new File("LockMe/users.txt");
			Boolean result = users.createNewFile();
			if (result) {
				System.out.println("Application Setup Successful");
			}
			else {
				System.out.println("Could not set up application");
				System.exit(0);
			}
			
		}
		else {
			System.out.println("Could not set up application");
		}
		
	}

	public static void main(String[] args) {
		
		LockerMenu lock = new LockerMenu();
		
		System.out.println("--------------------------------");
		System.out.println("|           LOCK ME            |");
		System.out.println("|                              |");
		System.out.println("|          Developer: Disha S  |");
		System.out.println("--------------------------------");
		
		System.out.println("Please Login to the application using below options");
		System.out.println("");
		System.out.println("1. Sign in");
		System.out.println("2. Sign up");
		
		int option_selected = in.nextInt();
		
		switch(option_selected){
		case 1 : lock.SignUp();
				break;
		case 2 : lock.SignIn();
				break;
		default: System.out.println("Sorry!! invalid input");
				 break;
		}
		
		System.out.println("        Main  Menu              ");
		System.out.println("1. List all the Credentials");
		System.out.println("2. Modify the credentials");
		System.out.println("3. Close the application");
		
		
	}
	
	public void SignUp() {
		
		System.out.println("--------------------------------");
		System.out.println("");
		System.out.println("Enter the Username");
		String username = in.nextLine();
		
		System.out.println("Enter the Password");
		String password = in.nextLine();
		
		System.out.println("Username:"+ username);
		System.out.println("Username:"+ password);
		
		FileWriter writer = new FileWriter(users);
		writer.write("Test data to file inserted !");
		writer.close();
	}
	
public void SignIn() {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("--------------------------------");
		System.out.println("");
		System.out.println("Enter the Username");
		String username = in.nextLine();
		
		System.out.println("Enter the Password");
		String password = in.nextLine();
		
		System.out.println("Username:"+ username);
		System.out.println("Username:"+ password);
	}

public void Init()
}
