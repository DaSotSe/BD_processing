package sesion2

import org.scalatest.FlatSpec
import sesion1.Sesion1.imprimir
import org.scalatest.Matchers.convertToAnyShouldWrapper
import sesion2.Sesion2._

class Sesion2Test extends FlatSpec{
  "Primer Test" should "cualquier cosa" in{
    val option = Option(1)
    f(option)
    f2(option)
  }

  "Filtro Personas" should "Devuelve una lista de personas mayores que 32" in{
    val lista = List(Persona("Ruben", Some(33)),
      Persona("Luisa", Some(21)),
      Persona("Robin", Some(12)),
      Persona("Paco", Some(40)),
      Persona("Carmen", Some(53)))

    filtrarPersonas(lista) shouldBe List(Persona("Ruben", Some(33)), Persona("Paco", Some(40)), Persona("Carmen", Some(53)))
  }

  "Filtro Personas Nombre" should "Devuelve una lista de personas mayores que 32 y empiece por R" in{
    val lista = List(Persona("Ruben", Some(33)),
      Persona("Luisa", Some(21)),
      Persona("Robin", Some(40)),
      Persona("Paco", Some(40)),
      Persona("Carmen", Some(53)))

    filtrarPersonas2(lista) shouldBe List(Persona("Ruben", Some(33)), Persona("Robin", Some(40)))
  }

  "Test Funciones De Clase" should "prueba" in {
    funcionesDeClase()
  }
  "Ultimo elemento de la lista" should "prueba" in {
    val lista = List(1,2,3,4)
    ultimoElemento(lista) shouldBe 4

    ultimoElemento(List("a", "b", "c") shouldBe "c"
  }
}
