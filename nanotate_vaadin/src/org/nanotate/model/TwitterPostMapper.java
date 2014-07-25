package org.nanotate.model;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.nanotate.model.TwitterPost;
import org.nanotate.model.TwitterPostExample;
import org.nanotate.model.TwitterPostKey;

public interface TwitterPostMapper {
    int countByExample(TwitterPostExample example);

    int deleteByExample(TwitterPostExample example);

    int deleteByPrimaryKey(TwitterPostKey key);

    int insert(TwitterPost record);

    int insertSelective(TwitterPost record);

    List<TwitterPost> selectByExample(TwitterPostExample example);

    TwitterPost selectByPrimaryKey(TwitterPostKey key);

    int updateByExampleSelective(@Param("record") TwitterPost record, @Param("example") TwitterPostExample example);

    int updateByExample(@Param("record") TwitterPost record, @Param("example") TwitterPostExample example);

    int updateByPrimaryKeySelective(TwitterPost record);

    int updateByPrimaryKey(TwitterPost record);
}