package com.example.community.mapper;

import com.example.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    public void create(Question question);
}
