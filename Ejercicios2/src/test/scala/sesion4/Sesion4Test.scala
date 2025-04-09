package sesion4

import org.apache.spark.rdd
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import sesion4.Sesion4._
import utils.TestInit
import org.apache.spark.sql.{DataFrame, Row, SparkSession}
import org.apache.spark.sql.types._

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

    //writeParquet(df, "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/examen")

    df.write.partitionBy("Id_producto")
      .mode("Overwrite")
      .parquet("/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/examen")

  }

  "Generacion DF,Filtrado y ordenado" should "Mostrar esquema, filtrar alumnos puntuacion >8 y ordenarlos como DataFrame" in {

    import spark.implicits._

    //Generamos un DF
    val df: DataFrame = Seq(
      ("Xose", 30, 9),
      ("Miguel", 27, 7),
      ("Miles", 45, 10),
      ("Donald", 37, 6),
      ("Thelonius", 31, 8)
    ).toDF("name", "age", "grades")

    // Mostramos el Schema
    df.printSchema()

    // Filtrado de DF
    val dfFiltrado: DataFrame = df
      .filter($"grades" > 8)
      .orderBy($"grades".desc)

    // DF esperado
    val out: DataFrame = Seq(
      ("Miles", 45, 10),
      ("Xose", 30, 9)
    ).toDF("name", "age", "grades")

    // Comprobacion
    checkDf(out, dfFiltrado)
  }

  "Promedio por estudiante" should "calcular correctamente el promedio de notas" in {

    import spark.implicits._


    val pathEstudiantes = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/test/resources/estudiantes.csv"
    val pathNotas = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/test/resources/calificaciones.csv" // ✅ ¡corregido!

    val dfEstudiantes = lecturaCsvDf2(pathEstudiantes)
    val dfNotas = lecturaCsvDf2(pathNotas)

    val df = promedioCalificacionesPorEstudiante(dfEstudiantes, dfNotas)

    df.show()

    // Comprobacion
    val out = Seq(
      ("Alice", 9.5),
      ("Bob", 8.0),
      ("Charlie", 7.5)
    ).toDF("nombre", "promedio")

    checkDfIgnoreDefault(out, df)
  }

  "ejercicio4" should "contar correctamente la frecuencia de cada palabra" in {

    val lista = List("hola miles", "hola thelonius", "mundo miles thelonius")

    val resultado: RDD[(String, Int)] = contarPalabasRDD(lista)

    val resultadoMap: Map[String, Int] = resultado.collect().toMap

    val esperado = Map("hola"   -> 2, "mundo"  -> 1, "miles"  -> 2, "thelonius"  -> 2)

    resultadoMap shouldEqual esperado
  }

  "ejercicio5" should "ingreso total por producto" in {


    val path = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/test/resources/ventas.csv"

    val dfVentas: DataFrame = lecturaCsvVentas(path)

    val dfResultado = calcularIngresosPorProducto(dfVentas)
    import spark.implicits._

    val resultadoMap: Map[String, Double] = dfResultado.as[(String, Double)].collect().toMap

    val esperado: Map[String, Double] = Map("A" -> 30.0, "B" -> 15.0)

    resultadoMap shouldEqual esperado
  }


    "ejercicio2" should "añadir una columna que indica si el número de productos es par o impar" in {
      import spark.implicits._

      val path = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Ejercicios2/src/test/resources/operaciones.csv"

      val dfOriginal: DataFrame = lecturaCsvOperaciones(path)
      val dfResultado: DataFrame = ejercicio2(dfOriginal)


      val dfEsperado = Seq((101, 2,  40.0, "par"), (102, 5, 100.0, "impar"), (103, 4,  80.0, "par"), (104, 7, 140.0, "impar"), (105, 6, 120.0, "par")).toDF("id_operacion", "num_productos", "importe_total", "par_impar")

      checkDfIgnoreDefault(dfEsperado, dfResultado)
    }



    /* esta es una prueba para hacer un esquema pero no se han realizado conversiones para que sea con DF (se hace con una funcion con RDD)
  "Dataframe" should "Read" in {

      // Definir esquema
      val schema = StructType(Seq(
        StructField("id_venta", StringType, nullable = false),
        StructField("id_producto", StringType, nullable = true),
        StructField("cantidad", StringType, nullable = true),
        StructField("precio_unitario", StringType, nullable = true)
      ))

      //Crear datos dummy
      val datos = Seq(Row("1", "101", "2", "11.0"),
                      Row("2", "102", "3", "15.0"))

      // Convertir a Dataframe
      val df = newDf(datos,schema)
      val in = filtarImpar(df)// la funcion filtar espera como entrada un RDD, por eso da error

      val out = newDf(Seq(Row("2", "102", "3", "15.0")),schema)
      // asi es como se valida los dataframe del test
      checkDfIgnoreDefault(in,out)//la funcion filtar espera como salida un RDD, por eso da error
*/








}
