package com.nap.search.api

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest

class SearchApiTest extends Specification with Specs2RouteTest  with SearchApi {

  def actorRefFactory = system

  "The service" should {

    "return 200 when valid search terms" in {
      Get("/?q=blend") ~> searchApiRoute ~> check {
        response.status must be equalTo (200)
      }
    }

    "return 400 when 1 character param" in {
      Get("/?q=-") ~> searchApiRoute ~> check {
        response.status must be equalTo (400)
        response.entity.asString mustEqual ("\"Query param cannot be a single character\"")
      }
    }

  }
}