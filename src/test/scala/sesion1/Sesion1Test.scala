package sesion1

import org.scalatest.FlatSpec
import org.scalatest.Matchers.convertToAnyShouldWrapper
import sesion1.Sesion1._

class Sesion1Test extends FlatSpec{

  "Primer Test" should "cualquier cosa" in{
    imprimir("hola")
  }

  "Concatenar" should "dado dos string concatena" in{
    concatenar("hola", "que tal?") shouldBe "holaque tal?"
    concatenar("hola", "hola") shouldBe "holahola"
  }

  "String" should "" in{
    string()
  }

  "Funciones" should "vemos elementos basicos" in {
    square(2) shouldBe 4
    square(2.2) shouldBe 4
    square("2".toDouble) shouldBe 4

    suma(1,2) shouldBe 3
  }

  "Funciones" should "Ejercicio de suma de cuadrados" in {
    //HACER EN CLASE
    sumaCuadrados(1,2) shouldBe 5
    sumaCuadrados(2,2) shouldBe 8
  }

  "Pater" should "" in {
    coleccions
  }



}
