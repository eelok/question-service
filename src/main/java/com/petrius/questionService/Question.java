package com.petrius.questionService;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Question {

    @Id
    @GeneratedValue
    private Long id;

    private String questionText;

    @OneToMany(mappedBy = "question")
    @JsonManagedReference
    private List<Answer> answers;



}
