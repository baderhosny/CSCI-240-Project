import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Admin extends Seller {	//extended from seller

	public Admin(String username, String password, String firstName, String lastName, String dob, double balance,
			String ssn) {
		super(username, password, firstName, lastName, dob, balance, ssn);
	}

	public Admin() {
		super();
	}

	public void deleteListing(Model model, Scanner sc) {		// runs through the file and check if the user exists, if it does then it will get deleted
		System.out.println("Enter first name:");
		String fname = sc.nextLine().trim();
		System.out.println("Enter last name:");
		String lname = sc.nextLine().trim();		//trim() is for cosmetics, it looks for the unneeded spaces from back and end of th text
		Person p = model.getByFirstLastName(fname, lname);
		if (p != null) {
			model.deleteListings(p);
			System.out.println("Successfully deleted.");
		} else {
			System.out.println("Failed: user/seller does not exist.");
		}
	}

	public void removeUserSeller(Model model, Scanner sc) {			// if found, delete
		System.out.println("Enter first name:");
		String fname = sc.nextLine().trim();
		System.out.println("Enter last name:");
		String lname = sc.nextLine().trim();
		if (model.removePerson(fname, lname)) {
			System.out.println("Successfully deleted.");
		} else {
			System.out.println("Failed: not found.");
		}
	}

	public void checkUserInfo(List<Person> persons) {		//outputs all users (persons)
		for (Person p: persons) {
			System.out.println(p);
		}
	}

	public void deactivateUser(Model model, Scanner sc) {			// we check if user exists and active inorder to deactivate, the users cars for sale won't be displayed to others
		System.out.println("Enter first name:");
		String fname = sc.nextLine().trim();
		System.out.println("Enter last name:");
		String lname = sc.nextLine().trim();
		Person p = model.getByFirstLastName(fname, lname);
		if (p != null) {
			User u = (User)p; // Anyway it is user because guest does not have first/last name, and
			// Seller is a subclass of User.

			if (u.isActive()) {
				u.setActive(false);
				System.out.println("Deactivated successfully.");
			} else {
				System.out.println("Failed: user is not active.");
			}
		} else {
			System.out.println("User not found.");
		}
	}

	public void activateUser(Model model, Scanner sc) {		
		System.out.println("Enter first name:");
		String fname = sc.nextLine().trim();
		System.out.println("Enter last name:");
		String lname = sc.nextLine().trim();
		Person p = model.getByFirstLastName(fname, lname);
		if (p != null) {
			User u = (User)p; // Anyway it is user because guest does not have first/last name, and
			// Seller is a subclass of User.

			if (!u.isActive()) {
				u.setActive(true);
				System.out.println("Activated successfully.");
			} else {
				System.out.println("Failed: user is active.");
			}
		} else {
			System.out.println("User not found.");
		}
	}

	public void makeAnnouncements(Model model, Scanner sc) {		// all messages will be sent to everyone's mail box
		System.out.println("Enter announcement:");
		String text = sc.nextLine().trim();
		for (User u: model.getUsers()) {
			u.getMessages().add(new Message(LocalDateTime.now().toString(), text, false));
		}
		System.out.println("Sending...");
	}

	public String getMenu() {
		return "[D]elete listing\n[R]remove users/sellers\n[C]heck user info"
				+ "\n[De]activate user\n[A]ctivate user\n[M]ake announcements\n[E]xit";
	}

	public String getType() {
		return "Admin";
	}
}
