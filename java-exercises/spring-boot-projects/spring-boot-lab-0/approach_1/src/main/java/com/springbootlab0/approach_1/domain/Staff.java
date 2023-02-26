package com.springbootlab0.approach_1.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "Staff")
@DiscriminatorValue(value = "STAFF")
public class Staff extends StaffMember{

}
