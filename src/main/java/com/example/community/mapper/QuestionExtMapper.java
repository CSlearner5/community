package com.example.community.mapper;

import com.example.community.model.Question;
import com.example.community.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
}