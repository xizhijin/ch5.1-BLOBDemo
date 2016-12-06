package com.hpe.mybatis.ch5.main;

import java.io.File;
import java.io.FileInputStream;

import org.apache.ibatis.session.SqlSession;

import com.hpe.mybatis.ch5.mapper.TFileMapper;
import com.hpe.mybatis.ch5.po.TFile;
import com.hpe.mybatis.ch5.util.SqlSessionFactoryUtil;

public class Ch5Main {

	public static void main(String[] args) throws Exception {
//		insert();
		getFile();

	}
	
	public static void insert() throws Exception {
		File file = new File("test.txt");
		FileInputStream in = new FileInputStream(file);
		byte[] bytes = new byte[(int)file.length()];
		try {
			in.read(bytes);
		} finally {
			in.close();
		}
		
		TFile tFile = new TFile();
		tFile.setFile(bytes);
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			TFileMapper tFileMapper = sqlSession.getMapper(TFileMapper.class);
			tFileMapper.insertFile(tFile);
			System.out.println(tFile.getId());
			sqlSession.commit();
		} catch (Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	public static void getFile() {
		SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
		try {
			TFileMapper tFileMapper = sqlSession.getMapper(TFileMapper.class);
			TFile tFile = tFileMapper.getFile(1);
			System.out.println(tFile.getId() + "  =======> " + tFile.getFile().length);
		} catch (Exception ex) {
			sqlSession.rollback();
			ex.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
}
