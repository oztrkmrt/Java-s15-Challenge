package com.workintech.library.records;

import com.workintech.library.person.Reader;

import java.time.LocalDate;

public class StudentRecord extends MemberRecord{

    public StudentRecord(int borrowedBooksNumber, LocalDate dateOfMembership, int member_id, String memberAddress, String memberName, String phoneNumber, MemberType type) {
        super(borrowedBooksNumber, dateOfMembership, member_id, memberAddress, memberName, phoneNumber, type);
    }
}
