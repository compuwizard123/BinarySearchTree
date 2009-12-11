
public class Faculty extends SSI {
	private String firstName;
	private String lastName;

	public Faculty(int id, String firstName, String lastName) {
		super(id);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String toString() {
		return super.toString() + " " + firstName + " " + lastName;
	}
}