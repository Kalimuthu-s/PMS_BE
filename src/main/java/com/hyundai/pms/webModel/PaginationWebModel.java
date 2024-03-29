package com.hyundai.pms.webModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaginationWebModel {

	private Integer pageNo;

	private Integer pageSize;
	
	private String searchKey;

}
