package com.sipderlab.common.config

import com.sipderlab.book.domain.entity.Book
import com.sipderlab.book.service.data.BookDataWriteService
import com.sipderlab.member.domain.entity.Member
import com.sipderlab.member.service.data.MemberDataWriteService
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("dev")
class DataSetup {


    @Bean
    fun setup(memberDataWriteService: MemberDataWriteService, bookDataWriteService: BookDataWriteService) =
        CommandLineRunner {

            val member1 = Member(1, "홍길동", "hong@woodo.kr", "010-0001-0002", "password12")
            val member2 = Member(2, "홍길동2", "hong2@woodo.kr", "010-0001-0003", "password12")
            memberDataWriteService.saveAllMember(
                listOf(
                    member1,
                    member2
                )
            )

            val member1Books = mutableListOf(
                Book(1L, "9791168473690", "세이노의 가르침", 1500),
                Book(2L, "9791168473691", "세이노의 가르침 2", 1500),
                Book(3L, "9791168473692", "세이노의 가르침 3", 1500),
                Book(4L, "9791168473693", "세이노의 가르침 4", 1500),
                Book(5L, "9791168473694", "세이노의 가르침 5", 1500),
                Book(6L, "9791168473695", "세이노의 가르침 6", 1500),
                Book(7L, "9791168473696", "세이노의 가르침 7", 1500),
                Book(8L, "9791168473697", "세이노의 가르침 8", 1500),
                Book(9L, "9791168473698", "세이노의 가르침 9", 1500),
                Book(10L, "9791168473699", "세이노의 가르침 10", 1500),
            )

            val member2Books = mutableListOf(
                Book(11L, "9791168473700", "세이노 1", 2500),
                Book(12L, "9791168473701", "세이노 2", 2500),
                Book(13L, "9791168473702", "세이노 3", 2500),
                Book(14L, "9791168473703", "세이노 4", 2500),
                Book(15L, "9791168473704", "사과", 1000),
            )

            member1Books.forEach { it.member = member1 }
            member2Books.forEach { it.member = member2 }

            bookDataWriteService.saveAllBooks(
                member1Books + member2Books
            )
        }
}