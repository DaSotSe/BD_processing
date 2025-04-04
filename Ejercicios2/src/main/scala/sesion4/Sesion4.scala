package sesion4

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

import scala.concurrent.duration.DurationConversions.fromNowConvert.R


object Sesion4 {
  def mapa(rdd: RDD[String])(implicit spark: SparkSession) = {
    rdd.map(s => (s, 1))
  }
}
