package site.metacoding.red.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.Dto.Request.Boards.UpdateDto;
import site.metacoding.red.web.Dto.Request.Boards.WriteDto;
import site.metacoding.red.web.Dto.Response.MainDto;
import site.metacoding.red.web.Dto.Response.PagingDto;

@RequiredArgsConstructor
@Service //트랜잭션관리시에만 사용
public class BoardsService {
	
	private final UsersDao usersDao;
	private final BoardsDao boardsDao;

	public PagingDto 게시글목록보기(Integer page, String Keyword) {
		if (page == null) {
			page = 0;
		}
		int startNum = page * 3;
		List<MainDto> boardsList = boardsDao.findAll(startNum, Keyword);
		PagingDto pagingDto = boardsDao.paging(page, Keyword);
		if(boardsList.size() == 0)
			pagingDto.setInNotResult(true);
		pagingDto.makeBlockInfo(Keyword);
		pagingDto.setMainDtos(boardsList);
		return pagingDto;
		
	}
	public Boards 게시글상세보기(Integer id) {
		return boardsDao.findById(id);
	}
	public void 게시글수정하기(Integer id, UpdateDto updateDto) {
		Boards boardsPS = boardsDao.findById(id);
		if(boardsPS == null) {
			
		}
		
		boardsPS.update(updateDto);
		
		boardsDao.update(boardsPS);
	}
	public void 게시글삭제하기(Integer id) {
		Boards boardsPS = boardsDao.findById(id);
		if(boardsPS == null) {
			
		}		
	}
	public void 게시글쓰기(WriteDto writeDto, Users principal) {
		boardsDao.insert(writeDto.toEntity(principal.getId()));
	}
}
