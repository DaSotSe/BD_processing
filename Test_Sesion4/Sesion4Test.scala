package sesion4

import org.apache.spark.rdd
import org.apache.spark.rdd.RDD
import sesion4.Sesion4._
import utils.TestInit

class Sesion4Test extends TestInit{

  val sc = spark.sparkContext

  "Rdd" should "mapa" in {

    val rdd:RDD[String] = sc.parallelize(Seq("a", "b", "c","d"))
    val out = mapa(rdd)
    println(out.collect().mkString("::"))
  }

  "Rdd" should "generarTublas" in {


    val rdd: RDD[String] = sc.parallelize(Seq("a","b","c"))
    val out = generarTublas(rdd)

    println(out.collect().mkString("\n"))
  }

  "Rdd" should "filtarImpar" in {

    val rdd = sc.parallelize(Seq(1,2,3,4,5))
    val out = filtarImpar(rdd)

    println(out.collect() shouldBe Seq(1,3,5))
  }
  "Rdd" should "listaIncremental" in {

    val rdd = sc.parallelize(Seq(1,2,3,4))
    val out = listaIncremental(rdd)

    out.collect() shouldBe Seq(1,10,2,20,3,30,4,40)
  }

"Lectura CSV" should "read" in {
  val RDD = lecturaCsv("/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/main/scala/ventas.csv")(sc)
  RDD.take(5).foreach(println)

}

  "Lectura DF" should "read" in {
    val df = lecturaCsvDf("/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/main/scala/ventas.csv")
    df.show(false)

  }

  "Escritura DF" should "escribir DF" in {
    val df = spark.read
      .option("header", true)
      .csv("/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/main/scala/ventas.csv")

    writecsv(df, "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/examen")

  }

  "Escritura en Parquet" should "guardar un DataFrame" in {
    val df = spark.read
      .option("header", true)
      .csv("/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/main/scala/ventas.csv")

    writeParquet(df, "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/examen")
  }

}
