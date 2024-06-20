package ver1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WriterDTO {

	private int user_id; // 사용자 ID
	private String uid;
	private String title;
	private String content;
<<<<<<< HEAD
    private String category; // 카테고리
=======
	private String category;
	private String date;
>>>>>>> 7a049af10a212519f12a6093b369196d34baf686
}
