package com.example.community.service;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.User;
import com.example.community.model.UserExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();

        if(totalCount == 0) {
            return paginationDTO;
        }
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
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

    public PaginationDTO list(String userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount == 0) {
            return paginationDTO;
        }
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
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

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
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
            questionMapper.create(question);
        }else {
            question.setGetModified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
