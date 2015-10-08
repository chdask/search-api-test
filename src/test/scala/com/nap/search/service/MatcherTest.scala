package com.nap.search.service

import org.scalatest.{Matchers, FlatSpec}

class MatcherTest extends FlatSpec with Matchers {

  behavior of "matcher"

  it should "return matched products " in {
    val product1 = new Product(602523,"London checked merino wool cape",550)
    val terms = List("merino")
    Matcher.matchProducts(sampleJson, terms) shouldBe List(product1)
  }

  it should "return empty list if no products match " in {
    val terms = List("jeans")
    Matcher.matchProducts(sampleJson, terms) shouldBe List.empty
  }

  behavior of "silly searches"

  it should "handle empty query param" in {
    Matcher.search("") shouldBe Left("Query param cannot be a single character")
  }

  it should "handle one char query param" in {
    Matcher.search("a") shouldBe Left("Query param cannot be a single character")
  }

  it should "handle hyphens and blanks " in {
    Matcher.search("-") shouldBe Left("Query param cannot be a single character")
    Matcher.search(" -       - ") shouldBe Right(List())
    Matcher.search("- -") shouldBe Right(List())
    Matcher.search("    ") shouldBe Right(List())
  }

  behavior of "matching terms against product name"

  it should "match search terms as one word" in {
    val productName = "Stripe-trimmed open-knit cotton-blend sweater"
    val terms = List("Stripe", "trimmed", "cotton")
    Matcher.matchSearchTermsToProductName(productName, terms) shouldBe true
    Matcher.matchSearchTermsToProductName(productName, terms.reverse) shouldBe true
  }

  it should "not match insufficient search terms" in {
    val productName = "Stripe-trimmed open-knit cotton-blend sweater"
    val terms = List("Leather", "poncho")
    Matcher.matchSearchTermsToProductName(productName, terms) shouldBe false
    Matcher.matchSearchTermsToProductName(productName, terms.reverse) shouldBe false
  }

  it should " not match search terms which contain hyphen" in {
    val productName =  "Stripe-trimmed open-knit cotton-blend sweater"
    val terms = List("cotton-blend")
    Matcher.matchSearchTermsToProductName(productName, terms) shouldBe false
  }

  it should " not match search terms which contain space" in {
    val productName =  "Stripe-trimmed open-knit cotton-blend sweater"
    val terms = List("blend sweat")
    Matcher.matchSearchTermsToProductName(productName, terms) shouldBe false
  }

  it should " match exact words only" in {
    val productName =  "Stripe-trimmed open-knit cotton-blend sweater"
    val terms = List("sweat")
    Matcher.matchSearchTermsToProductName(productName, terms) shouldBe false
  }

  it should "not match when search term equals product name" in {
    val productName =  "Stripe-trimmed open-knit cotton-blend sweater"
    val terms = List("Stripe-trimmed open-knit cotton-blend sweater")
    Matcher.matchSearchTermsToProductName(productName, terms) shouldBe false
  }

  val sampleJson =
    """
      |{
      |  "data": [
      |    {
      |      "name": {
      |        "en": "London checked merino wool cape",
      |        "fr": "Cape en laine mérinos à carreaux London",
      |        "de": "London kariertes Cape aus Merinowolle",
      |        "zh": "London 格纹美利奴羊毛披风"
      |      },
      |      "visible": true,
      |      "price": {
      |        "divisor": 100,
      |        "duty": 0,
      |        "tax": 9167,
      |        "gross": 55000,
      |        "net": 45833,
      |        "currency": "GBP"
      |      },
      |      "onSale": false,
      |      "brand": {
      |        "id": 1079,
      |        "name": {
      |          "en": "Burberry London"
      |        }
      |      },
      |      "analyticsKey": "London checked merino wool cape",
      |      "id": 602523,
      |      "categories": [
      |        {
      |          "id": 19943,
      |          "children": [
      |            {
      |              "id": 20006,
      |              "children": [
      |                {
      |                  "id": 20294
      |                }
      |              ]
      |            }
      |          ]
      |        },
      |        {
      |          "id": 5,
      |          "children": [
      |            {
      |              "id": 4279,
      |              "children": [
      |                {
      |                  "id": 5181
      |                },
      |                {
      |                  "id": 5166
      |                }
      |              ]
      |            }
      |          ]
      |        },
      |        {
      |          "id": 2,
      |          "children": [
      |            {
      |              "id": 49,
      |              "children": [
      |                {
      |                  "id": 10627
      |                },
      |                {
      |                  "id": 6385
      |                },
      |                {
      |                  "id": 6130
      |                }
      |              ]
      |            }
      |          ]
      |        }
      |      ],
      |      "colourIds": [
      |        58
      |      ],
      |      "saleableStandardSizes": [],
      |      "badges": [
      |        "In_Stock"
      |      ]
      |    }
      |  ]
      |}
    """.stripMargin
}
