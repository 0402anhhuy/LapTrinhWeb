package com.ltw.bt01_springsecurity.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
}
