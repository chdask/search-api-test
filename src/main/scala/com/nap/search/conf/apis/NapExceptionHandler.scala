package com.nap.search.conf.apis

import com.nap.search.conf.log.Logger
import spray.routing.{ExceptionHandler, HttpService}

trait NapExceptionHandler extends HttpService with Logger{
  implicit val myExceptionHandler =
    ExceptionHandler.apply {
      case e: Exception => ctx => {
        logger.debug("%s %n%s %n%s".format(e.getMessage, e.getStackTrace, e.getCause))
        ctx.complete(500, e.getMessage)
      }
    }
}