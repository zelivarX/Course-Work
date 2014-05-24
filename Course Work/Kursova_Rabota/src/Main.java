import java.util.Scanner;


public class Main {
	
	private static final int LOGIN = 1;
	private static final int REGISTER = 2;
	private static final int ADD_BUBJECT = 3;
	private static final int SHOW_PROFILE = 4;
	private static final int SHOW_SUBJECTS = 5;
	private static final int LOGOUT = 6;
	private static final int QUIT = 7;
	
	static void login() {
		
	}
	
	static void register() {
		
	}
	
	static boolean online;
	
	static int getMenuOptionlifeCycle() {
		System.out.println("1. Add subject");
		System.out.println("2. Show Profile");
		System.out.println("3. Show my subjects");
		System.out.println("4. logout");
		System.out.println("5. Quit");
		
		System.out.print("Enter your option: ");
		
		int option;
		Scanner in = new Scanner(System.in);
		do {
			option = in.nextInt();
			switch(option) {
			case 1: option = ADD_BUBJECT; break;
			case 2: option = SHOW_PROFILE; break;
			case 3: option = SHOW_SUBJECTS; break;
			case 4: option = LOGOUT; break;
			case 5: option = QUIT; break;
			
			}
		} while (option < ADD_BUBJECT || option > QUIT);
		
		return option;
	}
	
	private static boolean lifecyle(OptionsManager optionsManager) {
		int option;
		
		do {
			option = getMenuOptionlifeCycle();
			
			switch(option) {
			
			
			case ADD_BUBJECT: 
				optionsManager.getSubjects();
				break;
			case SHOW_SUBJECTS: 
				optionsManager.showSubjects();
				break;
			case SHOW_PROFILE: 
				optionsManager.show_profile();
				break;
			case LOGOUT:
				return false;
			
			}
			System.out.println();
			System.out.println();

		} while(option != QUIT);
		
		return true;
	}
	
	
	static int getMenuOption() {
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Quit");
		
		System.out.print("Enter your option: ");
		
		int option;
		Scanner in = new Scanner(System.in);
		do {
			option = in.nextInt();
			if(option == 3) option = QUIT;
		} while (option != LOGIN && option != QUIT && option != REGISTER);
		
		return option;
	}
	
	public static void main(String[] args) {
		int option;
		
		OptionsManager optionsManager = new OptionsManager();
		
		do {
			option = getMenuOption();
			
			switch(option) {
			
			case LOGIN: 
				
				if(optionsManager.login())
					if(lifecyle(optionsManager) == true) { // if true is return then the QUIT option was selected
						option = QUIT;
					}
				break;
			case REGISTER: 
				optionsManager.register();
				break;
			
			}
			System.out.println();
			System.out.println();

		} while(option != QUIT);
		
		optionsManager.quit();
		
		System.out.println("GOODBYE :))");
	}

}
