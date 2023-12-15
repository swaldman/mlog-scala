package com.mchange.sc.v1.log

trait SelfLogging {
  protected implicit lazy val logger : MLogger = MLogger(this)
}
