package conf.connection

import com.websudos.phantom.connectors.{KeySpace, SimpleCassandraConnector}

/**
 * Created by hashcode on 2015/06/07.
 */
trait DataConnection extends SimpleCassandraConnector {

  implicit val keySpace = new KeySpace("infoshare")
}



