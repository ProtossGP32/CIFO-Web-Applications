package com.springbootlab0.approach_1.bookimagemongodb;

import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/bookImages")
public class BookImageRestController {

    @Autowired
    BookImageService bookImageService;
    @Autowired
    PublicationService publicationService;

    private final String OPERATION = "operation";
    private final String VERSION = "version";
    private final String API = "api 1.0";
    private final String OPERATION_STATUS = "operationStatus";
    private final String ID_NOT_FOUND = "ID not found";


    // CRUD
    // Create --> Upload an image
    @PostMapping(value = "upload")
    public ResponseEntity<BookImage> uploadBookImage(@RequestParam String publicationId, @RequestParam String name, @RequestParam MultipartFile file) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(OPERATION, "upload");
        headers.add(VERSION, API);
        BookImage uploadedBookImage = bookImageService.uploadBookImage(name, file);
        if (uploadedBookImage != null) {
            // Retrieve the publication
            Optional<Publication> imagePublication = publicationService.findPublicationById(publicationId);
            // Assign the BookImage ID to the publication
            if (imagePublication.isPresent()) {
                imagePublication.get().addImageId(uploadedBookImage.getId());
                publicationService.updatePublication(imagePublication.get());
                headers.add(OPERATION_STATUS, "OK");
                return ResponseEntity.ok().headers(headers).body(uploadedBookImage);
            }
            headers.add(OPERATION_STATUS, "Publication not found");
            return ResponseEntity.ok().headers(headers).body(uploadedBookImage);
        }
        // Return an internal server error if the image couldn't be uploaded
        headers.add(OPERATION_STATUS, "Not uploaded");
        return ResponseEntity.internalServerError().headers(headers).body(null);
    }

    // Read --> get all images
    @GetMapping("")
    public ResponseEntity<Iterable<BookImage>> getAllBookImages() {
        // TODO: Parametrize the headers attributes
        HttpHeaders headers = new HttpHeaders();
        headers.add(OPERATION, "getAllImages");
        headers.add(VERSION, API);
        Iterable<BookImage> availableBookImages = bookImageService.getAllBookImages();
        if (availableBookImages != null) {
            headers.add(OPERATION_STATUS, "ok");
            return ResponseEntity.ok().headers(headers).body(availableBookImages);
        }
        headers.add(OPERATION_STATUS, "Cannot access book images collection");
        return ResponseEntity.internalServerError().headers(headers).build();

    }

    // Read --> get a single image
    @GetMapping("getImage")
    public ResponseEntity<byte[]> getBookImage(@RequestParam String id){
        // TODO: Parametrize the headers attributes
        HttpHeaders headers = new HttpHeaders();
        headers.add(OPERATION, "getImage");
        headers.add(VERSION, API);
        // Don't forget to set the returned content type!!
        // TODO: define generic MediaType for images
        headers.setContentType(MediaType.IMAGE_JPEG);
        // Retrieve the requested bookImage
        BookImage requestedBookImage = bookImageService.getBookImageById(id);
        if (requestedBookImage != null) {
            headers.add(OPERATION_STATUS, "found");
            return ResponseEntity.ok().headers(headers).body(requestedBookImage.getImage().getData());
        }
        headers.add(OPERATION_STATUS, ID_NOT_FOUND);
        return ResponseEntity.notFound().headers(headers).build();
    }

    // Read --> Get the data of a single image
    @GetMapping("getData")
    public ResponseEntity<String> getDataBookImage(@RequestParam String id) {
        // TODO: Parametrize the headers attributes
        HttpHeaders headers = new HttpHeaders();
        headers.add(OPERATION, "getData");
        headers.add(VERSION, API);
        BookImage requestedBookImage = bookImageService.getBookImageById(id);
        if (requestedBookImage != null) {
            headers.add(OPERATION_STATUS, "found");
            Encoder encoder = Base64.getEncoder();
            return ResponseEntity.ok().headers(headers).body(encoder.encodeToString(requestedBookImage.getImage().getData()));
        }
        headers.add(OPERATION_STATUS, ID_NOT_FOUND);
        return ResponseEntity.notFound().headers(headers).build();
    }

    // Update --> Update a Book Image information
    // TODO
    @PutMapping("update")
    public ResponseEntity<BookImage> updateBookImage(@RequestParam String id, @RequestParam(required = false) String name, @RequestParam(required = false) MultipartFile file) {
        return null;
    }

    // Delete --> Delete a Book Image
    @DeleteMapping("delete")
    public ResponseEntity<BookImage> deleteBookImage(@RequestParam String id) {
        // TODO: Parametrize the headers attributes
        HttpHeaders headers = new HttpHeaders();
        headers.add(OPERATION, "getData");
        headers.add(VERSION, API);
        BookImage deletedBookImage = bookImageService.deleteBookImageById(id);
        if (deletedBookImage != null) {
            headers.add(OPERATION_STATUS, "deleted");
            return ResponseEntity.ok().headers(headers).body(deletedBookImage);
        }
        headers.add(OPERATION_STATUS, ID_NOT_FOUND);
        return ResponseEntity.notFound().headers(headers).build();
    }

}
