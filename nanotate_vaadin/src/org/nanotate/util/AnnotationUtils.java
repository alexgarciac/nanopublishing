package org.nanotate.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.nanotate.model.Annotation;
import org.nanotate.model.AnnotationExample;
import org.nanotate.model.AnnotationMapper;
import org.nanotate.model.AnnotationWithBLOBs;
import org.nanotate.model.Ranges;
import org.nanotate.model.RangesExample;
import org.nanotate.model.RangesMapper;
import org.nanotate.model.Tag;
import org.nanotate.model.TagExample;
import org.nanotate.model.TagMapper;


public class AnnotationUtils {

	public static ArrayList<AnnotationWithBLOBs> getAnnotations(String doi,
			String user) {
		ArrayList<AnnotationWithBLOBs> annotations =  new ArrayList<AnnotationWithBLOBs>();
		
		try {
			SqlSession sqlSession = MyBatis.getSession();
			AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
			AnnotationExample example = new AnnotationExample();
			if(StringUtils.equals(user, "all"))
				example.createCriteria().andUriEqualTo(doi).andStatus_completedEqualTo(true);
			else
				example.createCriteria().andUriEqualTo(doi).andUserEqualTo(user).andStatus_completedEqualTo(true);
			
			example.setOrderByClause("created DESC");
			
			annotations = (ArrayList<AnnotationWithBLOBs>) mapper.selectByExampleWithBLOBs(example);
			
			sqlSession.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return annotations;
	}
	
	public static ArrayList<Tag> getTags(String annotation_uuid) {
		ArrayList<Tag> tags =  new ArrayList<Tag>();
		
		try {
			SqlSession sqlSession = MyBatis.getSession();
			TagMapper mapper = sqlSession.getMapper(TagMapper.class);
			TagExample example = new TagExample();
			example.createCriteria().andAnnotation_idEqualTo(annotation_uuid);
			example.setOrderByClause("automatic_tag");
		
			
			tags = (ArrayList<Tag>) mapper.selectByExample(example);
			
			sqlSession.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tags;
	}
	
	public static ArrayList<AnnotationWithBLOBs> getNew(AnnotationWithBLOBs lastannotation, String user)
	{
		
		ArrayList<AnnotationWithBLOBs> annotations =  new ArrayList<AnnotationWithBLOBs>();
		
		try {
			SqlSession sqlSession = MyBatis.getSession();
			AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
			AnnotationExample example = new AnnotationExample();
			if(StringUtils.equals(user, "all"))
				example.createCriteria().andUriEqualTo(lastannotation.getUri()).andCreatedGreaterThan(lastannotation.getCreated()).andStatus_completedEqualTo(true);
			else
				example.createCriteria().andUriEqualTo(lastannotation.getUri()).andUserEqualTo(user).andCreatedGreaterThan(lastannotation.getCreated()).andStatus_completedEqualTo(true);
			
			example.setOrderByClause("created DESC");
			
			annotations = (ArrayList<AnnotationWithBLOBs>) mapper.selectByExampleWithBLOBs(example);
			
			sqlSession.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("Size: "+annotations.size());
		
		return annotations;
		
	}
	
	
	public static Ranges getRanges(String annotation_id) {
		Ranges ranges=null;
		
		try {
			SqlSession sqlSession = MyBatis.getSession();
			RangesMapper mapper = sqlSession.getMapper(RangesMapper.class);
			RangesExample example = new RangesExample();
			example.createCriteria().andAnnotation_idEqualTo(annotation_id);
		
			
			ranges = mapper.selectByExample(example).get(0);
			
			sqlSession.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ranges;
	}

	public static boolean annotationsExists(String value, String user) {
		
		boolean ret=false;
		
		try {
			SqlSession sqlSession = MyBatis.getSession();
			AnnotationMapper mapper = sqlSession.getMapper(AnnotationMapper.class);
			AnnotationExample example = new AnnotationExample();
			if(StringUtils.equals(user, "all"))
				example.createCriteria().andUriEqualTo(value).andStatus_completedEqualTo(true);
			else
				example.createCriteria().andUriEqualTo(value).andUserEqualTo(user).andStatus_completedEqualTo(true);
			

			
			ArrayList<Annotation>annotations = (ArrayList<Annotation>) mapper.selectByExample(example);
			
			if(annotations.size()>0)
				ret=true;
			
			sqlSession.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	

}
