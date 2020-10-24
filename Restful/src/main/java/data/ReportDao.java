package data;


public class ReportDao {
	public static final String INSTANCE = null;
	private int id;
	private String name;
	private String email;
	private String problem;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	@Override
	public String toString() {
		return "ReportDao [id=" + id + ", name=" + name + ", email=" + email + ", problem=" + problem + "]";
	}
	
}

