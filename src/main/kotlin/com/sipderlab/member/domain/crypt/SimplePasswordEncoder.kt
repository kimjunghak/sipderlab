package com.sipderlab.member.domain.crypt

import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@Component
class SimplePasswordEncoder {

    fun encodePassword(password: String): String? {
        try {
            // SHA-256 해싱 알고리즘 사용
            val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
            val hash: ByteArray = digest.digest(password.toByteArray())


            // 바이트 배열을 16진수 문자열로 변환
            val hexString = StringBuilder()
            for (b in hash) {
                val hex = Integer.toHexString(0xff and b.toInt())
                if (hex.length == 1) hexString.append('0')
                hexString.append(hex)
            }
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return null
        }
    }

    fun matches(rawPassword: String, encodedPassword: String): Boolean {
        val hashedPassword = encodePassword(rawPassword)
        return encodedPassword == hashedPassword
    }
}