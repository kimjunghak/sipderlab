package com.sipderlab

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
class SipderlabApplication

fun main(args: Array<String>) {
    runApplication<SipderlabApplication>(*args)
}
