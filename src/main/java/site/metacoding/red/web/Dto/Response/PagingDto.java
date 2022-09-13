package site.metacoding.red.web.Dto.Response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PagingDto {
	private boolean inNotResult;
	private String keyword;
	private Integer blockCount;
	private Integer currentBlock;
	private Integer startPageNum;
	private Integer lastPageNum;
	private Integer totalCount;
	private Integer totalPage;
	private Integer currentPage;
	private boolean isLast;
	private boolean isFirst;
	
	private List<MainDto> mainDtos;
	
	public void makeBlockInfo(String keyword) {
		this.keyword = keyword;
		this.blockCount = 5;

		this.currentBlock = currentPage / blockCount;
		this.startPageNum = 1 + blockCount * currentBlock;
		this.lastPageNum = 5 + blockCount * currentBlock;

		if (totalPage < lastPageNum) {
			this.lastPageNum = totalPage;
		}
	}
}
