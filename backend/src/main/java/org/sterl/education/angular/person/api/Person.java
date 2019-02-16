package org.sterl.education.angular.person.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data 
@EqualsAndHashCode(of = "id") 
public class Person {
	private Long id;
	private String name;
}
