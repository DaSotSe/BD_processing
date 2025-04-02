# Codigo de Main
''' 
 /**
   * Esta es una explicacion de lo que hace la funcion
   *
   * @param texto este parametro es de tipo string
   */

  def imprimir(texto: String): Unit = {
    println(texto)
  }

  def tiposBasicos(): Unit = {
    val entero1: Int = 12
    val buleano: Boolean = true
    val double = 2.2
    val suma = 1 + entero1
    val texto = "hola que tal"

    val textoConSalto =
      """ Hola que tal
        |Yo estoy bien
        |y tu que?
        |""".stripMargin

  }

  // con doble barra hacemos comentarios

  """ def string() : Unit ={
    val concatenar = "Hola" ++ "que tal?"
    val str = ""
  """


  def string(texto1: String, texto2: String): String = {
    val concatenar = texto1 ++ " " ++ texto2
    concatenar
    // tambien podria simplificarse el codigo de esta manera
    // """  def string(texto1: String, texto2:String): String = texto1 ++ texto2 """
  }

  def string(): Unit = {
    val str = "Hola, mundo, soy David"
    val longitud = str.length
    val separacionComa = str.split(",")

    val entero = 10
    val intToString = entero.toString.charAt(0)
    println()
  }

  def cuadrado(numero: Int): Int = {
    val result = numero * numero
    result
  }
  // Habria que crear antes la funcion suma para poder aplicarse en la suma de cuadrados
  """
  def sumacuadrados( num1: Double,num2: Double): Double = suma(square(num1),square(num2))

  """

  def condicionante(): Unit = {
    val n = 2
    if (n == 2) {
      print("N es 2")
    } else {
      print("N no es 2")
    }

    val value = 3

    val res = value match {
      case 1 => "Octubre"
      case 2 => "Noviembre"
      case 3 => "Diciembre" //podria ser _ en este punto para else , cualquier otro caso
    }
    print(res)
  }

  case class monstruo(nombre: String, brazos: Int)

  val kraken = monstruo("kraken", 13)

  // ejemplo de PATERN MATCHING
  kraken match {
    case monstruo("kraken", 13) => println("Si es el maldito kraken")
    case _ => println("no te conozco monstruo")

  }

  def identificar(m: monstruo): String = m match {
    case monstruo("kraken", 13) => "Si es el maldito kraken"
    case _ => "no te conozco monstruo"
  }

val mapa = Map(1 ->"Enero",2 ->"Febrero")

  def ejecutarLista(): Unit = {
    val m = List(1, 2, 3, 4, 5, 6)
    val lMap = m.map(x => x * 2)
    println(lMap) // o usa un breakpoint aquí
  }



}'
'''

#Codigo de Test 
''' 
package sesion1

import org.scalatest.FlatSpec
import org.scalatest.Matchers.convertToAnyShouldWrapper
import sesion1.Sesion1.{condicionante, cuadrado, ejecutarLista, identificar, imprimir, monstruo, string}

class Sesion1Test  extends  FlatSpec {

  "Primer Test" should "cualquier cosa" in{
    imprimir("hola")
  }

  "Segundo Test" should "unir dos string" in{
    string("hola","que tal?") shouldBe "hola que tal?"
  }

  // el codigo siguiente nos permite debugear/testar las funciones del archivo scala y obtener los resultados dentro de string
  "String" should "" in{
    string()
  }
 "comprobacion de numeros" should "" in{
   cuadrado(2) shouldBe(4)
 }

  "comprobacion de cuadrados" should "" in{
    cuadrado(3) shouldBe(9)
  }


  "Pater" should "" in{
    condicionante()
  }


  // no nos vale por que es una class no una funcion
  """  "Monstruo" should "" in{
    monstruo("Ciclope") shouldBe "no te conozco monstruo" """

// el correcto seria , definiendo previamente la funcion identificar
  "Monstruo" should "devolver mensaje si no es conocido" in {
    identificar(monstruo("Ciclope", 1)) shouldBe "no te conozco monstruo"
  }

  ejecutarLista()
}
''' 
