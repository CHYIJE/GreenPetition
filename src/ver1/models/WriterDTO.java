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
	private int uid;
	private String title;
	private String content;
	private String category; // 카테고리
}
