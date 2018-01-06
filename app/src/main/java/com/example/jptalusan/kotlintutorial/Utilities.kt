package com.example.jptalusan.kotlintutorial

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager



/**
 * Created by jptalusan on 1/6/18.
 */
fun isNetworkConnected(context: Context): Boolean {
    val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null
}

/**
 * Compares two version strings.
 *
 * Use this instead of String.compareTo() for a non-lexicographical
 * comparison that works for version strings. e.g. "1.10".compareTo("1.6").
 *
 * @note It does not work if "1.10" is supposed to be equal to "1.10.0".
 *
 * @param str1 a string of ordinal numbers separated by decimal points.
 * @param str2 a string of ordinal numbers separated by decimal points.
 * @return The result is a negative integer if str1 is _numerically_ less than str2.
 *         The result is a positive integer if str1 is _numerically_ greater than str2.
 *         The result is zero if the strings are _numerically_ equal.
 */
fun versionCompare(str1: String, str2: String): Int {
    val vals1 = str1.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    val vals2 = str2.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    var i = 0
    // set index to first non-equal ordinal or length of shortest version string
    while (i < vals1.size && i < vals2.size && vals1[i] == vals2[i]) {
        i++
    }
    // compare first non-equal ordinal number
    if (i < vals1.size && i < vals2.size) {
        val diff = Integer.valueOf(vals1[i])!!.compareTo(Integer.valueOf(vals2[i]))
        return Integer.signum(diff)
    }
    // the strings are equal or one string is a substring of the other
    // e.g. "1.2.3" = "1.2.3" or "1.2.3" < "1.2.3.4"
    return Integer.signum(vals1.size - vals2.size)
}