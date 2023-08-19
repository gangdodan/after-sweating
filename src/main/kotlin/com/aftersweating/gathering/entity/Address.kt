package com.after.sweating.gathering.entity

import lombok.NoArgsConstructor
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.Embeddable

@Embeddable
class Address(
    val xPoint: String,
    val yPoint: String,
    val roadAddress: String
) {

}