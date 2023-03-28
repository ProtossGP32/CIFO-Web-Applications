package com.springbootlab0.approach_1.restControllers;

import com.springbootlab0.approach_1.domain.Borrow;
import com.springbootlab0.approach_1.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/borrows")
public class borrowsRestController {
    @Autowired
    BorrowService borrowService;

    @GetMapping("all")
    public Iterable<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @PostMapping("create")
    public Iterable<Borrow> createBorrow(@RequestParam("userId") String userId, @RequestParam("publicationIds") List<String> publicationIds) {
        return borrowService.createMultipleBorrows(userId, publicationIds);
    }

    @GetMapping("return")
    public Borrow returnBorrow(@RequestParam("borrowId") String borrowId) {
        return borrowService.returnBorrowByID(borrowId);
    }
}
