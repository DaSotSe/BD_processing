package sesion4

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions.avg


import scala.concurrent.duration.DurationConversions.fromNowConvert.R
case class scores(name:String,id:String,grades:Int)

object Sesion4 {
  def mapa(rdd: RDD[String])(implicit spark: SparkSession) = {
    rdd.map(s => (s, 1))
  }

  def generarTublas(rdd: RDD[String]) = rdd.map(s => (s, 1))


  def filtarImpar(rdd: RDD[Int]): RDD[Int] = rdd.filter((x => x % 2 != 0))

  def listaIncremental(rdd: RDD[Int]): RDD[(Int)] = rdd.flatMap(x => List(x, x * 10))


  def lecturaCsv(path: String)(sc: SparkContext): RDD[String] = sc.textFile(path)

  def lecturaCsvDf(path: String)(implicit  spark: SparkSession) = spark.read.csv(path)


  def writecsv(df:DataFrame,path:String) = df.write.option("header",true).mode("overwrite").csv(path)

  def writeParquet(df: DataFrame, path: String): Unit =
    df.write.mode("overwrite").parquet(path)

//Ejercicio 1
  def scoresToDs(df:DataFrame)(implicit spark: SparkSession): Dataset[scores] = {
    import spark.implicits._

    df.as[scores]



    //Ejercicio 2

    def lecturaCsvDf(path: String)(implicit spark: SparkSession): DataFrame = {
      spark.read
        .option("header", "true")       // El CSV tiene encabezado
        .option("inferSchema", "true")  // Detecta automáticamente los tipos (int, string, etc.)
        .csv(path)
    }

    // Función que une estudiantes con calificaciones y calcula el promedio por estudiante
    def promedioCalificacionesPorEstudiante(
                                             dfEstudiantes: DataFrame,
                                             dfNotas: DataFrame
                                           )(implicit spark: SparkSession): DataFrame = {

      import spark.implicits._

      // Hacemos join por id del estudiante
      val joined = dfEstudiantes
        .join(dfNotas, dfEstudiantes("id") === dfNotas("id_estudiante"))

      // Calculamos promedio de calificaciones por estudiante
      val promedio = joined
        .groupBy("nombre") // o también se puede usar dfEstudiantes("nombre")
        .agg(
          avg("calificacion").alias("promedio")
        )
        .orderBy($"promedio".desc)

      promedio
    }

    //Ejercicio 4

    def ejercicio4(palabras: List[String])(implicit spark: SparkSession): RDD[(String, Int)] = {
      val sc = spark.sparkContext

      val rdd = sc.parallelize(palabras)

      rdd
        .flatMap(_.split("\\s+"))
        .map(_.toLowerCase())
        .map(palabra => (palabra, 1))
        .reduceByKey(_ + _)
    }

    }



  }
}





  }
