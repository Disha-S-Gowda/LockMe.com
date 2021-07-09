import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import com.lockme.model.Users;
import com.lockme.model.UserCredentials;

public class LockerMenu {
	
	File users;
	File file;
	File userDirectory;
	Scanner in;
	private static Users userobj;
	private static UserCredentials usercred;
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
			
			userobj = new Users();
			usercred = new UserCredentials();
			
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
		
		in1.close();
		
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
		
		userDirectory = new File(basePath + username);
		FileWriter writer = new FileWriter(users, true);
		
		
		if(!userDirectory.exists()) {
			
			userDirectory.mkdir();			
						
			writer.write(username + "\n");
			writer.write(password + "\n");
			System.out.println("User Registered successfully");
		}
		else {
			System.out.println("User already exists");
		}
				
		writer.close();
		
		}catch(Exception e) {
			System.out.println("User could not be Registered");
			e.printStackTrace();
		}
	}
	
public void SignIn() {
	
	try {
		
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
					userDirectory = new File(basePath + username);
					basePath = basePath + username + "/";
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
		
		while(true) {
			
			System.out.println("==========================================");
			System.out.println("");
			System.out.println("        Locker Options              ");
			System.out.println("1. List all the Credentials");
			System.out.println("2. Modify the credentials");
			System.out.println("3. Close the application");
			int option = in.nextInt();
			switch(option) {
				case 1 : 
					listAscending(inpUsername);
					break;
				case 2 :
					showModifyOption(inpUsername);
					break;
				case 3 :
					closeApplication(inpUsername);
					break;
				default :
					System.out.println("Please select 1, 2 or 3");
					break;
			}
			
		}
		
	}

	private void closeApplication(String inpUsername) {
		
		in.close();
		System.exit(0);
		
	}

	private void showModifyOption(String inpUsername) {
		
		System.out.println("==========================================");
		System.out.println("");
		System.out.println("1 . ADD CREDENTIAL");
		System.out.println("2 . DELETE CREDENTIAL ");
		System.out.println("3 . SEARCH CREDENTIAL ");
		System.out.println("4 . RETURN TO LOCKER OPTIONS ");
		
		int option = in.nextInt();
		switch(option) {
			case 1 : 
				storeCredentials(inpUsername);
				break;
			case 2 :
				deleteCredentials(inpUsername);
				break;
			case 3 :
				fetchCredentials(inpUsername);
				break;
			case 4 :
				return;
			default :
				System.out.println("Please select 1 Or 2");
				break;
		}
	}

	private void listAscending(String inpUsername) {
		
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("*   YOUR CREDS ARE 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		String[] list = userDirectory.list();
		
		if(list != null) {
			
			for( String item : list) {
				
				System.out.println(item);
			}
		}
		else {
			System.out.println("\n No credentials entered yet");
		}
		
	}

	private void deleteCredentials(String inpUsername) {
		
		System.out.println("Please enter the sitename of the credential to be deleted");
		String sitename = in.next();
		
		File siteFile = new File(basePath + sitename + ".txt");
		
		if(siteFile.exists()) {
			siteFile.delete();
			System.out.println(" credentials deleted successfully");
		}
		else {
			System.out.println(" credentials does not exist");
		}
		
	}

	private void storeCredentials(String inpUsername) {

		try {
			
			System.out.println("==========================================");
			System.out.println("*					*");
			System.out.println("*   WELCOME TO DIGITAL LOCKER STORE YOUR CREDS HERE	 *");
			System.out.println("*					*");
			System.out.println("==========================================");
			
			usercred.setLoggedInUser(inpUsername);
			
			System.out.println("Enter Site Name :");
			String siteName = in.next();
			usercred.setSiteName(siteName);
			
			System.out.println("Enter Username :");
			String username = in.next();
			usercred.setUsername(username);
			
			System.out.println("Enter Password :");
			String password = in.next();
			usercred.setPassword(password);
			
			File site = new File(basePath + siteName + ".txt");
			
			if(!site.exists()) {
				site.createNewFile();
			}
			
			FileWriter WriteCred = new FileWriter(site);
			
			WriteCred.write(usercred.getLoggedInUser() + "\n");
			WriteCred.write(usercred.getSiteName() + "\n");
			WriteCred.write(usercred.getUsername() + "\n");
			WriteCred.write(usercred.getPassword() + "\n");
			
			System.out.println("YOUR CREDS ARE STORED AND SECURED!");
			WriteCred.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	}

	private void fetchCredentials(String inpUsername) {
		
		System.out.println("==========================================");
		System.out.println("*					*");
		System.out.println("*   WELCOME TO DIGITAL LOCKER 	 *");
		System.out.println("*   YOUR CREDS ARE 	 *");
		System.out.println("*					*");
		System.out.println("==========================================");
		
		System.out.println("Enter Site Name :");
		String siteName = in.next();
		usercred.setSiteName(siteName);
		
		
		for( String item : userDirectory.list()) {
		
			try {
				
				if(item.contains(siteName)) {
					
					File temp = new File(basePath + item);		
					
					fileReader = new Scanner(temp);
					
					while(fileReader.hasNext()) { 
						
						String usertemp  = fileReader.next();
						 if(usertemp.equals(inpUsername)) {
							  
							  System.out.println("Site Name: "+fileReader.next());
							  System.out.println("User Name: "+fileReader.next());
							  System.out.println("User Password: "+fileReader.next()); 
						 } 
					}	
							 
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}			
			
		}
		
		
		 
		 
		
	}

}
