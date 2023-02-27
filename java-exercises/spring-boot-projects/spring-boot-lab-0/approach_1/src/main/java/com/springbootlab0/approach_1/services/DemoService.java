package com.springbootlab0.approach_1.services;

import com.springbootlab0.approach_1.domain.LibraryMember;
import com.springbootlab0.approach_1.domain.Publication;
import com.springbootlab0.approach_1.repository.AuthorRepository;
import com.springbootlab0.approach_1.repository.LibraryMemberRepository;
import com.springbootlab0.approach_1.repository.PublicationRepository;
import com.springbootlab0.approach_1.utils.FakeDataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Autowired
    PublicationRepository publicationRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    LibraryMemberRepository libraryMemberRepository;

    // ONLY FOR DEMO PURPOSES
    public void createFakePublications(int numPublications) {
        for(Publication fakePublication : FakeDataGenerator.createFakePublications(numPublications)) {
            // First add the author into the database
            authorRepository.save(fakePublication.getAuthor());
            // Then add the publication as it depends on the Author being available in the DataBase
            publicationRepository.save(fakePublication);
        }
    }

    public void createFakeLibraryMembers(int numMembers) {
        for (LibraryMember fakeMember : FakeDataGenerator.createFakeLibraryMembers(numMembers)) {
            // Add each member to the LibraryMember Repository
            libraryMemberRepository.save(fakeMember);
        }
    }
}
