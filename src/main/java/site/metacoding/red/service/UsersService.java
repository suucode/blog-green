package site.metacoding.red.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.Dto.Request.Users.JoinDto;
import site.metacoding.red.web.Dto.Request.Users.LoginDto;
import site.metacoding.red.web.Dto.Request.Users.UpdateDto;

@RequiredArgsConstructor
@Service
public class UsersService {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;
	
	public void 회원가입(JoinDto joinDto) { //username, password, email
		//1. Dto를 Entity로 변경하는 코드
		Users users = joinDto.toEntity();
		//2. Entity로 DB 수행
		usersDao.insert(users);
	}
	public Users 로그인(LoginDto loginDto) { //username, password
		Users usersPS = usersDao.findByUsername(loginDto.getUsername()); //아직 안만듦
		
		//if로 usersPS의 password와 Dto의 password 비교
		if(usersPS.getPassword().equals(loginDto.getPassword())) {
			return usersPS;
		}else {
			return null;
		}
	}
	public void 회원수정(Integer id, UpdateDto updateDto) { //id, Dto(password, email)
		//1. 영속화
		Users usersPS = usersDao.findById(id);
		//2. 영속화된 객체 변경
		usersPS.update(updateDto);
		//3. db수행
		usersDao.update(usersPS);
	}
	
	@Transactional(rollbackFor=RuntimeException.class)
	public void 회원탈퇴(Integer id) {
		usersDao.deleteById(id);
		boardsDao.updateByUsersId(id);
	} //Users - delete, Boards - update
	
	public boolean 아이디중복확인(String usersname) {
		Users usersPS = usersDao.findByUsername(usersname);
		
		if(usersPS == null) {
			return false;
		}else {
			return true;
		}
		// 있으면 true, 없으면 false
		
	}
	
	public Users 회원정보보기(Integer id) {
		Users usersPS = usersDao.findById(id);
		return usersPS;
	}
}
