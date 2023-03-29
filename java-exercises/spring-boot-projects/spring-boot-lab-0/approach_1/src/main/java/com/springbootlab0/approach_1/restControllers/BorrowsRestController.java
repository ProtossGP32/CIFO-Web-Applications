package com.springbootlab0.approach_1.restControllers;

import com.springbootlab0.approach_1.domain.Borrow;
import com.springbootlab0.approach_1.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/borrows")
public class BorrowsRestController {
    @Autowired
    BorrowService borrowService;

    @GetMapping("all")
    public Iterable<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @GetMapping("create")
    public Iterable<Borrow> createBorrow(@RequestBody String createBorrowData) {
        JsonParser bodyParser = JsonParserFactory.getJsonParser();
        Map<String, Object> bodyMap = bodyParser.parseMap(createBorrowData);
        try {
            return borrowService.createMultipleBorrows(bodyMap.get("userId").toString(), (Iterable<String>) bodyMap.get("publicationIds"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "borrowId not found in requestBody", e);
        }
    }

    @GetMapping("return")
    public Borrow returnBorrow(@RequestBody String returnBorrowData) {
        JsonParser bodyParser = JsonParserFactory.getJsonParser();
        Map<String, Object> bodyMap = bodyParser.parseMap(returnBorrowData);
        try {
            return borrowService.returnBorrowByID(bodyMap.get("borrowId").toString());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "borrowId not found in requestBody", e);
        }
    }
}
