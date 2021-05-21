import java.util.Scanner;

public class Seller extends User 		// the seller does most of what the user does, but will be able to list 15 cars and must provide a SSN inorder to become a seller and a minimum of 100k$
{
	
	private String ssn;

	public Seller(String username, String password, String firstName, 
			String lastName, String dob, double balance, String ssn) 
	{
		super(username, password, firstName, lastName, dob, balance);
		this.ssn = ssn;
	}
	
	public Seller() 
	{
		super();
	}

	public String getSsn() 
	{
		return ssn;
	}

	public void setSsn(String ssn) 
	{
		this.ssn = ssn;
	}
	
	public void createListing(Scanner sc, Model model) 		//maximum aamount is 15 listings
	{
		int maxCars = 15 - model.getNumCars(this);
		if (maxCars == 0) 
		{
			System.out.println("Failed to create listing: maximum number of cars (15) reached.");
			return;
		}
		System.out.println("Enter number of cars you want to sell/rent (" + maxCars + " max):");
		int numCars = Integer.parseInt(sc.nextLine().trim());
		if (numCars < 1 || numCars > maxCars) 
		{
			System.out.println("Failed to become a Seller, you can add up to " + maxCars + " cars, but at least 1 car.");
		} else {
			for (int i = 0; i < numCars; i ++) 
			{
				try 
				{
					Car c = addCar(sc);
					model.addCar(c);
					System.out.println("Car added successfully.");
				} 
				catch (Exception e) 
				{
					System.out.println("Failed to add car. Check input..");
				}
			}
		}
	}
	
	public String getMenu() 
	{
		return "[B]rowse cars\n[Ch]eck balance\n[C]reate listing\n[Che]ck mail\n[E]xit";
	}
	
	public String getType() 
	{
		return "Seller";
	}
}
