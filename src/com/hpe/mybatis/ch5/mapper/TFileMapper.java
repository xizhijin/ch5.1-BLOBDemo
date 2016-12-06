package com.hpe.mybatis.ch5.mapper;

import com.hpe.mybatis.ch5.po.TFile;

public interface TFileMapper {
	public TFile getFile(int id);
	public int insertFile(TFile tFile);
}
