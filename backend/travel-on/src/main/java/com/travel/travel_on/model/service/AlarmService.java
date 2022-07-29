package com.travel.travel_on.model.service;

import com.travel.travel_on.dto.Alarm;
import com.travel.travel_on.dto.User;

import java.util.List;

public interface AlarmService {

    List<Alarm> selectAll(int userId);

//    int insert(User user, String content);
    int insert(int userId, String content);

    int deleteAll(int userId);
}