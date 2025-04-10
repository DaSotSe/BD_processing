package sesion4

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions.avg

import scala.concurrent.duration.DurationConversions.fromNowConvert.R

case class Scores(name: String, age: Int, grades: Int)

object Sesion4 {

  //Ejercicio 1

  //Parto inicialmente de un case Class , posteriormente creo el DF que tiene de nombre Scores en vez de Estudiantes, contiene los atributos solicitados

  case class Scores(name: String, age: Int, grades: Int)

  def scoresToDs(df:DataFrame)(implicit spark: SparkSession): Dataset[Scores] = {
    import spark.implicits._

    df.as[Scores]
  }

  //Ejercicio 2

  def ejercicio2(numeros: DataFrame)(implicit spark: SparkSession): DataFrame = {
    import spark.implicits._
    import org.apache.spark.sql.functions.udf

    // val esParOImpar = udf((n: Int) => if (n % 2 == 0) "par" else "impar") CON IF
    val esParOImpar = udf((n: Int) => (n % 2) match {
      case 0 => "par"
      case _ => "impar"
    })

    numeros.withColumn("par_impar", esParOImpar($"num_productos"))
  }

  //Ejercicio 3

  def lecturaCsvDf2(path: String)(implicit spark: SparkSession): DataFrame = {
    spark.read.option("header", "true").option("inferSchema", "true").csv(path)
  }

  //ordenado por orden descendiente
  def promedioCalificacionesPorEstudiante(dfEstudiantes: DataFrame, dfNotas: DataFrame)(implicit spark: SparkSession): DataFrame = {

    import spark.implicits._

    val joined = dfEstudiantes.join(dfNotas, dfEstudiantes("id_estudiante") === dfNotas("id_estudiante"))
    val promedio = joined
      .groupBy(dfEstudiantes("nombre"))
      .agg(avg("calificacion").alias("promedio"))
      .orderBy($"promedio".desc)

    promedio
  }

  //Ejercicio 4

  def contarPalabasRDD(palabras: List[String])(implicit spark: SparkSession): RDD[(String, Int)] = {
    val sc = spark.sparkContext

    val rdd = sc.parallelize(palabras)

    rdd.flatMap(_.split(" ")).map(_.toLowerCase()).map(palabra => (palabra, 1)).reduceByKey(_ + _)
  }

  //Ejercicio 5

  def lecturaCsvVentas(path: String)(implicit spark: SparkSession): DataFrame = {
    spark.read.option("header", "true").option("inferSchema", "true").csv(path)
  }

  def calcularIngresosPorProducto(df: DataFrame)(implicit spark: SparkSession): DataFrame = {import spark.implicits._
    import org.apache.spark.sql.functions._

    df.withColumn("ingreso", $"cantidad" * $"precio_unitario").groupBy($"id_producto").agg(round(sum($"ingreso"), 2).alias("ingreso_total")).orderBy($"id_producto")
  }

    def lecturaCsvOperaciones(path: String)(implicit spark: SparkSession): DataFrame = {
      spark.read.option("header", "true").option("inferSchema", "true").csv(path)
    }




}
