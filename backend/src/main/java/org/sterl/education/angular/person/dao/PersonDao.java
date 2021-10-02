package org.sterl.education.angular.person.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.sterl.education.angular.person.model.Person;

public interface PersonDao extends JpaRepository<Person, Long> {

}
