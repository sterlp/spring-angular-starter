package org.sterl.education.angular.person.behavior;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.sterl.education.angular.person.dao.PersonDao;
import org.sterl.education.angular.person.model.Person;

@Component
class PersonDataGeneratorBA {

    @Autowired PersonDao personDao;
    
    @PostConstruct
    @Transactional
    void execute() {
        long count = personDao.count();
        if (count == 0) {
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                personDao.save(Person.builder()
                        .name("Person " + (i + 1))
                        .address("Random Address " + r.nextInt( (i + 1) * 10))
                        .build());
            }
        }
    }
}
