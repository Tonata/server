import conf.util.CORSFilter
import play.api.{Application, GlobalSettings}
import play.api.mvc.WithFilters

/**
 * Created by hashcode on 2015/06/28.
 */
object Global extends WithFilters(CORSFilter()) with GlobalSettings {
  override def onStart(app: Application): Unit = {
    super.onStart(app)
  }
}