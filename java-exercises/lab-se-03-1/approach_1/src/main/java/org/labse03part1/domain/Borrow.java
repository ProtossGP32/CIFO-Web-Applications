package org.labse03part1.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.labse03part1.utils.InterfaceUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Borrow {
    private enum borrowStatus {
        IN_PROGRESS("In progress") {
            @Override
            boolean update(Borrow borrowToUpdate) {
                // Update the due date 15 days from today
                borrowToUpdate.setDueBorrowDate(LocalDate.now().plusDays(15));
                return true;
            }
        },
        LATE("Late") {
            @Override
            boolean update(Borrow borrowToUpdate) {
                return true;
            }
        },
        CLOSED("Closed") {
            @Override
            boolean update(Borrow borrowToUpdate) {
                borrowToUpdate.setReturnDate(LocalDate.now());
                return true;
            }
        };

        private final String description;

        abstract boolean update(Borrow borrowToUpdate);

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
    private String studentID;
    private String bookID;
    private LocalDate startBorrowDate;
    private LocalDate dueBorrowDate;
    private LocalDate returnDate;
    private String borrowID;
    private borrowStatus status;

    public Borrow(String studentID, String bookID) {
        this.studentID = studentID;
        this.bookID = bookID;
        // TODO: Automatically created attributes
        this.startBorrowDate = LocalDate.now();
        // dueBorrowDate is 15 days after startBorrowDate
        this.dueBorrowDate = this.startBorrowDate.plusDays(15);
        // returnDate is empty?
        this.status = borrowStatus.IN_PROGRESS;
        this.borrowID = InterfaceUtils.createUUID();
    }

    // Additional public setters and getters
    public String getStatusDescription() {
        // return the status description
        return this.getStatus().getDescription();
    }

    public void setStatus(String newStatus) {
        // get the enum value matching the newStatus description
        borrowStatus newEnum = borrowStatus.getEnum(newStatus);
        if (newEnum != null) {
            // Execute the action related to that newStatus
            if (newEnum.update(this)) {
                this.status = borrowStatus.getEnum(newStatus);
            } else {
                System.out.println("[Borrow] Something wrong when trying to update status from " + this.status + " to " + newEnum);
            }
        } else {
            System.out.println("[Borrow] Status " + newStatus + " does not exist!");
        }
    }

    public void setDueBorrowDate(LocalDate newDueBorrowDate) {

        this.dueBorrowDate = newDueBorrowDate;
        this.setStatus("In progress");
    }

    public void setDueBorrowDate(String newDueDate) {
        // New due date input shall have the following format: "yyyy/MM/dd", then converted to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.setDueBorrowDate(LocalDate.parse(newDueDate, formatter));
    }
}
