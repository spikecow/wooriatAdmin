package com.wooriat.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Shot_Sell2")
public class ShotSell {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sell_id")
	private Long sellId;

	@Column(name = "tot_news_seq_no", nullable = false)
	private Integer totNewsSeqNo;

	@Column(name = "news_cd", length = 2, nullable = false)
	private String newsCd;

	@Column(name = "news_seq_no", nullable = false)
	private Integer newsSeqNo;

	@Column(name = "member_id", length = 14, nullable = false)
	private String memberId;

	@Column(name = "reg_date", nullable = false)
	private LocalDateTime regDate;

	@Column(name = "news_title", length = 100, nullable = false)
	private String newsTitle;

	@Column(name = "news_content", length = 10485760, nullable = false)
	private String newsContent;

	@Column(name = "click_cnt", nullable = false)
	private Integer clickCnt;

	@Column(name = "Sort_Status", length = 30)
	private String sortStatus;

	@Column(name = "news_today_yn", length = 100)
	private String newsTodayYn;

	@Column(name = "news_pub_date", length = 100)
	private String newsPubDate;

	@Column(name = "insertFile1", length = 1000)
	private String insertFile1;

	@Column(name = "insertFile2", length = 1000)
	private String insertFile2;

	@Column(name = "insertFile3", length = 1000)
	private String insertFile3;

	@Column(name = "insertFile4", length = 1000)
	private String insertFile4;

	@Column(name = "insertFile5", length = 1000)
	private String insertFile5;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uid")
	private TbUser userInfo;

	@Builder
	public ShotSell(
			Long sellId,
			LocalDateTime regDate,
			String newsTitle,
			Integer clickCnt,
			String sortStatus,
			String insertFile1,
			String insertFile2,
			String insertFile3,
			String insertFile4,
			String insertFile5,
			TbUser userInfo){

		this.sellId = sellId;
		this.totNewsSeqNo = 0;
		this.newsCd = "35";
		this.newsSeqNo = 0;
		this.memberId = "관리자";
		this.regDate = regDate;
		this.newsTitle = newsTitle;
		this.newsContent = newsTitle;
		this.clickCnt = clickCnt;
		this.sortStatus = sortStatus;
		this.insertFile1 = insertFile1;
		this.insertFile2 = insertFile2;
		this.insertFile3 = insertFile3;
		this.insertFile4 = insertFile4;
		this.insertFile5 = insertFile5;
		this.userInfo = userInfo;

	}
}
