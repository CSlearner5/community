package com.example.community.service;

import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.enums.CustomizeErrorCode;
import com.example.community.exception.CustomizeException;
import com.example.community.mapper.QuestionExtMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.QuestionExample;
import com.example.community.model.User;
import com.example.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        //Integer totalCount = questionMapper.count();

        if(totalCount == 0) {
            return paginationDTO;
        }
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();

        Integer offset = size * (page - 1);

        //List<Question> questions = questionMapper.list(offset, size);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));
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

        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);
        //Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount == 0) {
            return paginationDTO;
        }
        paginationDTO.setPagination(totalCount, page, size);

        page = paginationDTO.getPage();

        Integer offset = size * (page - 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
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
}
