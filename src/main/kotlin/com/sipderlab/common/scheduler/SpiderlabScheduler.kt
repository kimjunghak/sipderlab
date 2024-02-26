package com.sipderlab.common.scheduler

import com.sipderlab.book.service.data.BookDataWriteService
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import org.springframework.scheduling.support.PeriodicTrigger
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@EnableScheduling
class SpiderlabScheduler(
    private val bookDataWriteService: BookDataWriteService,
) : SchedulingConfigurer {

    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {

        taskRegistrar.addTriggerTask(
            { bookDataWriteService.returnBooks() },
            PeriodicTrigger(Duration.ofSeconds(1L))
        )
    }
}