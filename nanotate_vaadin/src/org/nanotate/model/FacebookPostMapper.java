package org.nanotate.model;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.nanotate.model.FacebookPost;
import org.nanotate.model.FacebookPostExample;
import org.nanotate.model.FacebookPostKey;

public interface FacebookPostMapper {
    int countByExample(FacebookPostExample example);

    int deleteByExample(FacebookPostExample example);

    int deleteByPrimaryKey(FacebookPostKey key);

    int insert(FacebookPost record);

    int insertSelective(FacebookPost record);

    List<FacebookPost> selectByExample(FacebookPostExample example);

    FacebookPost selectByPrimaryKey(FacebookPostKey key);

    int updateByExampleSelective(@Param("record") FacebookPost record, @Param("example") FacebookPostExample example);

    int updateByExample(@Param("record") FacebookPost record, @Param("example") FacebookPostExample example);

    int updateByPrimaryKeySelective(FacebookPost record);

    int updateByPrimaryKey(FacebookPost record);
}