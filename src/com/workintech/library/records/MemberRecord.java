package com.workintech.library.records;

import com.workintech.library.person.Reader;

import java.time.LocalDate;
import java.util.Objects;

public abstract class MemberRecord {
    private int member_id;
    private MemberType type;
    private LocalDate dateOfMembership;
    private int borrowedBooksNumber = 0;
    private final int maxBookLimit = 5;
    private String memberName;
    private String memberAddress;
    private String phoneNumber;

    public MemberRecord(int borrowedBooksNumber, LocalDate dateOfMembership, int member_id, String memberAddress, String memberName, String phoneNumber, MemberType type) {
        this.borrowedBooksNumber = borrowedBooksNumber;
        this.dateOfMembership = dateOfMembership;
        this.member_id = member_id;
        this.memberAddress = memberAddress;
        this.memberName = memberName;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }

    public int getBorrowedBooksNumber() {
        return borrowedBooksNumber;
    }

    public void setBorrowedBooksNumber(int borrowedBooksNumber) {
        if (borrowedBooksNumber <= maxBookLimit){
            this.borrowedBooksNumber = borrowedBooksNumber;
        } else {
            System.out.println("Maximum book limit has been reached.");
        }

    }

    @Override
    public String toString() {
        return "MemberRecord{" +
                "dateOfMembership=" + dateOfMembership +
                ", member_id=" + member_id +
                ", type=" + type +
                ", maxBookLimit=" + maxBookLimit +
                ", memberName=" + memberName +
                ", memberAddress='" + memberAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MemberRecord that = (MemberRecord) object;
        return member_id == that.member_id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(member_id);
    }
}
