package com.example.community.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.ID
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TITLE
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GET_CREATE
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Long getCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.GET_MODIFIED
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Long getModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.CREATOR
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.VIEW_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.LIKE_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.TAG
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.NEW_VIEW
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private Integer newView;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column QUESTION.DESCRIPTION
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.ID
     *
     * @return the value of QUESTION.ID
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.ID
     *
     * @param id the value for QUESTION.ID
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TITLE
     *
     * @return the value of QUESTION.TITLE
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TITLE
     *
     * @param title the value for QUESTION.TITLE
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GET_CREATE
     *
     * @return the value of QUESTION.GET_CREATE
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Long getGetCreate() {
        return getCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GET_CREATE
     *
     * @param getCreate the value for QUESTION.GET_CREATE
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setGetCreate(Long getCreate) {
        this.getCreate = getCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.GET_MODIFIED
     *
     * @return the value of QUESTION.GET_MODIFIED
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Long getGetModified() {
        return getModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.GET_MODIFIED
     *
     * @param getModified the value for QUESTION.GET_MODIFIED
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setGetModified(Long getModified) {
        this.getModified = getModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.CREATOR
     *
     * @return the value of QUESTION.CREATOR
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.CREATOR
     *
     * @param creator the value for QUESTION.CREATOR
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.COMMENT_COUNT
     *
     * @return the value of QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.COMMENT_COUNT
     *
     * @param commentCount the value for QUESTION.COMMENT_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.VIEW_COUNT
     *
     * @return the value of QUESTION.VIEW_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.VIEW_COUNT
     *
     * @param viewCount the value for QUESTION.VIEW_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.LIKE_COUNT
     *
     * @return the value of QUESTION.LIKE_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.LIKE_COUNT
     *
     * @param likeCount the value for QUESTION.LIKE_COUNT
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.TAG
     *
     * @return the value of QUESTION.TAG
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.TAG
     *
     * @param tag the value for QUESTION.TAG
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.NEW_VIEW
     *
     * @return the value of QUESTION.NEW_VIEW
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public Integer getNewView() {
        return newView;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.NEW_VIEW
     *
     * @param newView the value for QUESTION.NEW_VIEW
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setNewView(Integer newView) {
        this.newView = newView;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column QUESTION.DESCRIPTION
     *
     * @return the value of QUESTION.DESCRIPTION
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column QUESTION.DESCRIPTION
     *
     * @param description the value for QUESTION.DESCRIPTION
     *
     * @mbg.generated Tue May 26 19:10:35 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}