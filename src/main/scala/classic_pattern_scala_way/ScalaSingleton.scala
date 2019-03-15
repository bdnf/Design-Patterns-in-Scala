package classic_pattern_scala_way

import java.util.Calendar
import java.text.SimpleDateFormat

//scala supports singleton pattern with the @object keyword

//example from https://alvinalexander.com/scala/how-to-implement-singleton-pattern-in-scala-with-object
object DateUtils {

  // as "Thursday, November 29"
  def getCurrentDate: String = getCurrentDateTime("EEEE, MMMM d")

  // as "6:20 p.m."
  def getCurrentTime: String = getCurrentDateTime("K:m aa")

  // a common function used by other date/time functions
  private def getCurrentDateTime(dateTimeFormat: String): String = {
    val dateFormat = new SimpleDateFormat(dateTimeFormat)
    val cal = Calendar.getInstance()
    dateFormat.format(cal.getTime())
  }

}

object DateMain extends App {
  val currTime = DateUtils.getCurrentTime
  val currDate = DateUtils.getCurrentDate
  println(s"Current time: is $currTime")
  println(s"Current date: is $currDate")
}