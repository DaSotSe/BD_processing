package Sesion1

import Sesion1.PruebaMain1.{Astring, condicionante, ejecutarLista, filtrar, funcionesString, identificar, imprimir, imprimirLongitud, monstruo, multiplicar, sumacuadrados}
import org.scalatest.FlatSpec
import org.scalatest.Matchers.convertToAnyShouldWrapper

class PruebaMain1Test extends FlatSpec {

  "Primer Test" should "cualquier cosa" in {
    imprimir("Que tal")
  }

  "Segundo Test" should "devolver longitud texto" in {
    val resultado = imprimirLongitud("que tal")
    resultado shouldBe (7)
  }
  "Tercer test" should "concatena strings" in {
    val resultado = Astring("hola", " como mola")
    resultado shouldBe ("hola como mola")

  }
  "Cuarto test" should "" in {
    funcionesString()
  }
  "quinto test" should "multiplicaciones" in {
    val resultado = multiplicar(3, 5)
    resultado shouldBe (15)
  }
  "Sexto test" should "multiplicar cuadrados" in {
    val resultado = sumacuadrados(2.0, 3.0)
    resultado shouldBe (36.00)
  }

  "Septimo test" should "condicionante1" in {
    val resultado = condicionante(2)
    resultado shouldBe ("N es 2")
  }

  "Octavo test" should "reconocer un monstruo" in {
    identificar(monstruo("kraken", 14)) shouldBe "no te conozco monstruo"
    //considera todos los atributos definidos para la class, de manera que ante un valor incorrecto notifica que no es
  }


  "Noveno test" should "recorrer una lista y realizar una transformacion" in {
    val resultado = ejecutarLista(List(2, 5, 8, 5, 1))
    resultado shouldBe List(4, 10, 16, 10, 2)
  }

  "Decimo test" should "Probar filtrado" in {
    val resultado = filtrar(List(5,1,10,15,2))
    resultado shouldBe  List(10,15)
  }



}
