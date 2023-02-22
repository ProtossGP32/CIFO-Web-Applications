package com.springbootlab0.approach_1.domain;

public interface StaffOperations {
    void addPublication(Publication item);
    void removePublication(Publication item);
    void updatePublication(Publication item);
    void registerMember(LibraryMember user);
    void removeMember(LibraryMember user);
    void updateMember(LibraryMember user);
    void generateReports();
}
