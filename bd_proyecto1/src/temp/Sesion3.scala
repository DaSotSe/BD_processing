object Sesion3 {
// ejercicios de la sesion 2

  //Busqueda del ultimo elemento de list
  def ultimo[A](ls : List[A]): A = ls.last

  //Busqueda del penultimo
  def penultimo[A](ls : List[A]): A = ls(ls.lenght-2)

// busqueda del elemento N, preparado para excepcion cuando buscamos es una posicion fuera del rango ( en este caso ponemos -1)
// Revisar este ejercicio
  def nultimo[A](n: Int, ls : List[A]): A = n match {
    case value if value <= ls.length -1 && value =>
    case _ => throw new  NoSuchElementException
  }
  //version alternativa
  def penultimo[A](ls : List[A]): A = if (ls.isEmpty) throw new

  //Ejercicio 4
  def longitud

  //Ejercicio5

  def invertir [A](ls; List[A]): List[A] = ls match {
    case Nil => Nil
    case head :: tail =>
      val result = invertir(tail) :+head
      result
  }

  //Ejercicio6

  def palindrome[A](ls: List[A]): Boolean = ls == invertir(ls)


//Ejercicio7



}
 // Ejercicio8

def extraemail(texto: String) : Option[String] = {
  val regex = "([a-z]@([a-z]+)\\.(com|es)".r
  val res = regex.findFirstIn(texto)
}

def codificar[A](ls: List[A], num: Int=1): List[(Int, A)] = ls match {
  case Nil => Nil
  case head :: Nil => List((num, head))
  case head :: body :: Nil if head == body => List((num + 1, head))
  case head :: body :: Nil if head != body => (num, head) :: codificar(body :: Nil)
  case head :: body :: tail if head != body => (num, head) :: codificar(body :: tail)
  case head :: body :: tail if head == body => codificar(body :: tail, num + 1)
}