import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor

public class FormData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String sid;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String address;
	private String universityStream;
	private String courseDetails;
	private double cgpa;
	private int year;
	

}
