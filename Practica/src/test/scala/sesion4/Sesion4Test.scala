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

  //Ejercicio 1

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

    dfFiltrado.show()

    // DF esperado
    val out: DataFrame = Seq(
      ("Miles", 45, 10),
      ("Xose", 30, 9)
    ).toDF("name", "age", "grades")

    // Comprobacion
    checkDf(out, dfFiltrado)
  }

  //Ejercicio 2

  "ejercicio2" should "añadir una columna que indica si el número de productos es par o impar" in {
    import spark.implicits._

    val path = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Practica/src/test/resources/operaciones.csv"

    val dfOriginal: DataFrame = lecturaCsvOperaciones(path)
    val dfResultado: DataFrame = ejercicio2(dfOriginal)

    dfResultado.show()
    val dfEsperado = Seq((101, 2, 40.0, "par"), (102, 5, 100.0, "impar"), (103, 4, 80.0, "par"), (104, 7, 140.0, "impar"), (105, 6, 120.0, "par")).toDF("id_operacion", "num_productos", "importe_total", "par_impar")

    checkDfIgnoreDefault(dfEsperado, dfResultado)

  }
    //Ejercicio 3
    "Promedio por estudiante" should "calcular correctamente el promedio de notas" in {

      import spark.implicits._


      val pathEstudiantes = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Practica/src/test/resources/estudiantes.csv"
      val pathNotas = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Practica/src/test/resources/calificaciones.csv" // ✅ ¡corregido!

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
    //Ejercicio 4

    "ejercicio4" should "contar correctamente la frecuencia de cada palabra" in {

      val lista = List("hola miles", "hola thelonius", "mundo miles thelonius")

      val resultado: RDD[(String, Int)] = contarPalabasRDD(lista)

      val resultadoMap: Map[String, Int] = resultado.collect().toMap

      val esperado = Map("hola" -> 2, "mundo" -> 1, "miles" -> 2, "thelonius" -> 2)

      resultadoMap shouldEqual esperado
    }

    //Ejercicio 5

    "ejercicio5" should "ingreso total por producto" in {


      val path = "/Users/davidsoteloseguin/Library/Mobile Documents/com~apple~CloudDocs/Personal/Formacion /Bootcamp/Bootcamp KC/BD_Processing/BD_Process_Ejercicios/Practica/src/test/resources/ventas.csv"

      val dfVentas: DataFrame = lecturaCsvVentas(path)

      val dfResultado = calcularIngresosPorProducto(dfVentas)
      import spark.implicits._

      val resultadoMap: Map[String, Double] = dfResultado.as[(String, Double)].collect().toMap

      dfResultado.show()

      val esperado: Map[String, Double] = Map("A" -> 30.0, "B" -> 15.0)

      resultadoMap shouldEqual esperado
    }












}
