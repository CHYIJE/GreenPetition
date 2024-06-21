package ver1.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatitionDTO {
	
	private int id;
	private int user_id;
	private Category category;
	private String title;
	private String date;
	private int agree;
	private int disagree;
	
}
