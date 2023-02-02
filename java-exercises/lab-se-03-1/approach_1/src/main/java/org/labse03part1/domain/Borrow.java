package org.labse03part1.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private Date startBorrowDate;
    private Date dueBorrowDate;
    private Date returnDate;
    private String borrowId;
    private String borrowStatus;
}
