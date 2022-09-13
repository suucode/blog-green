package site.metacoding.red.web.Dto.Request.Users;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UpdateDto {
	private String password;
	private String email;
}
