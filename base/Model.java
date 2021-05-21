import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Model 
{

	private List<Person> persons;
	private List<Car> cars;

	public Model() 
	{
		persons = new ArrayList<>();
		cars = new ArrayList<>();
	}
	
	public void addPerson(Person p) 
	{
		persons.add(p);
	}
	
	public void addCar(Car c) 
	{
		cars.add(c);
	}
	
	public void deleteListings(Person p) 
	{					// this "->" is lambda, this means that it will remove the listing of the car if the owner matches, so if the owner is actually is the owner of te car, then delete
		cars.removeIf(c -> c.getOwner().equals(p));
	}
	
	public List<User> getUsers() 
	{
		List<User> users = new ArrayList<>();
		for (Person p: persons) {
			if (p instanceof User) {
				users.add((User)p);
			}
		}
		return users;
	}
	
	public int getNumCars(Person p) 
	{					// this will get the amount of cars owned by the person from all the cars that are in the program
		return (int) cars.stream().filter(c -> c.getOwner().equals(p)).count();
	}
	
	public int getNumCarsForSale(Person p) 
	{
		return (int) cars.stream().filter(c -> c.getOwner().equals(p) && c.isForSale()).count();		// we are applying a filters here, it checks if the car is owned by that person and if the car is on sale
	}
	
	public List<Car> getCarsForSale() 		// checks if the car is onsale and it gets the owner of the car, it also checks if the owner is an active user and transforming all of this into a list
	{
		return cars.stream().filter(c -> c.isForSale() && c.getOwner() instanceof User && 
				((User)c.getOwner()).isActive()).collect(Collectors.toList());
	}
	
	public Person getByFirstLastName(String fname, String lname) 
	{
		for (Person p: persons) {
			if (p instanceof User) {
				User u = (User)p;
				if (u.getFirstName().equals(fname) && u.getLastName().equals(lname)) {
					return u;
				}
			}
		}
		return null;
	}
	
	public boolean removePerson(String fname, String lname) 		// when the given input it looks through the list and see if those input matches the given firstname and last name, if it matches then delete/remove
	{	
		return persons.removeIf(p -> p instanceof User && 
				((User)p).getFirstName().equals(fname) && ((User)p).getLastName().equals(lname));
	}
	
	public Guest getGuest() 
	{
		return (Guest) persons.stream().filter(p -> p.getType().equals("Guest")).findFirst().get();
	}
	
	public Person login(String login, String password) 			// itterate through the saved users and see if the given input matches and then login...
	{
		for (Person p: persons) {
			if (p instanceof User) {
				User u = (User)p;
				if (u.getUsername().equals(login) && u.getPassword().equals(password)) {
					return p;
				}
			}
		}
		return null;
	}
	
	// Returns cars belong to the given person.
	public List<Car> getCars(Person p) 
	{
		return cars.stream().filter(c -> c.getOwner().equals(p)).collect(Collectors.toList());
	}

	public List<Person> getPersons() 
	{
		return persons;
	}

	public void setPersons(List<Person> persons) 
	{
		this.persons = persons;
	}

	public List<Car> getCars() 
	{
		return cars;
	}

	public void setCars(List<Car> cars) 
	{
		this.cars = cars;
	}

}
