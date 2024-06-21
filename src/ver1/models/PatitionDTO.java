package ver1.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PatitionDTO {
	
	private int id;
	private int user_id;
	private Category category;
	private String title;
	private String content;
	private Date date;
	
	private int agree;
	private int disagree;

	
}
