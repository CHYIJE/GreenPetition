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
public class CheckerDTO {

	private int petition_id; // 글 ID
	private String name;
	private String title;
	private String content;
    private String category; // 카테고리
    private String date;
    private int agree;
    private int disagree;
    

	
}
