package lightning.maps.logging

import org.slf4j.{ LoggerFactory }
import java.util.Date

trait Loggable {

  private val log = LoggerFactory.getLogger(getClass)

  final def debug(msg: => String) = if (log.isDebugEnabled) log.debug(msg)
  final def debug(msg: => String, e: => Throwable) = if (log.isDebugEnabled) log.debug(msg, e)

  final def info(msg: => String) = if (log.isInfoEnabled) log.info(msg)
  final def info(msg: => String, e: => Throwable) = if (log.isInfoEnabled) log.info(msg, e)

  final def warn(msg: => String) = if (log.isWarnEnabled) log.warn(msg)
  final def warn(msg: => String, e: => Throwable) = if (log.isWarnEnabled) log.warn(msg, e)

  final def error(msg: => String) = if (log.isErrorEnabled) log.error(msg)
  final def error(msg: => String, e: => Throwable) = if (log.isErrorEnabled) log.error(msg, e)

  final def trace(msg: => String) = if (log.isTraceEnabled) log.trace(msg)
  final def trace(msg: => String, e: => Throwable) = if (log.isTraceEnabled) log.trace(msg, e)

  final def debugTime[B](comment: String)(functionToTime: => B): B = time(comment, functionToTime, log.debug(_), log.isDebugEnabled)
  final def infoTime[B](comment: String)(functionToTime: => B): B = time(comment, functionToTime, log.info(_), log.isInfoEnabled())
  final def warnTime[B](comment: String)(functionToTime: => B): B = time(comment, functionToTime, log.warn(_), log.isWarnEnabled())
  final def errorTime[B](comment: String)(functionToTime: => B): B = time(comment, functionToTime, log.error(_), log.isErrorEnabled())
  final def traceTime[B](comment: String)(functionToTime: => B): B = time(comment, functionToTime, log.trace(_), log.isTraceEnabled())

  final def time[B](comment: String, functionToTime: => B, logFuntion: String => Unit, logEnabled: Boolean): B = {
    if (logEnabled) {
      val start = new Date()
      logFuntion("Start at : " + start.getTime() + " " + comment)
      val result = functionToTime
      val end = new Date()
      logFuntion("End at : " + end.getTime() + " total time: " + (end.getTime() - start.getTime()) + " " +  comment)
      result
    } else {
      functionToTime
    }
  }
}