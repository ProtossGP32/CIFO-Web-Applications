package org.labse00part2.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clinic {

    private String name;
    private double lowestWeightIndex;
    private List<Person> members;

    @Builder
    public Clinic(String name, double lowestWeightIndex) {
        this.name = name;
        this.lowestWeightIndex = lowestWeightIndex;
        this.members = new ArrayList<>();
    }

    @Builder
    public Clinic(String name, List<Person> members) {
        this.name = name;
        this.members = members;
        // Once initialized the members, set the lowest Weight Index
        this.setLowestWeightIndex();
    }

    public void addMember(Person member) {
        this.getMembers().add(member);
        // Remember to update the lowestWeightIndex each time a new member is added
        if (member.weightIndex() < this.getLowestWeightIndex()) {
            this.lowestWeightIndex = member.weightIndex();
        }
        this.setLowestWeightIndex();
    }

    public boolean hasMember(Person member) {
        return this.getMembers().contains(member);
    }

    public Person personWithHighestWeightIndex() {
        Person highestWeightIndexMember = null;
        for (Person member : this.getMembers()) {
            if (highestWeightIndexMember == null || member.weightIndex() > highestWeightIndexMember.weightIndex()) {
                highestWeightIndexMember = member;
            }
        }
        return highestWeightIndexMember;
    }

    public void setLowestWeightIndex() {
        for (Person member : this.getMembers()) {
            if (this.getLowestWeightIndex() == 0 || member.weightIndex() < this.getLowestWeightIndex()) {
                this.lowestWeightIndex = member.weightIndex();
            }
        }
    }

    public String toString() {
        StringBuilder membersBuilder = new StringBuilder("");
        for (Person member : this.getMembers()) {
            membersBuilder.append(member + "\n");
        }
        return "Clinic: " + this.getName() + "\n"
                + "Lowest Weight Index: " + this.getLowestWeightIndex() + "\n"
                + "Members: " + membersBuilder;
    }

}
