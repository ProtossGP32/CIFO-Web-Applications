package org.labse00part2.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private int age;
    private int height;
    private int weight;
    private MyDate birthDate;

    public void printPerson() {
        System.out.println(this.name + ", " + this.age + " years old");
    }

    public void becomeOlder() {
        this.age++;
    }

    public void becomeOlder(int years) {
        this.age += years;
    }

    public boolean isAdult() {
        return this.age >= 18;
    }

    public double weightIndex() {
        double heightInMeters = this.height / 100.0;
        return this.weight / (heightInMeters * heightInMeters);
    }

    public boolean olderThan(Person compared) {
        return this.age > compared.getAge();
    }

    private void setAge() {
        // TODO: Use the myDate attribute to set the current age
        // Some methods like becomeOlder should be removed
    }

    @Override
    public String toString() {
        return this.getName() + ", born in " + this.getBirthDate() + ", " + this.getAge() + " years old, weight index: " + this.weightIndex() + ".";
    }
}
