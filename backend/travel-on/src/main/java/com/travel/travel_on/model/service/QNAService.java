package com.travel.travel_on.model.service;

import com.travel.travel_on.dto.QNADto;
import com.travel.travel_on.dto.UserDto;
import com.travel.travel_on.entity.QNA;
import com.travel.travel_on.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QNAService {

    List<QNA> selectAll(User user, String keyword); // 글 리스트 전체 조회

    QNADto selectOne(Integer id); // 글 조회

    boolean write(UserDto userDto, QNADto qnaDto); // 글 등록

    boolean update(QNADto qnaDto); // 글 수정

    boolean delete(Integer id); // 글 삭제

    List<QNA> adminSelectAll(String keyword); // 매니저 글 리스트 전체 조회

    List<QNA> noneAnswerAll();
}
