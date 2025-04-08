package sesion4

import org.apache.spark.rdd
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}
import sesion4.Sesion4._
import utils.TestInit
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


    "Generacion DF ,Filtrado y ordenado" should "Generar DF , Filtrar los alumnos >8 y Ordenarlos " in {



    import spark.implicits._

    "Generacion DF ,Filtrado y ordenado" should "Mostrar esquema, filtrar alumnos >8 y ordenarlos como DataFrame" in {

      // Creamos un DataFrame directamente desde datos simulados
      val df: DataFrame = Seq(
        ("Alice", "A001", 9),
        ("Bob", "A002", 7),
        ("Charlie", "A003", 10),
        ("David", "A004", 6),
        ("Eva", "A005", 8)
      ).toDF("name", "id", "grades")

      // Mostrar el esquema (puedes quitar esto en el test si no quieres salida de consola)
      df.printSchema()

      // Aplicar la lógica directamente sobre el DataFrame
      val dfFiltrado: DataFrame = df
        .filter($"grades" > 8)
        .orderBy($"grades".desc)

      // DataFrame esperado
      val out: DataFrame = Seq(
        ("Charlie", "A003", 10),
        ("Alice", "A001", 9)
      ).toDF("name", "id", "grades")

      // Comparación con tu función de test
      checkDf(out, dfFiltrado)
    }


      "Promedio por estudiante" should "calcular correctamente el promedio de notas" in {

        // Especificamos el path a los archivos CSV
        val pathEstudiantes = "src/test/resources/estudiantes.csv"
        val pathNotas = "src/test/resources/calificaciones.csv"

        // Cargamos los DataFrames desde los CSV
        val dfEstudiantes = lecturaCsvDf(pathEstudiantes)
        val dfNotas = lecturaCsvDf(pathNotas)

        // Aplicamos la lógica: join + promedio
        val df = promedioCalificacionesPorEstudiante(dfEstudiantes, dfNotas)

        // Mostramos resultado (opcional)
        df.show()

        // Aquí puedes comparar con un DataFrame esperado (ejemplo opcional):
        val out = Seq(
          ("Alice", 9.5),
          ("Bob", 8.0),
          ("Charlie", 7.5)
        ).toDF("nombre", "promedio")

        checkDf(out, df)
      }

      "ejercicio4" should "contar correctamente la frecuencia de cada palabra" in {

        val lista = List(
          "hola mundo",
          "hola spark",
          "mundo spark spark"
        )

        // 🧪 Llamamos a la función definida en Sesion4
        val resultado: RDD[(String, Int)] = ejercicio4(lista)

        // ✅ Convertimos a Map para comparar más fácilmente (no importa el orden)
        val resultadoMap: Map[String, Int] = resultado.collect().toMap

        // 🎯 Resultado esperado
        val esperado = Map(
          "hola"   -> 2,
          "mundo"  -> 2,
          "spark"  -> 3
        )

        // ✅ Comparamos el resultado real con el esperado
        resultadoMap shouldEqual esperado
      }
      }


    }




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
