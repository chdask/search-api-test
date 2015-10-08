package com.nap.search.api

import com.nap.search.conf.apis.Headers
import com.nap.search.conf.log.Logger
import com.nap.search.service.Matcher
import com.wordnik.swagger.annotations.{ApiImplicitParam, ApiImplicitParams, Api, ApiOperation}
import spray.httpx.encoding.{Deflate, NoEncoding, Gzip}
import spray.routing.HttpService

@Api(value = "/", description = "NAP search products API")
trait SearchApi extends HttpService with Logger {

  @ApiOperation(value = "Searches for products", httpMethod = "GET", produces = "application/json")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "q", value = "Search term", required = true, dataType = "string", paramType = "query", allowMultiple=true)
  ))
  def searchApiRoute =
    respondWithHeaders(Headers.AccessControlAllowAll, Headers.AccessControlAllowHeadersAll) {
      pathSingleSlash {
        options {
          complete(200, "ok")
        } ~
        get {
          parameters('q) { (q) =>
            compressResponse(Gzip, Deflate, NoEncoding) {
                import com.nap.search.conf.apis.MyJsonProtocol._
                Matcher.search(q) match {
                  case Right(listOfProducts) => complete(200,listOfProducts)
                  case Left(errors) => complete(400, errors)
                }
            }
          }
        }
      }
  }
}

