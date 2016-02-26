package domain.organisation

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/03.
 */
case class OrganisationLogo(org: String,
                       id: String,
                       url: String,
                       size: Option[String],
                       description:String,
                       mime: String,
                       date: Date)

object OrganisationLogo {

  implicit val orgLogoFmt = Json.format[OrganisationLogo]

}
