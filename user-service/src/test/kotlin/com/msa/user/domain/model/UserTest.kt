package com.msa.user.domain.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTest {

    @Test
    @DisplayName("이름과 이메일이 정상이면 id가 자동 부여된 User객체가 생성된다")
    fun createUser_success() {
        val user = User.create(name = "유장훈", email = "test@example.com")

        assertNotNull(user.id)
        assertEquals("유장훈", user.name)
        assertEquals("test@example.com", user.email)
    } 

    @Test
    @DisplayName("이름이 공백이면 생성 거부됨")
    fun createUser_blankName_throwsException() {
        assertThrows(IllegalArgumentException::class.java) {
            User.create(name = "    ", email = "test@example.com")
        }
    }

    @Test
    @DisplayName("이메일이 공백이면 생성 거부됨")
    fun createUser_blankEmail_throwsException() {
        assertThrows(IllegalArgumentException::class.java) {
            User.create(name = "유장훈", email = "      ")
        } 
    }
}
