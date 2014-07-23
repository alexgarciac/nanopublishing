package org.nanotate.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
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
				example.createCriteria().andUriEqualTo(doi);
			else
				example.createCriteria().andUriEqualTo(doi).andUserEqualTo(user);
			
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
	
	
	

}
