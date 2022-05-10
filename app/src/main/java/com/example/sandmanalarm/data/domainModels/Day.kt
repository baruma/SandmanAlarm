package com.example.sandmanalarm.data.domainModels

import androidx.room.TypeConverter

enum class Day(val dayOfWeek: Int) {
    MONDAY(0),
    TUESDAY(1),
    WEDNESDAY(2),
    THURSDAY(3),
    FRIDAY(4),
    SATURDAY(5),
    SUNDAY(6),
    ALL(7);

//    companion object {
//        fun map(dayOfWeek: Int): Day {
//            return values()[dayOfWeek]
//        }
//
//        // Hardcoded for now.
//        // TODO: Must amend later.
//        @TypeConverter
//        fun mapFromDay(dayOfWeek: Int): Day {
//            return MONDAY
//        }
//
//        @TypeConverter
//        fun mapToDay(day: Day): Int {
//            return 0
//        }
//    }
}