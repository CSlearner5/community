package com.example.community.enums;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001, "所用的问题不存在, 请重新操作"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "请先登录系统"),
    SYS_ERROR(2004, "服务器有点忙, 请稍后重试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在, 请重新操作"),
    COMMENT_IS_EMPTY(2007, "输入内容不能为空"),
    GITHUB_SLOW(2008, "GitHub认证卡顿, 请稍后重试")
    ;

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


}
