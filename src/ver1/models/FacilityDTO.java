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
public class FacilityDTO {

	private int id;
	private int user_id;
	private Category category;
	private String title;
	private Date date;

	private String acc_id;

	private int agree;
	private int disagree;

}