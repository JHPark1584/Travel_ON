package com.travel.travel_on.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table (name="notice")
@Entity
public class FAQ {

    @Id
    @Column(name="faq_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer faqId;

    @Column(length = 70, nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;
}
