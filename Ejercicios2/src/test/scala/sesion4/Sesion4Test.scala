package sesion4

import org.apache.spark.rdd.RDD
import sesion4.Sesion4._
import utils.TestInit

class Sesion4Test extends TestInit{

  val sc = spark.sparkContext

  "Rdd" should "mapa" in {

    val rdd:RDD[String] = sc.parallelize(Seq("a", "b", "c"))
    val out = mapa(rdd)
    println(out.collect().mkString("\n"))
  }


}
