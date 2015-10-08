package com.nap.search.conf.apis

import org.json4s.{NoTypeHints, jackson, Formats}
import spray.httpx.Json4sJacksonSupport

object MyJsonProtocol extends Json4sJacksonSupport {
  implicit def json4sJacksonFormats: Formats = jackson.Serialization.formats(NoTypeHints)
}