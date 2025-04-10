package Sesion1


object PruebaMain1 {

  def imprimir(texto: String): Unit = {
    print(texto)
  }


  def imprimirLongitud(texto: String): Int = {
    print(texto) //no es necesaria esta linea de codigo
    texto.length
  }

  def Astring(texto1: String, texto2: String): String = {
    val concatenar = texto1 + texto2
    concatenar
  }

  def funcionesString(): Unit = {
    val str = "Esto esta muy guapooo"
    val longitud = str.length
    val separar = str.split(",")

    println()
  }

  def multiplicar(num: Int, num2: Int): Int = {
    val cuadrado = num * num2
    cuadrado
  }

  def sumacuadrados(num1: Double, num2: Double): Double = {
    val cuadrado1 = num1 * num1
    val cuadrado2 = num2 * num2
    val resultado = cuadrado1 * cuadrado2
    resultado
  }

  def condicionante(num: Int) : String = {
    val resultado = if (num == 2) {
      "N es 2"}
      else {
      "N no es 2"}
    println(resultado)
    resultado
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


  def ejecutarLista(m: List[Int]): List[Int] = {
    val lMap = m.map(x => x * 2)
    lMap // o usa un breakpoint aquí

  }

  def filtrar(m : List[Int]) : List[Int] = {
    val filtroM = m.filter { x=>
      val cond = x > 5
      cond
    }
    filtroM
  }


}
