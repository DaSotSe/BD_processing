package Sesion2_1


object sesion2_1 {

  def imprimirLongitud2(texto: String): Int = {
    print(texto) //no es necesaria esta linea de codigo
    texto.length
  }

  case class MesTiempo(mes: String, tiempo: String)

  val mapa2 = Map(1 -> MesTiempo("Enero", "frio"),
    2 -> MesTiempo("Febrero", "frio"))

  def filtramapa(nombreMes: String): String = {
    val result = mapa2.values.find(_.mes == nombreMes)
    result.map(_.tiempo).getOrElse("No encontrado")
  }


  val datos: List[(Int, MesTiempo)] = List(
    (1, MesTiempo("Enero", "frio")),
    (2, MesTiempo("Febrero", "frio")),
    (3, MesTiempo("Marzo", "templado")),
    (7, MesTiempo("Julio", "calor"))
  )

  def buscarMesYClima(nombreMes: String, clima: String): List[(Int, MesTiempo)] = {
    datos.filter { case (_, mt) => mt.mes == nombreMes && mt.tiempo == clima }
  }

  def f(option: Option[Int]) = option match {
    case Some(n) if n > 2 => println(s"f:El valor es mayor que 2 y es $n")
    case _ => println("f:El valor es no hay nada")
  }

  case class Persona(nombre: String, edad: Option[Int])

  def filtrapersona(listaPersonas: List[Persona]): List[Persona] = listaPersonas.filter(_.edad.getOrElse(1) >= 32)


  def filtraPersonas2(listaPersonas: List[Persona]): List[Persona] = listaPersonas
    .filter(persona => persona.edad.getOrElse(1) >= 32 & persona.nombre.charAt(0).toString == "R")

// reutilizacion de una misma clase al emplear tipos genericos , de esta manera una misma funcion nos vale para varios tipos
  def funcionesDeClase(): Unit = {

    class Box[T](value: T) { def getValue: T = value}

    val intBox = new Box(12)

    val stringBox = new Box("Hola")
    val boolBox = new Box(true)

    println(intBox.getValue)
    println(stringBox.getValue)

  }
// Creamos una funcion generica con un tipo generico para ser empleado con Int y String
  val r = Persona("r", Option(1))
  val p = r.copy(nombre = "r")

  def ultimoElemento[S](ls: List[S]): S = ls.last // Tambien se puede utilizar ls(ls.lenght-1), nos da el ultimo elemento de la lista ls

  val lista2:Seq[Int] = 1::2::Nil
  val lista3: Seq[Int] = Seq(1,2)
  val b: Boolean = List() == Nil
  val b2: Boolean = 1::2::Nil == Seq(1,2)



  def sumList(ls: List[Int]): Int = ls match {
    case Nil => 0
    case head :: tail =>
      val r = head + sumList(tail)
      r
  }


}