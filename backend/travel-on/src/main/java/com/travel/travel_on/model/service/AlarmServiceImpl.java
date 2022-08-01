package com.travel.travel_on.model.service;

import com.travel.travel_on.entity.Alarm;
import com.travel.travel_on.entity.User;
import com.travel.travel_on.model.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    AlarmRepository repo;

    @Autowired
    UserService usvc;

    @Override
    public List<Alarm> selectAll(User user) {
        List<Alarm> list = repo.findByUser(user);
//        for(Alarm alarm:list){
//            System.out.println(alarm);
//        }
        return list;
    }

//    @Override
//    public int insert(User user,String content) {
//        Alarm alarm = Alarm.builder()
//                .user(user)
//                .content(content)
//                .build();
//        repo.save(alarm);
//        usvc.updateAlarm(user.getUserId());
//        return 0;
//    }

    @Override
    public int insert(User user,String content) {
        Alarm alarm = Alarm.builder()
                .user(user)
                .content(content)
                .build();
        alarm.setUser(user);
        repo.save(alarm);
        usvc.updateAlarm(user.getUserId());
        return 0;
    }


    @Override
    public int deleteAll(User user) {
//        Optional<List<Alarm>> result = repo.findAllByUser(user);
        List<Alarm> list = repo.findByUser(user);
//        if (result.isPresent()) {
            for(Alarm alarm : list) {
                repo.delete(alarm);
            }
            return 0;
//        }
//        return 1;
    }
}