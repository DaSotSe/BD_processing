package Sesion2_1

import Sesion1.PruebaMain1.imprimirLongitud
import Sesion2_1.sesion2_1.{MesTiempo, Persona, buscarMesYClima, f, filtraPersonas2, filtramapa, filtrapersona, funcionesDeClase, imprimirLongitud2, sumList, ultimoElemento}
import org.scalatest.Matchers.convertToAnyShouldWrapper
import org.scalatest.{FlatSpec, Matchers}

class sesion2_1Test extends FlatSpec {
// Para que podamos crear un Scala.test desde la interface tenemos que importar las librerias de build y ademas excribir algun codigo de test
  "Primer Test" should "devolver longitud texto" in {
    val resultado = imprimirLongitud2("que tal")
    resultado shouldBe (7)
  }

  "Segundo test" should "Filtra en un map generado de una class" in {
    val resultado = filtramapa("Febrero")
    resultado shouldBe ("frio")
  }

  "Tercer test Filtro con case class" should "encontrar mes frio" in {
    val resultado = buscarMesYClima("Febrero", "frio")
    resultado shouldBe List((2, MesTiempo("Febrero", "frio")))
  }

    " Cuarto Test" should "cualquier cosa" in {
      val option = Option(12)
      f(option)
    }

    "Quinto Test" should " Que la persona tenga mas de 32 años y su nombre empiece por R" in {
      val lista = List(Persona("Ruben", Some(33)),
        Persona("Luisa", Some(21)),
        Persona("Robin", Some(12)),
        Persona("Paco", Some(40)),
        Persona("Carmen", Some(53)))

      filtrapersona(lista) shouldBe List(Persona("Ruben", Some(33)), Persona("Paco", Some(40)), Persona("Carmen", Some(53)))
    }


  "Filtro Personas Nombre" should "Devuelve una lista de personas mayores que 32 y empiece por R" in{
    val lista = List(Persona("Ruben", Some(33)),
      Persona("Luisa", Some(21)),
      Persona("Robin", Some(40)),
      Persona("Paco", Some(40)),
      Persona("Carmen", Some(53)))

    filtraPersonas2(lista) shouldBe List(Persona("Ruben", Some(33)), Persona("Robin", Some(40)))
  }

  "Test Funciones De Clase" should "prueba" in {
    funcionesDeClase()
  }

  "Ultimo elemento de la lista" should "prueba" in {
    val lista = List(1,2,3,4)
    ultimoElemento(lista) shouldBe 4

    ultimoElemento(List("a", "b", "c")) shouldBe "c"
  }

  "Recursividad" should "Test" in{
    sumList(List(1,1,1,3,4)) shouldBe 10

  }


}
