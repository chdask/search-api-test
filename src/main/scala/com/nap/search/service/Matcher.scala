package com.nap.search.service

import com.nap.search.conf.apis.ApiConf
import org.json4s.JsonAST._
import org.json4s.DefaultFormats
import org.json4s.jackson.JsonMethods._
import scalaj.http.Http

case class Product ( productId: Int, name: String, price: Long )

trait SourceData {
  def get(url:String) = Http(url).timeout(2000, 10000).execute[String]()
}

object Matcher extends SourceData {

  val url = ApiConf.SOURCE_URL

  def tokeniseTerms(queryParam:String) = queryParam.trim.split("[ \t]+").toList

  /**
   * Checks queryParam validity & source data is available.
   * If all conditions apply, matches the products against source.
   */
  def search(queryParam:String): Either[String, List[Product]] = {
    if (queryParam.length <= 1)
      Left("Query param cannot be a single character")
    else {
      val response = get(url)
      if (!response.is2xx) {
        Left("Source system is unreachable")
      } else {
        Right(matchProducts(response.body, tokeniseTerms(queryParam)))
      }
    }
  }

  /**
   * It first filters the data based on the search terms,
   * limits to 10000, and then returns the final matched products as a list of Product.
   */
  def matchProducts(json:String, searchTerms:List[String]): List[Product] = {
    implicit val formats = DefaultFormats
    val data = parse(json, useBigDecimalForDouble = true)

    (data \ "data").extract[JArray].children
      .filter(s => matchSearchTermsToProductName((s \ "name"  \ "en").extract[String], searchTerms))
      .take(10000)
      .map(p => {
          new Product(productId = (p \ "id").extract[Int],
                      name      = (p \ "name"  \ "en").extract[String],
                      price     = (p \ "price" \ "gross").extract[Int] / (p \ "price" \ "divisor").extract[Int])
      })
  }

  /**
   * matches the search terms against a product name
   */
  def matchSearchTermsToProductName( productName:String, terms: List[String] ): Boolean = {
    val nameWords = productName.split("[ \\-]+").toList.map(f => f.toLowerCase)
    val matchedTerms = terms.filter(term => nameWords.contains(term.toLowerCase))
    matchedTerms == terms
  }

}









