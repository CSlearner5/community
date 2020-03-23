package com.example.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {

    private List<QuestionDTO> questions;
    private Boolean showPrevious;
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        totalPage = totalCount / size;
        if(totalCount % size != 0) {
            totalPage++;
        }

        if(page < 1) {
            page = 1;
        }
        if(page > totalPage) {
            page = totalPage;
        }
        this.page = page;

        pages.add(page);
        for(int index = 1; index <= 3; index++) {
            if(page - index > 0) {
                pages.add(0, page - index);
            }
            if(page + index <= totalPage) {
                pages.add(page + index);
            }
        }

        showPrevious = true;
        if(page == 1) {
            showPrevious = false;
        }

        showNext = true;
        if(page == totalPage) {
            showNext = false;
        }

        showFirstPage = true;
        if(pages.contains(1)) {
            showFirstPage = false;
        }

        showEndPage = true;
        if(pages.contains(totalPage)) {
            showEndPage = false;
        }
    }
}
