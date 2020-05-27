package com.example.community.mapper;

import com.example.community.model.Comment;
import com.example.community.model.CommentExample;
import com.example.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}