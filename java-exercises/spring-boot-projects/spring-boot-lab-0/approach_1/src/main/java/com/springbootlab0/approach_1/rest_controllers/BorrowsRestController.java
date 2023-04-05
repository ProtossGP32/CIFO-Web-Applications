package com.springbootlab0.approach_1.rest_controllers;

import com.springbootlab0.approach_1.domain.Borrow;
import com.springbootlab0.approach_1.services.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/borrows")
public class BorrowsRestController {
    @Autowired
    BorrowService borrowService;

    @GetMapping("all")
    public Iterable<Borrow> getAllBorrows() {
        return borrowService.getAllBorrows();
    }

    @PostMapping("create")
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Borrow> deleteBorrow(@PathVariable("id") String borrowId) {
        // Initialize the headers
        // TODO: Parametrize the headers attributes
        HttpHeaders headers = new HttpHeaders();
        headers.add("operation", "deleteBorrow");
        headers.add("version", "api 1.0");
        // Call the service to delete the Borrow from the DB if it exists
        Borrow deletedBorrow = borrowService.deleteAndReturnBorrowById(borrowId);
        if (deletedBorrow != null) {
            // Add the operation status to the headers
            headers.add("operationStatus", "deleted");
            return ResponseEntity.ok().headers(headers).body(deletedBorrow);
        }
        // Return a null response
        headers.add("operationStatus", "borrow not found");
        return ResponseEntity.badRequest().headers(headers).body(null);
    }
}
