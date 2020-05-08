package org.sterl.education.angular.person.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data 
@EqualsAndHashCode(of = "id") // this will cause problems with SETs, if the value is null
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull @Size(min = 5, max = 50)
    private String name;
    @Size(max = 255)
    private String address;
    @Version
    private Long version;
}
