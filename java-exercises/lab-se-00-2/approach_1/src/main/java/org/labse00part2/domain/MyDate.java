package org.labse00part2.domain;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyDate {
    private int day;
    private int month;
    private int year;

    @Override
    public String toString() {
        return this.getYear() + "/" + this.getMonth() + "/" + this.getDay();
    }

    public boolean earlierThan(MyDate compared) {
        // First, compare years
        if (this.getYear() < compared.getYear()) {
            return true;
        }
        // Then, compare months
        if (this.getYear() == compared.getYear() && this.getMonth() < compared.getMonth()) {
            return true;
        }

        // Then, compare day
        if (this.getYear() == compared.getYear()
                && this.getMonth() == compared.getMonth()
                && this.getDay() < compared.getDay()){
            return true;
        }

        return false;
    }

}
