package com.springbootlab0.approach_1.bookimagemongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookImageRepository extends MongoRepository<BookImage, String> {
}
