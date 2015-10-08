package com.nap.search.conf.log

import org.slf4j.LoggerFactory

trait Logger {
  def logger = LoggerFactory.getLogger(this.getClass)
}
