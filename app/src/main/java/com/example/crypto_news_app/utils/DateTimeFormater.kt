package com.example.crypto_news_app.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
fun parseDateTime(/*dateTime: String*/) {


    val current = LocalDateTime.now()
    //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    //val formatted = current.format(formatter)
    //val date = LocalDate.parse("2022-06-16 19:31:39",formatter)
    //val time = SimpleDateFormat().parse("2022-06-16 19:31:39")
    /*Log.d("parseTest", date.dayOfMonth.toString())
    Log.d("parseTest", time.toString())*/



    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.UK)
    //formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    formatter.timeZone.id = "UTC"
    val formatted = formatter.parse("2022-06-16T19:31:39Z")
    val formatted2 = formatter.parse("2022-06-16T22:31:39Z")
    Log.d("parseTest", formatted.toLocaleString())

    val dtf = DateTimeFormatter.ISO_DATE_TIME
    //val zdt = ZonedDateTime.parse("2022-06-16T22:31:39Z", dtf)

    val ldt = ZonedDateTime.parse("2022-05-21T19:31:39Z", dtf)

    val zoneId: ZoneId = ZoneId.of("Africa/Lagos") // Or "Asia/Kolkata", "Europe/Paris", and so on.
    val instant: Instant = Instant.now() // Current moment in UTC.
    val zdt = ZonedDateTime.ofInstant(instant, zoneId)

    //Log.d("parseTest", zdt.month.toString())

    val noOfHours = ChronoUnit.HOURS.between(ldt, zdt)
    val noOfDays = ChronoUnit.DAYS.between(ldt, zdt)
    val noOfMonths = ChronoUnit.MONTHS.between(ldt, zdt)

    Log.d("parseTest", noOfDays.toString())



}

