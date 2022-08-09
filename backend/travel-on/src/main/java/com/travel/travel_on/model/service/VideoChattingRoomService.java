package com.travel.travel_on.model.service;

import com.travel.travel_on.entity.*;

import java.util.List;

public interface VideoChattingRoomService {

    boolean checkRoomNumber(String roomCode);
    boolean insert(VideoChattingRoom videoChattingRoom);
    VideoChattingRoom match(User user, String areaCode);
    boolean enter(User user, String roomCode);
    boolean leave(User user, String roomCode);
    boolean close(User user, String roomCode);
    boolean report(Report report);
    List<String> selectTopic();
    List<String> selectKeyword(String topic);

}
