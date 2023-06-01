package org.sterl.education.angular.person.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
