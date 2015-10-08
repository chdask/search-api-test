package com.nap.search.conf.apis

import spray.http.StatusCodes
import spray.routing.{AuthenticationFailedRejection, MalformedRequestContentRejection, RejectionHandler, HttpService}

trait NapRejectionHandler extends HttpService {
  implicit val myRejectionHandler = RejectionHandler {
    case MalformedRequestContentRejection(message, Some(throwable)) :: _ => complete(StatusCodes.BadRequest,"Bad request" + throwable.getMessage)
  }
}
