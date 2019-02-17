package org.sterl.education.angular.person.api;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persons")
public class PersonBF {
	
	private final static AtomicLong ID_GENERATOR = new AtomicLong(0);
	private final static Map<Long, Person> DB = Collections.synchronizedMap(new LinkedHashMap<>());
	
	@PostConstruct
	void init() {
		DB.clear();
		for (int i = 0; i < 5; i++) {
			final Long id = ID_GENERATOR.incrementAndGet();
			DB.put(
				id,
				Person.builder()
					.id(id)
					.name("Person " + id)
					.build()
			);
		}
	}

    @RequestMapping
    public Collection<Person> list() {
    	sleep(500);
        return DB.values();
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Person get(@PathVariable("id") Long id) {
    	sleep(500);
        return DB.get(id);
    }
    @RequestMapping(method = RequestMethod.POST)
    public Person add(@RequestBody Person p) throws InterruptedException {
    	p.setId(ID_GENERATOR.incrementAndGet());
    	DB.put(p.getId(), p);
        return p;
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public Person add(@PathVariable("id") Long id, @RequestBody Person p) {
    	p.setId(id);
    	DB.put(p.getId(), p);
        return p;
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	sleep(700);
    	DB.remove(id);
    }
    /**
     * adding some delay for demonstration purpose of the loading indicator only
     */
    private static void sleep(long time) {
    	try {
			Thread.sleep(time);
		} catch (InterruptedException ignored) {}
    }
}