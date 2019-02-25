package com.fshl.xy.weizhan.vo;

import java.util.List;

import com.fshl.xy.weizhan.entity.Prod;
import com.fshl.xy.weizhan.entity.ProdParam;

public class ProdListVO extends Prod {
	
	private List<ProdParam> mainParams;

	public List<ProdParam> getMainParams() {
		return mainParams;
	}

	public void setMainParams(List<ProdParam> mainParams) {
		this.mainParams = mainParams;
	}
	
}
