import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import com.lockme.model.Users;

public class LockerMenu {
	
	File users;
	File file;
	File userFile;
	Scanner in;
	private static Users userobj;
	String basePath = "LockMe/";
	private static Scanner fileReader;
	
	public LockerMenu() {
		
		try {
			
		in = new Scanner(System.in);
		
		file = new File("LockMe");
		
		if(!file.exists()) {
			file.mkdir();
		}		
			users = new File("LockMe/users.txt");
			
			if(!users.exists()) {
				
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
				System.out.println("Application Setup Successful");
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Scanner in1 =  new Scanner(System.in);
		
		LockerMenu lock = new LockerMenu();
		
		System.out.println("--------------------------------");
		System.out.println("|           LOCK ME            |");
		System.out.println("|                              |");
		System.out.println("|          Developer: Disha S  |");
		System.out.println("--------------------------------");
		
		System.out.println("Please Login to the application using below options");
		System.out.println("");
		System.out.println("1. Sign up");
		System.out.println("2. Sign in");
		
		int option_selected = in1.nextInt();
		
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
		try {
		
		System.out.println("--------------------------------");
		System.out.println("");
		System.out.println("Enter the Username");
		String username = in.nextLine();
		userobj.setUsername(username);
		
		System.out.println("Enter the Password");
		String password = in.nextLine();
		userobj.setPassword(password);
		
		System.out.println("Username:"+ username);
		System.out.println("Password:"+ password);
		
		FileWriter writer = new FileWriter(users);
		writer.write(username);
		writer.write(password);
		
		System.out.println("User Registered successfully");
		
		writer.close();
		
		}catch(Exception e) {
			System.out.println("User could not be Registered");
			e.printStackTrace();
		}
	}
	
public void SignIn() {
	
	try {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("--------------------------------");
		System.out.println("");
		System.out.println("Enter the Username");
		String username = in.nextLine();
		
		boolean found = false;
		
		fileReader = new Scanner(users);
		
		while(fileReader.hasNext() && !found) {
			
			if(fileReader.next().equals(username)) {
				System.out.println("Enter Password :");
				String Password = in.nextLine();
				if(fileReader.next().equals(Password)) {
					System.out.println("Login Successful ! 200OK");
					found = true;
					lockerOptions(username);
					break;
				}
			}
		}
		if(!found) {
			System.out.println("User Not Found : Login Failure : 404");
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

	public void lockerOptions(String inpUsername) {
		System.out.println("1 . FETCH ALL STORED CREDENTIALS ");
		System.out.println("2 . STORED CREDENTIALS ");
		int option = in.nextInt();
		switch(option) {
			case 1 : 
				fetchCredentials(inpUsername);
				break;
			case 2 :
				storeCredentials(inpUsername);
				break;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
		lockerInput.close();
	}

}
