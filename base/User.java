import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User extends Guest implements Mail 
{

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String dob;
	private double balance;
	private boolean active;
	private List<Message> messages;

	public User(String username, String password, String firstName, String lastName, String dob, double balance) {
		this();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.balance = balance;		
	}

	public User() {
		super();
		active = true;
		messages = new ArrayList<>();
	}

	// Overrides this method from superclass to become a Seller.
	public void becomeOther(Scanner sc, Model model) 		// in order to become a seller you need to provide a SSN and have 100k in balance
	{
		if (balance >= 100000) 
		{
			System.out.println("Enter SSN:");
			String ssn = sc.nextLine().trim();
			if (!ssn.isEmpty()) 
			{
				Seller s = new Seller(username, password, firstName, lastName, dob, balance, ssn);
				model.addPerson(s);
				model.getPersons().remove(this);
				System.out.println("Congratulations! Now you are a Seller.");
			}
		} else {
			System.out.println("Not enoudh money.");
		}
	}

	public Car addCar(Scanner sc) 
	{
		System.out.println("Enter make:");		// asks questions regarding the car
		String make = sc.nextLine().trim();
		System.out.println("Enter model:");
		String modl = sc.nextLine().trim();
		System.out.println("Enter year:");
		int year = Integer.parseInt(sc.nextLine().trim());
		System.out.println("Enter color:");
		String color = sc.nextLine().trim();
		System.out.println("Enter accident (if there was):");
		String accident = sc.nextLine().trim();
		System.out.println("Enter price:");
		double price = Double.parseDouble(sc.nextLine().trim());
		System.out.println("Select status: [R]ent or [S]ale");
		String opt = sc.nextLine().trim();
		String status = null;
		if (opt.equalsIgnoreCase("r")) 
		{
			status = "For rent";
		} else if (opt.equalsIgnoreCase("s")) 
		{
			status = "For sale";
		}

		if (make.isEmpty() || modl.isEmpty() || color.isEmpty() || price < 0 ||
				year < 1900 || status == null) 
		{
			return null;
		}

		Car c = new Car(make, modl, year, color, 0, accident, price, this, status);

		return c;
	}

	// Only 1 car is allowed for User.
	public void sellCar(Model model, Scanner sc) 
	{
		if (model.getNumCarsForSale(this) == 0) 
		{
			Car c;
			if ((c = addCar(sc)) != null) 
			{
				model.addCar(c);
				c.setForSale(true);
				System.out.println("Successfully added.");
			} else 
			{
				System.out.println("Failed");
			}
			
		} else 
		{
			System.out.println("Failed: User can sell only 1 car.");
		}
	}

	public void buyCar(Model model, Scanner sc) 
	{
		List<Car> cars = model.getCarsForSale();
		if (cars.isEmpty()) 		// if the car list is empty then output the following..
		{
			System.out.println("No cars for sale");
			return;
		}
		System.out.println("Select car (index):");
		for (int i = 0; i < cars.size(); i ++) 
		{
			System.out.println(i + "  " + cars.get(i));
		}
		String opt = sc.nextLine().trim();
		try 
		{
			int idx = Integer.parseInt(opt);
			Car c = cars.get(idx);

			if (c != null) 
			{
				if (c.getStatus().equals("For sale")) 
				{
					if (c.getPrice() <= balance) 	// subtract money when bought
					{
						c.setOwner(this);
						c.setForSale(false);
						c.setStatus("");
						c.setNumPreviousOwners(c.getNumPreviousOwners() + 1);
						balance -= c.getPrice();
						System.out.println("Congratulations! You bought a car.");
					} else 
					{
						System.out.println("Failed: not enough money.");
					}
				} else 
				{ // If for rent.
					if (balance < c.getPrice() * 3) 		// if you are renting then you need to  have atleast x3 the price
					{		
						System.out.println("Failed: not enough money.");
					} else 
					{
						c.setOwner(this);
						c.setForSale(false);
						c.setStatus("");
						c.setNumPreviousOwners(c.getNumPreviousOwners() + 1);
						balance -= c.getPrice() * 3;
						System.out.println("Congratulations! You rented a car.");
					}
				}
				
			} else {
				throw new Exception();
			}


		} catch (Exception e) 
		{
			System.out.println("Failed: check input.");
		}
	}

	public void checkMail() 
	{

		System.out.println("Unchecked messages:");		// checks all messages
		
		for (Message m: messages) 
		{
			if (!m.isChecked()) 
			{
				m.setChecked(true);
				System.out.println(m);
			}
		}
	}

	public double checkBalance() 
	{
		return balance;
	}

	public void deposit(Scanner sc) 
	{
		System.out.println("Enter amount:");
		try 
		{
			double amount = Double.parseDouble(sc.nextLine());
			if (amount < 0) 
			{
				throw new Exception();
			}
			balance += amount;
			System.out.println("Successfully increased.");
		} catch (Exception e) {
			System.out.println("Failed to deposit. Check input.");
		}
	}

	public String getMenu() 
	{
		return "[B]rowse cars\n[Be]come a Seller\n[S]ell a car\n[Bu]y a car"
				+ "\n[C]heck email\n[Ch]eck balance\n[D]eposit money\n[E]xit";
	}

	public String getType() 
	{
		return "User";
	}

	public void sendMessage(String text) 		//localdatetime gives us the time wwhen the message was sent
	{
		messages.add(new Message(LocalDateTime.now().toString(), text, false));
	}

	public String getUsername() //return username
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getDob() 		//get date of birth
	{
		return dob;
	}

	public void setDob(String dob) 
	{
		this.dob = dob;
	}

	public double getBalance() 	//get balance, with double it also comes with fractions
	{
		return balance;
	}

	public void setBalance(double balance) 
	{
		this.balance = balance;
	}

	public boolean isActive() 		//whether user is active or not
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}

	public List<Message> getMessages() 
	{
		return messages;
	}

	public void setMessages(List<Message> messages) 
	{
		this.messages = messages;
	}

	public String toString() 
	{
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", balance=" + balance + ", active=" + active + "]";
	}


}
