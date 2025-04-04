import org.apache.spark.sql.Row.empty.schema
import org.apache.spark.sql.SparkSession

import sesion4.spUtils.SparkUtils.runSparkSession

object Main {
  def main(args: Array[String]): Unit = {

    implicit val spark: SparkSession = runSparkSession("Keepkoding")

    spark.read.csv("/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/main/scala/ventas.csv").show(false)


  }
}