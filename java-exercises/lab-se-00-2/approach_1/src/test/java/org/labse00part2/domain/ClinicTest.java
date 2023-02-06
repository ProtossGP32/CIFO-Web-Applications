package org.labse00part2.domain;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicTest {

    private String fakeName;
    private List<Person> fakeMembers;

    private Clinic fakeClinic;
    private final Faker clinicFaker = new Faker();

    @BeforeEach
    void initialize() {

        this.fakeName = clinicFaker.medical().hospitalName();
        this.fakeMembers = new ArrayList<>();
        for (int i = 0; i < clinicFaker.random().nextInt(0, 10); i++) {
            Person newMember = createMember();
            this.fakeMembers.add(newMember);
        }
        this.fakeClinic = new Clinic(this.fakeName, this.fakeMembers);
    }

    @Test
    void addMember() {
        Person newMember = createMember();
        this.fakeClinic.addMember(newMember);
        // TODO: Check that once a member is added, the lowestWeightIndex is updated and correct
        assertTrue(this.fakeClinic.hasMember(newMember));
    }

    @Test
    void hasMember() {
        for(Person member : this.fakeMembers) {
            assertTrue(this.fakeClinic.hasMember(member));
        }
    }

    @Test
    void personWithHighestWeightIndex() {
        Person fakeHighestWeightIndexPerson = null;
        for (Person member : this.fakeMembers) {
            if (fakeHighestWeightIndexPerson == null || member.weightIndex() > fakeHighestWeightIndexPerson.weightIndex()) {
                fakeHighestWeightIndexPerson = member;
            }
        }

        assertEquals(fakeHighestWeightIndexPerson, this.fakeClinic.personWithHighestWeightIndex());
    }

    @Test
    void testToString() {
        StringBuilder membersBuilder = new StringBuilder();
        for (Person member : this.fakeMembers) {
            membersBuilder.append(member).append("\n");
        }
        String expectedToString = "Clinic: " + this.fakeName + "\n"
                + "Lowest Weight Index: " + this.getLowestWeightIndex() + "\n"
                + "Members: " + membersBuilder;

        // assertEquals must be between the same type, can't compare String with Clinic
        // even though toString() is implicitly invoked
        assertEquals(expectedToString, this.fakeClinic.toString());
    }

    // Test utilities
    Person createMember() {
        Date birthDate = clinicFaker.date().birthday();
        MyDate fakeDate = new MyDate(
                birthDate.getDay(),
                birthDate.getMonth(),
                birthDate.getYear()
                );
        return new Person(
                clinicFaker.name().fullName(),
                clinicFaker.number().numberBetween(0, 99),
                clinicFaker.number().numberBetween(30, 250),
                clinicFaker.number().numberBetween(3, 300),
                fakeDate);
    }

    double getLowestWeightIndex() {
        double lowestWeightIndex = Double.MAX_VALUE;
        for (Person member : this.fakeMembers) {
            if (member.weightIndex() < lowestWeightIndex) {
                 lowestWeightIndex = member.weightIndex();
            }
        }
        return lowestWeightIndex;
    }
}