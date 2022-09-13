package site.metacoding.red.web.Dto.Request.Users;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LoginDto {
	private String username;
	private String password;
}
