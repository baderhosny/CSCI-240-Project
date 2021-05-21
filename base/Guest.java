import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Guest extends Person 
{

	public Guest() 
	{
		super();
	}

	// Works for all subclasses as well.
	public void exit() 
	{
		System.out.println(getType() + " exited.");
	}

	public String browseCars(List<Car> cars)  //outputs all cars that are on sale and by active users
	{

		String output = "";

		for (Car car: cars) {
			if (((User)car.getOwner()).isActive()) { // Only active users can sell cars.
				output += car + "\n";
			}
		}

		return output;
	}

	// Guest can become User.
	public void becomeOther(Scanner sc, Model model) 
	{
		try {
			System.out.println("Enter login:");
			String login = sc.nextLine().trim();
			System.out.println("Enter password:");
			String password = sc.nextLine().trim();
			System.out.println("Enter first name:");
			String fname = sc.nextLine().trim();
			System.out.println("Enter last name:");
			String lname = sc.nextLine().trim();
			System.out.println("Enter dob:");
			String dob = sc.nextLine().trim();
			System.out.println("Enter initial balance:");		// isEmpty() is a function that returns true if its 0, so if it is "" it returns true
			double balance = Double.parseDouble(sc.nextLine().trim());
			if (login.isEmpty() || password.isEmpty() || fname.isEmpty() || lname.isEmpty() || balance < 0) {
				throw new Exception();
			}
			User user = new User(login, password, fname, lname, LocalDate.parse(dob).toString(), balance);

			System.out.println("Congratulations! You become a User.");		// if all information makes sense then you became a user!
			model.addPerson(user);


		} catch (Exception e) {
			System.out.println("Failed to become a User.");
		}
	}

	public String getType() {
		return "Guest";
	}

	public String getMenu() {
		return "[B]rowse cars \n[Be]come a user \n[E]xit";
	}
}
