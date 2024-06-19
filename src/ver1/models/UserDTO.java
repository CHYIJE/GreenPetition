package ver1.models;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String name;
	private String acc_id;
	private String acc_pw;
=======
public class UserDTO {
	private int id;
	private String 회원이름;
	private String 아이디;
	private String 비번;
>>>>>>> e6b776f635e02e46e1846d5ca5d609be4bef3b5e
}
