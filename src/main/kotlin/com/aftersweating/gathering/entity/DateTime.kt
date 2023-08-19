package com.after.sweating.gathering.entity

import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Embeddable

@Embeddable
class DateTime(
    date: LocalDate,
    time: LocalTime
) {
    constructor() : this(LocalDate.now(),LocalTime.now())
}