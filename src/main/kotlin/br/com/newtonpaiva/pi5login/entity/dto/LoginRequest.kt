package br.com.newtonpaiva.pi5login.entity.dto

data class LoginRequest(
    var username: String = "",
    var password: String = ""
)