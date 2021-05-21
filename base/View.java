import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class View 
{

	private Model model;

	public void init() 			// my database using XML format ( new thing that i was able to learn and use )
	{
		if (Files.exists(Paths.get("db.xml"))) {
			try (XMLDecoder decoder = new XMLDecoder(new FileInputStream("db.xml"))) {
				model = (Model) decoder.readObject();
			} catch (FileNotFoundException e1) 
			{
				System.err.println("Oops! Error occurred: " + e1);
			}
		} else 
		{
			model = new Model();
			model.addPerson(new Guest()); // Only 1 Guest is required.
			model.addPerson(new Admin("admin", "admin", "admin", "admin", "1900-01-01", 0, ""));	//the admin login information and is believed to be 120 years old!
			// 1 Admin required with admin/admin login/password.
		}
	}

	public void persist() 		// my output
	{
		try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream("db.xml"))) {
			encoder.writeObject(model);
		} catch (FileNotFoundException e1) {
			System.err.println("Oops! Error occurred: " + e1);
		}
	}

	public void start() 
	{
		try (Scanner sc = new Scanner(System.in)) 
		{
			System.out.println("Welcome to the Car Marketplace!");
			while (true) 
			{
				System.out.println("Select option:");		// you can type l,g,e to choose, the also the upper and lower case dont matter! thanks to equallsIgnorecase()
				System.out.println("[L]ogin");
				System.out.println("[G]uest");
				System.out.println("[E]xit");

				String option = sc.nextLine().trim();
				if (option.equalsIgnoreCase("l")) {
					login(sc);
				} else if (option.equalsIgnoreCase("g")) {
					processGuest(sc);
				} else if (option.equalsIgnoreCase("e")) {
					persist(); // Store information to xml file.
					System.out.println("Thank you for the visiting us! Bye.");
					break;
				}
			}
		}
	}

	public void processGuest(Scanner sc) 
	{

		Guest g = model.getGuest();
		while (true) 
		{
			System.out.println(g.getMenu());		// the guest has a few options of browsing cars only or becoming a user where he  will need to create a listing
			String option = sc.nextLine();
			if (option.equalsIgnoreCase("b")) {
				String output = g.browseCars(model.getCars());
				if (output.isEmpty()) {
					System.out.println("No cars");
				} else {
					System.out.println(output);
				}

			} else if (option.equalsIgnoreCase("be")) {
				g.becomeOther(sc, model);
				break;
			} else if (option.equalsIgnoreCase("e")) {
				g.exit();
				break;
			}
		}
	}

	public void login(Scanner sc) 		// it checks the type of person, if he is an admin, a user or a seller, then it will provide the correct menu
	{
		System.out.println("Enter login:");
		String login = sc.nextLine().trim();
		System.out.println("Enter password:");
		String password = sc.nextLine().trim();
		Person p = model.login(login, password);
		if (p != null) {
			if (p.getType().equals("Admin")) {
				Admin a = (Admin)p;
				processAdmin(sc, a);
			} else if (p.getType().equals("User")) {
				User u = (User)p;
				processUser(sc, u);
			} else if (p.getType().equals("Seller")) {
				Seller s = (Seller)p;
				processSeller(sc, s);
			}
		} else {
			System.out.println("Invalid login or password.");
		}
	}

	public void processUser(Scanner sc, User u) 
	{
		
		System.out.println("Hello, " + u.getFirstName() + " " + u.getLastName() + "!");

		while (true) 
		{
			System.out.println(u.getMenu());
			String option = sc.nextLine().trim();
			if (option.equalsIgnoreCase("b")) {
				String output = u.browseCars(model.getCars());		// if there are no cars then output the follwing...
				if (output.isEmpty()) {
					System.out.println("No cars");
				} else {
					System.out.println(output);
				}
			} else if (option.equalsIgnoreCase("be")) {
				u.becomeOther(sc, model);
				if (!model.getUsers().contains(u)) {
					break;
				}
				
			} else if (option.equalsIgnoreCase("s")) {		// the options for users...
				u.sellCar(model, sc);
			} else if (option.equalsIgnoreCase("bu")) {
				u.buyCar(model, sc);
			} else if (option.equalsIgnoreCase("c")) {
				u.checkMail();
			} else if (option.equalsIgnoreCase("ch")) {
				System.out.println("Your balance: $" + u.checkBalance());
			} else if (option.equalsIgnoreCase("d")) {
				u.deposit(sc);
			} else if (option.equalsIgnoreCase("e")) {
				u.exit();
				break;
			}
		}
	}

	public void processAdmin(Scanner sc, Admin a) 
	{
		System.out.println("Hello, " + a.getFirstName() + " " + a.getLastName() + "!");
		while (true) 
		{
			System.out.println(a.getMenu());
			String option = sc.nextLine().trim();
			if (option.equalsIgnoreCase("d")) {
				a.deleteListing(model, sc);
			} else if (option.equalsIgnoreCase("r")) {
				a.removeUserSeller(model, sc);
			} else if (option.equalsIgnoreCase("c")) {
				a.checkUserInfo(model.getPersons());
			} else if (option.equalsIgnoreCase("de")) {
				a.deactivateUser(model, sc);
			} else if (option.equalsIgnoreCase("a")) {
				a.activateUser(model, sc);
			} else if (option.equalsIgnoreCase("m")) {
				a.makeAnnouncements(model, sc);
			} else if (option.equalsIgnoreCase("e")) {
				a.exit();
				break;
			}
		}
	}

	public void processSeller(Scanner sc, Seller s) 
	{
		System.out.println("Hello, " + s.getFirstName() + " " + s.getLastName() + "!");
		while (true) 
		{
			System.out.println(s.getMenu());
			String option = sc.nextLine().trim();
			if (option.equalsIgnoreCase("b")) {
				s.browseCars(model.getCars());
			} else if (option.equalsIgnoreCase("ch")) {
				System.out.println("Your balance: $" + s.checkBalance());
			} else if (option.equalsIgnoreCase("c")) {
				s.createListing(sc, model);
			} else if (option.equalsIgnoreCase("che")) {
				s.checkMail();
			} else if (option.equalsIgnoreCase("e")) {
				s.exit();
				break;
			}
		}
	}

	public static void main(String[] args) 
	{					// our main, will call the functions for the program to run
		View view = new View();
		view.init();
		view.start();
	}
}
