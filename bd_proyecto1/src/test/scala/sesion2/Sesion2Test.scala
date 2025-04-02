package sesion2

class Sesion2Test {

}
// Falta por incorporar la dependencia con las librerias scalatest.FlatSpec,sesion1.Sesion1.imprimir...
// Como lo hace por que parece que trae la dependencia del test de sesion1 ??????Mirar el minuto 40-45
"Filtro Personas" should "Lista de personas mayor que 32"
val lista = List("Ruben", Some(33))...



def funcionesDeClase(): Unit = {

  class Box[T](value: T) {
    def get: T = value
  }

  val intBox = new Box
  val stringBox = new Box[String] ("Hola")

  println((intBox.get)
    println(string.Box.get)

}