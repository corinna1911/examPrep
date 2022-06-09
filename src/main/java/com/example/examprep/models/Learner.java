package com.example.examprep.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.Duration;
import java.util.Date;

@Entity
@Getter
@Setter
@DiscriminatorValue("L")
@NoArgsConstructor
@AllArgsConstructor
public class Learner extends User{

    Date startDate;
    Date endDate;
    Duration duration;

    public Learner(String name, String email, String password){
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.duration = Duration.ZERO;
    }

}
