package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.web.Dto.Response.MainDto;
import site.metacoding.red.web.Dto.Response.PagingDto;

public interface BoardsDao {
	public void insert(Boards boards); //보통은 이름을 save로 많이 짓는다
	public List<MainDto> findAll(int startNum, String keyword);
	public Boards findById(Integer id);
	public void update(Boards boards);
	public void deleteById(Integer id);
	public void updateByUsersId(Integer id);
	public PagingDto paging(@Param("page") Integer page, @Param("keyword") String keyword);
}
