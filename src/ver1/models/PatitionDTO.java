package ver1.models;

import java.sql.Date;
import java.sql.Timestamp;

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
	private Timestamp date;

	private String acc_id;

	private int agree;
	private int disagree;

}
