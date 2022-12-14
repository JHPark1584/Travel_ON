package com.travel.travel_on.model.service;

import com.travel.travel_on.dto.QNADto;
import com.travel.travel_on.dto.UserDto;
import com.travel.travel_on.entity.QNA;
import com.travel.travel_on.entity.User;
import com.travel.travel_on.model.repo.QNARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class QNAServiceImpl implements QNAService{

    @Autowired
    QNARepository qnaRepository;

    @Override
    public List<QNA> selectAll(User user, String keyword) {
        List<QNA> list = qnaRepository.findByUser(user);
        if(keyword.equals("null")){
            return list;
        }else{
            List<QNA> klist = new LinkedList<>();
            for (QNA qna:list) {
                if(qna.getTitle().contains(keyword)){
                    klist.add(qna);
                }
            }
            return klist;
        }
    }

    @Override
    public QNADto selectOne(Integer id) {
        Optional<QNA> result = qnaRepository.findById(id);
        if(result.isPresent()){
            QNA qna = result.get();
            QNADto qnaDto = new QNADto(qna);
            return qnaDto;
        }
        return null;
    }

    @Override
    public boolean write(UserDto userDto, QNADto qnaDto) {
        User user = userDto.toEntity();
        if(qnaDto == null){
            return false;
        }
        QNA qna = QNA.builder()
                .user(user)
                .realId(qnaDto.getRealId())
                .nickname(qnaDto.getNickname())
                .title(qnaDto.getTitle())
                .content(qnaDto.getContent())
                .qnaDate(qnaDto.getQnaDate())
                .answerFlag(qnaDto.isAnswerFlag())
                .answer(qnaDto.getAnswer())
                .answerDate(qnaDto.getAnswerDate())
                .build();
        qna.setUser(user);
        qnaRepository.save(qna);
        return true;
    }

    @Override
    public boolean update(QNADto qnaDto) {
        Optional<QNA> result = qnaRepository.findById(qnaDto.getQNAId());

        if (result.isPresent()){
            QNA qna = result.get();
            qna.setQnaId(qnaDto.getQNAId());
            qna.setAnswer(qnaDto.getAnswer());
            qna.setQnaDate(qnaDto.getQnaDate());
            qna.setAnswerDate(qnaDto.getAnswerDate());
            qna.setContent(qnaDto.getContent());
            qna.setRealId(qnaDto.getRealId());
            qna.setNickname(qnaDto.getNickname());
            qna.setAnswerFlag(qnaDto.isAnswerFlag());
            qnaRepository.save(qna);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        Optional<QNA> result = qnaRepository.findById(id);
        if(result == null){
            return false;
        }else{
            result.ifPresent(qna -> {
                qnaRepository.delete(qna);
            });
            return true;
        }
    }

    @Override
    public List<QNA> adminSelectAll(String keyword) {
        List<QNA> list = qnaRepository.findAll();

        if(keyword.equals("null")){
            return list;
        }else{
            List<QNA> klist = new LinkedList<>();
            for (QNA qna:list) {
                if(qna.getTitle().contains(keyword)){
                    klist.add(qna);
                }
            }
            return klist;
        }
    }

    @Override
    public List<QNA> noneAnswerAll() {
        List<QNA> list = qnaRepository.findAll();

        List<QNA> alist = new LinkedList<>();
        for (QNA qna:list){
            if(qna.isAnswerFlag() == false){
                alist.add(qna);
            }
        }

        return alist;
    }

    @Override
    public List<QNA> AnswerAll(User user) {
        List<QNA> list = qnaRepository.findByUser(user);

        List<QNA> alist = new LinkedList<>();
        for (QNA qna:list){
            if(qna.isAnswerFlag() == true){
                alist.add(qna);
            }
        }

        return alist;
    }

}
