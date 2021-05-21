public class Car {

	private String make;
	private String model;
	private int year;
	private String color;
	private int numPreviousOwners;
	private String historyAccident;
	private double price;
	private Person owner;	
	private String status; // For rent or selling.
	private boolean forSale; // True if for sale, otherwise false;

	public Car() 
	{
		historyAccident = "";
	}

	public Car(String make, String model, int year, String color, int numPreviousOwners, String historyAccident,
			double price, Person owner, String status) 
	{
		this();
		this.make = make;
		this.model = model;
		this.year = year;
		this.color = color;
		this.numPreviousOwners = numPreviousOwners;
		this.historyAccident = historyAccident;
		this.price = price;
		this.owner = owner;
		this.status = status;
	}

	public String getMake() 
	{
		return make;
	}

	public void setMake(String make) 
	{
		this.make = make;
	}

	public String getModel() 
	{
		return model;
	}

	public void setModel(String model) 
	{
		this.model = model;
	}

	public int getYear() 
	{
		return year;
	}

	public void setYear(int year) 
	{
		this.year = year;
	}

	public String getColor() 
	{
		return color;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}

	public int getNumPreviousOwners() 
	{
		return numPreviousOwners;
	}

	public void setNumPreviousOwners(int numPreviousOwners) 
	{
		this.numPreviousOwners = numPreviousOwners;
	}

	public String getHistoryAccident() 
	{
		return historyAccident;
	}

	public void setHistoryAccident(String historyAccident) 
	{
		this.historyAccident = historyAccident;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}

	

	public Person getOwner() 
	{
		return owner;
	}

	public void setOwner(Person owner) 
	{
		this.owner = owner;
	}

	

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public String toString() 		//outputs all information
	{
		return "Car [make=" + make + ", model=" + model + ", year=" + year + ", color=" + color + ", numPreviousOwners="
				+ numPreviousOwners + ", historyAccident=" + historyAccident + 
				", price=" + price + "$" + (status.equals("For rent") ? "/mo" : "") +
				", owner=" + owner
				+ ", status=" + status + "]";
	}

	public boolean isForSale() 
	{
		return forSale;
	}

	public void setForSale(boolean forSale) 
	{
		this.forSale = forSale;
	}
	
}
