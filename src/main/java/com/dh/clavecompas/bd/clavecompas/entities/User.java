package com.dh.clavecompas.bd.clavecompas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.io.Serializable;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", schema = "security")
public class User implements Serializable {
    @Id
    @Column(name = "user_id")
    int userId;

    @Column(name = "c_user")
    String userCode;
}
