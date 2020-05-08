package org.sterl.education.angular.person.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.sterl.education.angular.person.dao.PersonDao;
import org.sterl.education.angular.person.model.Person;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/persons")
public class PersonBF {
    @Value("${example.enable-sleep:false}")
    boolean sleepEnabled;
    @Autowired PersonDao personDao;

    @RequestMapping
    public ResponseEntity<Page<Person>> list(Pageable page) {
        sleep(300);
        return ResponseEntity.ok(personDao.findAll(page));
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> get(@PathVariable("id") Long id) {
        sleep(250);
        return ResponseEntity.of(personDao.findById(id));
    }
    @RequestMapping(method = RequestMethod.POST)
    public Person add(@Validated @RequestBody Person p) throws InterruptedException {
        return personDao.saveAndFlush(p);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Person add(@PathVariable("id") Long id, @RequestBody Person p) {
        p.setId(id);
        return personDao.saveAndFlush(p);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
        sleep(700);
        personDao.deleteById(id);
    }
    /**
     * adding some delay for demonstration purpose of the loading indicator only
     */
    private void sleep(long time) {
        if (sleepEnabled) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException ignored) {}
        }
    }
}