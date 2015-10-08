package com.nap.search.conf.apis

import spray.http.HttpHeaders

object Headers {

  val AccessControlAllowMethods = HttpHeaders.RawHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST")

  val AccessControlAllowHeadersAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Headers",
    "Authorization,Origin, X-Requested-With, Content-Type, Accept, Accept-Encoding, Accept-Language, Host, Referer, User-Agent"
  )

  val AccessControlAllowAll = HttpHeaders.RawHeader(
    "Access-Control-Allow-Origin", "*"
  )

}
