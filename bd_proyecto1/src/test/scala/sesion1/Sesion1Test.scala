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
