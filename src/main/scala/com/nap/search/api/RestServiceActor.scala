package com.nap.search.api

import akka.actor.{Actor, ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import com.nap.search.conf.apis.{ApiConf, NapExceptionHandler, NapRejectionHandler}
import com.nap.search.conf.swagger.{Swagger, SwaggerUI}
import spray.can.Http

import scala.concurrent.duration._
import scala.reflect.runtime.universe._

object NapActorSystem {

  def boot() = {
    implicit val system = ActorSystem("on-spray-can")
    val service = system.actorOf(Props[RestServiceActor], "rest-service-actor")

    implicit val timeout = Timeout(5.seconds)
    IO(Http) ? Http.Bind(service, interface = ApiConf.BIND_IP, port = ApiConf.BIND_PORT)
  }
}

class RestServiceActor extends Actor with SearchApi with NapRejectionHandler with NapExceptionHandler {

  implicit val actorRefFactory = context
  implicit val apiTypes = Seq(typeOf[SearchApi])

  val swaggerApi = new Swagger
  val swaggerUI = new SwaggerUI

  def receive = runRoute(handleRejections(myRejectionHandler)(handleExceptions(myExceptionHandler)(
    searchApiRoute ~
      swaggerUI.routes ~
      swaggerApi.routes)))
}