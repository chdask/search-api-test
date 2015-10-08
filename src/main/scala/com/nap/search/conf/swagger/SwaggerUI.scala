package com.nap.search.conf.swagger

import akka.actor.ActorRefFactory
import spray.http.StatusCodes
import spray.routing.HttpService

/**
  * Service that implements the route for SwaggerUI
  */
class SwaggerUI(implicit val actorRefFactory: ActorRefFactory) extends HttpService {

   def routes =
     path("swagger") {
       pathEnd {
         redirect("/swagger-ui/", StatusCodes.PermanentRedirect)
       }
     } ~
       pathPrefix("swagger-ui") {
         pathSingleSlash {
           getFromResource("swagger-ui/index.html")
         } ~
           getFromResourceDirectory("swagger-ui")
       }
 }