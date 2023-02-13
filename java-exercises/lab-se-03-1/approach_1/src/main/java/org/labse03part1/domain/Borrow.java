package org.labse03part1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.labse03part1.utils.InterfaceUtils;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private enum borrowStatus {
        IN_PROGRESS("In progress"),
        LATE("Late"),
        CLOSED("Closed");

        private final String description;

        borrowStatus(String description) {
            this.description = description;
        }

        private String getDescription() {
            return this.description;
        }

        public static borrowStatus getEnum(String description) {
            for (borrowStatus status : values()) {
                if (status.getDescription().equals(description)) {
                    return status;
                }
            }
            return null;
        }
    }
    private String studentName;
    private String bookName;
    private Date startBorrowDate;
    private Date dueBorrowDate;
    private Date returnDate;
    private String borrowId;
    private borrowStatus status;

    public Borrow(String studentName, String bookName) {
        this.studentName = studentName;
        this.bookName = bookName;
        // TODO: Automatically created attributes
        this.startBorrowDate = new Date();
        // dueBorrowDate is 15 days after startBorrowDate
        // returnDate is empty?
        this.status = borrowStatus.IN_PROGRESS;
        this.borrowId = InterfaceUtils.createUUID();
    }

    // Additional public setters and getters
    public void setStatus(String newStatus) {
        // get the enum value matching the newStatus description
        borrowStatus newEnum = borrowStatus.getEnum(newStatus);
        if (newEnum != null) {
            this.status = borrowStatus.getEnum(newStatus);
        } else {
            System.out.println("[Borrow] Status " + newStatus + " does not exist!");
        }
    }
}
