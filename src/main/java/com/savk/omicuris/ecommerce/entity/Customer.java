package com.savk.omicuris.ecommerce.entity;

import com.savk.omicuris.ecommerce.dto.CustomerDto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;
    private String contactinfo;

    @Column(unique = true, nullable = false)
    private String ssn;

    public Customer() {}

    public Customer(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.name = customerDto.getName();
        this.address = customerDto.getAddress();
        this.contactinfo = customerDto.getContactinfo();
        this.ssn = customerDto.getSsn();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactinfo() {
        return contactinfo;
    }

    public void setContactinfo(String contactinfo) {
        this.contactinfo = contactinfo;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(name, customer.name) &&
                Objects.equals(address, customer.address) &&
                Objects.equals(contactinfo, customer.contactinfo) &&
                Objects.equals(ssn, customer.ssn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, contactinfo, ssn);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactinfo='" + contactinfo + '\'' +
                ", ssn=" + ssn +
                '}';
    }
}
