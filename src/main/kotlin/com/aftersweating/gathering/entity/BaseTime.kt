package com.after.sweating.gathering.entity

import jakarta.persistence.Embeddable
import java.time.LocalDate
import java.time.LocalTime

@Embeddable
class BaseTime(
    date: LocalDate,
    time: LocalTime
) {
    constructor() : this(LocalDate.now(),LocalTime.now())
}