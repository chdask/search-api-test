package com.nap.search.conf.apis

import com.typesafe.config.ConfigFactory

object ApiConf {

  val configuration = ConfigFactory.load

  val BIND_IP = configuration.getString("api.bind.ip")
  val BIND_PORT = configuration.getString("api.bind.port").toInt

  val SOURCE_URL = configuration.getString("source.url")
}
