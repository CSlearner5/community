package com.example.community.service;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.dto.QuestionQueryDTO;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.CommentMapper;
import com.example.community.mapper.QuestionExtMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serivce层起到一个组装各实体关系映射的作用
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentMapper commentMapper;

    public PaginationDTO list(Integer page, Integer size, String search) {
        if(StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);
        //Integer totalCount = questionMapper.count();

        if(totalCount == 0) {
            return paginationDTO;
        }
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();

        Integer offset = size * (page - 1);

        //List<Question> questions = questionMapper.list(offset, size);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("get_create desc");
        questionQueryDTO.setOffset(offset);
        questionQueryDTO.setSize(size);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for(Question question : questions) {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            User user = users.get(0);
            //User user = userMapper.findByAccountId(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //通过反射机制，将对应名字的属性值进行拷贝
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO list(String userId, Integer page, Integer size, Boolean isQuestion) {
        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample questionExample = new QuestionExample();
        if(isQuestion) {
            questionExample.createCriteria().andCreatorEqualTo(userId);
        }else {
            questionExample.createCriteria().andCreatorEqualTo(userId).andNewViewEqualTo(1);
        }

        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        //Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount == 0) {
            return paginationDTO;
        }
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();

        Integer offset = size * (page - 1);
        QuestionExample example = new QuestionExample();
        if(isQuestion) {
            example.createCriteria().andCreatorEqualTo(userId);
        }else {
            example.createCriteria().andCreatorEqualTo(userId).andNewViewEqualTo(1);
        }
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        //List<Question> questions = questionMapper.listByUserId(userId, offset, size);

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(userId);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        //User user = userMapper.findByAccountId(userId);
        for(Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); //通过反射机制，将对应名字的属性值进行拷贝
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if(question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(question.getCreator());
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        //User user = userMapper.findByAccountId(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null) {
            question.setGetCreate(System.currentTimeMillis());
            question.setGetModified(question.getGetCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setNewView(0);
            questionMapper.insert(question);
        }else {
            Question updateQuestion = new Question();
            updateQuestion.setGetModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            if(updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        //Question question = questionMapper.selectByPrimaryKey(id);
        //Question updateQuestion = new Question();
        //updateQuestion.setViewCount(question.getViewCount() + 1);
        //QuestionExample questionExample = new QuestionExample();
        //questionExample.createCriteria().andIdEqualTo(id);
        //questionMapper.updateByExampleSelective(updateQuestion, questionExample);

        //保证并发不会出错
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if(StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOs = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOs;
    }

    public List<QuestionDTO> topicList() {
        Integer offset = 0;
        Integer size = 8;
        List<Question> topicQuestions = questionExtMapper.topicList(offset, size);
        List<QuestionDTO> topicDTOs = new ArrayList<>();
        for(Question topic : topicQuestions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(topic, questionDTO);
            UserExample userExample = new UserExample();
            userExample.createCriteria().andAccountIdEqualTo(topic.getCreator());
            List<User> users = userMapper.selectByExample(userExample);
            User user = users.get(0);
            questionDTO.setUser(user);
            topicDTOs.add(questionDTO);
        }
        return topicDTOs;
    }

    public Long countQuestions(String accountId) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(accountId);
        return questionMapper.countByExample(questionExample);
    }

    public Long countReplies(String accountId) {
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(accountId).andNewViewEqualTo(1);
        return questionMapper.countByExample(questionExample);
    }

    public void seeView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setNewView(0);
        questionExtMapper.showView(question);
    }

    public void setView(Long parentId, String account) {
        if(!questionMapper.selectByPrimaryKey(parentId).getCreator().equals(account)) {
            Question question = new Question();
            question.setId(parentId);
            question.setNewView(1);
            questionExtMapper.showView(question);
        }
    }

    public void setViewChild(Long parentId, Long userId) {
        if(!commentMapper.selectByPrimaryKey(parentId).getCommentator().equals(userId)) {
            Comment comment = commentMapper.selectByPrimaryKey(parentId);
            Question question = new Question();
            question.setId(comment.getParentId());
            question.setNewView(1);
            questionExtMapper.showView(question);
        }

    }
}
