package org.labse03part2.domain;

public interface StaffOperations {
    void addPublication(Publication item);
    void removePublication(Publication item);
    void updatePublication(Publication item);
    void registerMember(LibraryMember user);
    void removeMember(LibraryMember user);
    void updateMember(LibraryMember user);
    void generateReports();
}
