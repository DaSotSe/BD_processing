package sesion2

import java.util.concurrent.locks.Condition

object Sesion2 {
  case class Whatsapp(Id:Int,telefono:String, grupo:List[String] =List(), nick: Option[String])
  case class Teleop(Id:Int,telefono:String, fijo: Option[String])



  def f(option: Option[Int]) = option match {
    case Some(n) if n > 2 => println(s"f:El valor es mayor que 2 y es $n")
    case _ => println("f:El valor es no hay nada")
    case Some(n) if n < 2 => println(s"f:El valor es menor que 2 y es $n")
  }

  def f2(option: Option[Int]) = println(s"f2:El valor es ${option.getOrElse("Nada")}")

  case class Persona(nombre: String, edad: Option[Int])

  def filtrarPersonas(listaPersonas: List[Persona]): List[Persona] = listaPersonas.filter(_.edad.getOrElse(1) >= 32)

  def filtrarPersonas2(listaPersonas: List[Persona]): List[Persona] = listaPersonas
    .filter(persona => persona.edad.getOrElse(1) >= 32 & persona.nombre.charAt(0).toString == "R")


  val lista: Seq[List[Int]] = List(List(1))
  val listaFlat: Seq[Int] = lista.flatten


  def funcionesDeClase(): Unit = {

    class Box[T](value: T) { def getValue: T = value}

    val intBox = new Box(12)

    val stringBox = new Box("Hola")
    val boolBox = new Box(true)

    println(intBox.getValue)
    println(stringBox.getValue)

  }

  val r = Persona("r", Option(1))
  val p = r.copy(nombre = "r")

  def ultimoElemento[S](ls: List[S]): S = ???


}
