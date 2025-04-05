package sesion4

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

import scala.concurrent.duration.DurationConversions.fromNowConvert.R


object Sesion4 {
  def mapa(rdd: RDD[String])(implicit spark: SparkSession) = {
    rdd.map(s => (s, 1))
  }

  def generarTublas(rdd: RDD[String]) = rdd.map(s => (s, 1))


  def filtarImpar(rdd: RDD[Int]): RDD[Int] = rdd.filter((x => x % 2 != 0))

  def listaIncremental(rdd: RDD[Int]): RDD[(Int)] = rdd.flatMap(x => List(x, x * 10))


  def lecturaCsv(path: String)(sc: SparkContext): RDD[String] = sc.textFile(path)

  def lecturaCsvDf(path: String)(implicit  spark: SparkSession) = spark.read.csv(path)
}
