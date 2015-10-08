package com.nap.search.conf.apis

import com.wordnik.swagger.annotations.{ApiModelProperty, ApiModel}
import scala.annotation.meta.field

@ApiModel(description = "Error response")
case class ErrorResponse (
                           @(ApiModelProperty @field)(value = "Error Message") message: String,
                           @(ApiModelProperty @field)(value = "Backtrace") backtrace: String
                           )