package Sesion5
/*
// Importa clases necesarias de Spark SQL
import org.apache.spark.sql.{Dataset, Row}

// Importa la case class Cuenta desde el paquete Sesion5
import Sesion5.Cuenta

// Importa funcionalidades auxiliares definidas en TestInit (probablemente configuración del SparkSession, funciones de comparación, etc.)
import utils.TestInit


class Sesion5Test extends TestInit {


  // Define un test con estilo BDD: "Dataset" should "generarTuplas"
  "Dataset" should "generarTuplas" in {

    // Importa implicits necesarios para poder usar `.toDS()` y trabajar con Datasets
    import spark.implicits._

    // Crea un Dataset[Cuenta] a partir de una secuencia de objetos Cuenta (tipados)
    val ventasDs: Dataset[Cuenta] = Seq(
      Cuenta("1", Some("101"), Some("1"), Some("15.0")),
      Cuenta("2", Some("102"), Some("2"), Some("15.0")),
      Cuenta("3", Some("103"), Some("3"), Some("15.0")),
      Cuenta("4", Some("104"), Some("4"), Some("15.0"))
    ).toDS() // Convierte la secuencia en Dataset[Cuenta] usando los implicits de Spark

    // Muestra el contenido original del Dataset (como una tabla)
    ventasDs.show()

    // Aplica una transformación sobre cada fila del Dataset usando `.map`
    ventasDs.map(transaction =>

      // Copia el objeto original, modificando el campo `cantidad`:
      // - accede a transaction.cantidad (que es Option[String])
      // - lo convierte a Int, lo multiplica por 0.91 (descuento del 9%), y luego a String
      // - lo vuelve a envolver en Some(...) y lo asigna al nuevo campo `cantidad`
      transaction.copy(cantidad = Some((transaction.cantidad.get.toInt * 0.91).toString))

      // Cierra el map y muestra el Dataset transformado
    ).show()
  }

*/
/*
// src/test/scala/sesion5/Sesion5Test.scala
package sesion5

import org.apache.spark.sql.{DataFrame, Dataset}
import utils.TestInit
import Sesion5._  // Importamos nuestras funciones y case class
import org.scalatest.matchers.should.Matchers

class Sesion5Test extends TestInit with Matchers {

  test("cuentasToDataset convierte correctamente un DataFrame a Dataset[Cuenta]") {
    import spark.implicits._

    // Simulamos un DataFrame como el que vendría de un CSV
    val df: DataFrame = Seq(
      (1, Some("101"), Some(2), Some(10.5)),
      (2, Some("102"), None, Some(5.0))
    ).toDF("id_venta", "id_producto", "cantidad", "precio_unitario")

    // Aplicamos la función
    val result: Dataset[Cuenta] = cuentasToDataset(df)

    // Comprobamos que los datos coincidan con lo esperado
    val expected = Seq(
      Cuenta(1, Some("101"), Some(2), Some(10.5)),
      Cuenta(2, Some("102"), None, Some(5.0))
    ).toDS()

    assert(result.collect().sameElements(expected.collect()))
  }

  test("cambiarPrecioCantidad transforma correctamente la cantidad y precio_unitario") {
    import spark.implicits._

    val input = Seq(
      Cuenta(1, Some("101"), Some(2), Some(10.0)),
      Cuenta(2, Some("102"), None, None)
    ).toDS()

    val transformed = cambiarPrecioCantidad(input)

    val expected = Seq(
      // cantidad original 2 → +1 = 3 ; precio original 10.0 + (2 * 0.91) = 10.0 + 1.82 = 11.82
      Cuenta(1, Some("101"), Some(3), Some(11.82)),
      // cantidad None → 0 + 1 = 1 ; precio None → 0.0 + (1 * 0.91) = 0.91
      Cuenta(2, Some("102"), Some(1), Some(0.91))
    )

    val transformedData = transformed.collect()
    transformedData.length shouldBe 2
    transformedData(0) shouldEqual expected(0)
    transformedData(1).cantidad shouldEqual expected(1).cantidad
    transformedData(1).precio_unitario.getOrElse(0.0) shouldEqual expected(1).precio_unitario.getOrElse(0.0)
  }
}
 */
/*USO DE UNION ENTRE DATAFRAMES
// Mostrar el contenido original del Dataset tipado
ventasDs.show()

// Unión del Dataset consigo mismo (duplicando filas)
ventasDs.union(ventasDs).show()

// Convertir el Dataset[Cuenta] a un DataFrame (para usar funciones generales)
val df = ventasDs.toDF()

// Convertir otro Dataset (productoDs) a DataFrame también
val df2 = productoDs.toDF()

// Unión de ambos DataFrames por nombre de columnas, permitiendo columnas distintas
df.unionByName(df2, allowMissingColumns = true).show()

// Aplicar la transformación `cambiarPrecioCantidad` y mostrar el Dataset resultante
cambiarPrecioCantidad(ventasDs).show()

*/
/*
import org.apache.spark.sql.functions._
FUNCIONES DE FILTROS
// FILTROS

// Filtra con una cadena SQL: solo ventas con id_venta = 2
ventasDs.filter("id_venta = 2").show()

// Filtra usando la API de columnas: equivalente a lo anterior
ventasDs.filter(col("id_venta") === 2).show()

// Filtra con múltiples condiciones y la API de columnas con notación Scala
ventasDs.filter($"id_venta" === 2 && $"id_producto" === "102").show()

FUNCIONES DE JOINS
// JOINS

// INNER JOIN entre ventas y productos por id_producto (solo donde coinciden)
ventasDs.join(productoDs, ventasDs("id_producto") === productoDs("id_producto")).show()

// LEFT JOIN: muestra todas las ventas, aunque no tengan producto coincidente
ventasDs.join(productoDs, ventasDs("id_producto") === productoDs("id_producto"), "left").show()

// RIGHT JOIN: muestra todos los productos, aunque no tengan ventas asociadas
ventasDs.join(productoDs, Seq("id_producto"), "right").show()
 */




}
