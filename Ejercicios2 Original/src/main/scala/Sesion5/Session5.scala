/*package Sesion5

import DFutils.utils.writeDs // viene del archivo de utils

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession


/* case class Cuenta(
                   id_venta: String,
                   id_producto: Option[String],
                   cantidad: Option[String],
                   precio_unitario: Option[String]
                 )

*/

object Session5 {



/*
   por ejemplo como estructura de proyecto DE ETL def run(implicit spark:SparkSession): Unit = {

  ejemplo de estructura liena 1: def run(implicit spark:SparkSession): Unit = {
  ejemplo de estructura liena 2:  val dftransaction= lecturaCsvdf("path/out") LECTURA

  ejemplo de estructura liena 3: val out = transforms(dftransaction) la funcion de transformacion va debajo en este archivo TRANSFORMACION

  ejemplo de estructura liena 4: writeCsv(out,"path/out") ESCRITURA
*/
/*
    def cuentasToDataset(df:dataFrame)(implicit spark: SparkSession: unit = {

    }
  }
*/
/*
// Definimos un objeto singleton llamado `Sesion5`
// Aquí agrupamos la lógica relacionada con la transformación de datos de ventas
object Sesion5 {

  // Definición de una case class llamada `Cuenta`
  // Representa la estructura tipada de una fila de datos de ventas
  // Usar Option permite que estos campos puedan tener valores nulos o ausentes (como en CSVs)
  case class Cuenta(
    id_venta: Int,                          // ID de la venta (entero)
    id_producto: Option[String],            // ID del producto vendido
    cantidad: Option[Int],                  // Cantidad vendida
    precio_unitario: Option[Double]         // Precio por unidad
  )

  // Función que convierte un DataFrame a un Dataset[Cuenta]
  // Requiere un SparkSession en contexto (por eso se pasa como `implicit`)
  def cuentasToDataset(df: DataFrame)(implicit spark: SparkSession): Dataset[Cuenta] = {

    // Importa los implicits necesarios para que `.as[Cuenta]` funcione
    import spark.implicits._

    // Convierte el DataFrame a un Dataset tipado usando la case class `Cuenta`
    // Spark intentará mapear automáticamente las columnas del DataFrame a los campos de la clase
    df.as[Cuenta]
  }

  // Función que transforma un Dataset[Cuenta] ajustando la cantidad y el precio_unitario
  // También recibe implícitamente un SparkSession
  def cambiarPrecioCantidad(ds: Dataset[Cuenta])(implicit spark: SparkSession): Dataset[Cuenta] = {

    // Importa implicits para poder trabajar con operaciones como `.map` en Datasets
    import spark.implicits._

    // Aplica una transformación funcional con `.map` sobre cada elemento del Dataset
    // `transaction` es una instancia de `Cuenta`
    ds.map { transaction =>

      // Calcula una nueva cantidad ajustada aplicando un "descuento del 9%" (multiplicación por 0.91)
      // Si `cantidad` es None, se usa 1 como valor por defecto
      val cantidad = transaction.cantidad.getOrElse(1) * 0.91

      // Devuelve una nueva copia del objeto `Cuenta` con los valores transformados
      transaction.copy(
        // Aumentamos la cantidad original en 1. Si no hay cantidad, se usa 0 + 1
        cantidad = Some(transaction.cantidad.getOrElse(0) + 1),

        // Actualiza el precio_unitario sumando la cantidad calculada (como si el nuevo precio dependiera de la cantidad vendida)
        // Si no hay precio previo, parte de 0.0
        precio_unitario = Some(transaction.precio_unitario.getOrElse(0.0) + cantidad)
      )
    }
  }

}
*/
  /*
// la version considerando que halla valores none no queremos que salgan en el resultado

// Función que transforma un Dataset[Cuenta] modificando la cantidad y el precio_unitario
def cambiarPrecioCantidad(ds: Dataset[Cuenta])(implicit spark: SparkSession): Dataset[Cuenta] = {
  import spark.implicits._

  // Aplica transformación fila por fila de forma funcional
  ds.map { transaction =>

    // Hace pattern matching sobre las opciones `cantidad` y `precio_unitario`SE CREA UNA TUPLA PARA RECOGER LA CASUISTICA
    (transaction.cantidad, transaction.precio_unitario) match {

      // Si ambas están definidas (Some), realiza la transformación
      case (Some(cantidad), Some(precio)) =>
        transaction.copy(
          // Suma 1 a la cantidad actual
          cantidad = Some(cantidad + 1),

          // Añade al precio: cantidad * 0.91 (como un ajuste o recargo)
          precio_unitario = Some(precio + cantidad * 0.91)
        )

      // Si falta alguno de los dos valores (cantidad o precio), deja la fila igual
      case _ => transaction
    }
  }
}
 */


}*/
