package com.example.community.cache;

import com.example.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOs = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("Java", "JavaScript", "CSS", "HTML", "Python", "C++", "Go", "C", "CUDA", "MatLab",
                "JSON", "XML", "YAML", "TypeScript", "Shell Script", "JSP", "Properties", "Less", "Markdown"));
        tagDTOs.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("Spring", "Spring Boot", "Django", "express", "struts", "koa", "微信小程序"));
        tagDTOs.add(framework);

        TagDTO network = new TagDTO();
        network.setCategoryName("神经网络");
        network.setTags(Arrays.asList("CNN", "RNN", "TensorFlow", "PyTorch"));
        tagDTOs.add(network);

        TagDTO other = new TagDTO();
        other.setCategoryName("其他");
        other.setTags(Arrays.asList("欢迎", "点赞", "差评", "技术", "其他"));
        tagDTOs.add(other);
        return tagDTOs;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ',');
        List<TagDTO> tagDTOs = get();

        List<String> tagList = tagDTOs.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
