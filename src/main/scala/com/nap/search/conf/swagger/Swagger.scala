package com.nap.search.conf.swagger

import akka.actor.ActorRefFactory
import com.gettyimages.spray.swagger.SwaggerHttpService

import scala.reflect.runtime.universe.Type

class Swagger(implicit val actorRefFactory: ActorRefFactory, implicit val apiTypes: Seq[Type]) extends SwaggerHttpService {
   def apiVersion = "1.0"
   def baseUrl ="/"
   def specPath = "api-spec"
   def resourcePath = "resources"
 }
