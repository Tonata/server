package domain.organisation

import java.util.Date

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
  * Created by user42 on 2016/10/04.
  */
case class OrganisationModel ( id: String,
                               name: String,
                               details:Map[String,String],
                               adminattached:String,
                               date:Date,
                               state:String)
{
  def getDomain(): Organisation = OrganisationModel.domain(this)
}

object OrganisationModel{
  implicit val orgFmt = Json.format[OrganisationModel]

  def domain(model: OrganisationModel) = {

    Organisation (model.id,
      model.name,
      model.details,
      model.adminattached,
      model.date,
      model.state)
  }
}

