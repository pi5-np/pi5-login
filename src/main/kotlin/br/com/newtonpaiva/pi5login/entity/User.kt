package br.com.newtonpaiva.pi5login.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.Table

@Entity
@Table(name = "users")
class User {

    @Id
    var username: String = ""

    var password: String = ""

}