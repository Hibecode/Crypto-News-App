package com.example.crypto_news_app.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit


// Created by Okunoye Ibukunoluwa (2022)
// https://github.com/Hibecode

@RequiresApi(Build.VERSION_CODES.O)
fun parseDateTime(dateTime: String): String {

    return getDateTimePeriod(dateTime)

}

/**
 * Returns instant zoned date and time
 *
 * @return ZonedDateTime
 */
@RequiresApi(Build.VERSION_CODES.O)
fun getInstantTime(): ZonedDateTime {
    val zoneId: ZoneId = ZoneId.of("Africa/Lagos") // Or "Asia/Kolkata", "Europe/Paris", and so on.
    val instant: Instant = Instant.now() // Current moment in UTC.
    return ZonedDateTime.ofInstant(instant, zoneId)
}


@RequiresApi(Build.VERSION_CODES.O)
fun getDateTimePeriod(startDate: String): String {
    val dtf = DateTimeFormatter.ISO_DATE_TIME
    val sdt = ZonedDateTime.parse(startDate, dtf)

    val instant = getInstantTime()
    val noOfMinutes = ChronoUnit.MINUTES.between(sdt, instant)
    val noOfHours = ChronoUnit.HOURS.between(sdt, instant)
    val noOfDays = ChronoUnit.DAYS.between(sdt, instant)
    val noOfWeeks = ChronoUnit.WEEKS.between(sdt, instant)
    val noOfMonths = ChronoUnit.MONTHS.between(sdt, instant)
    val noOfYears = ChronoUnit.YEARS.between(sdt, instant)


    if(noOfYears < 1) {
        if (noOfMonths in 1..11) {
            return "$noOfMonths month${isOneOrNot(noOfMonths)}"
        } else {
            if (noOfWeeks in 1..3) {
                return "$noOfWeeks week${isOneOrNot(noOfWeeks)}"
            } else {
                if (noOfDays in 1..30) {
                    return "$noOfDays day${isOneOrNot(noOfDays)}"
                } else {
                    if (noOfHours in 1..23) {
                        return "$noOfHours hour${isOneOrNot(noOfHours)}"
                    } else {
                        if (noOfMinutes in 1..59) {
                            return "$noOfMinutes minute${isOneOrNot(noOfMinutes)}"
                        }
                    }
                }
            }
        }
    } else {
        return "$noOfYears years"
    }

    return startDate

}

// This checks whether a number is One or not
// in order to determine whether the date/time will
// be pluralized or not. It also adds "ago" at the end.
fun isOneOrNot(number: Long): String {
    val no = number.toInt()
    if (no != 1)
        return "s ago"
    return " ago"
}
