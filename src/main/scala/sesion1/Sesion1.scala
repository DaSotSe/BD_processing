package sesion1

import scala.::

object Sesion1 {

  /**
   * Funcion que dado un parametro lo escribe
   * @param texto es de tipo String y sera impreso por consola
   */
  def imprimir(texto: String): Unit = {
    println(texto)
  }

  def tiposBasicos(): Unit = {
    val entero1: Int = 12
    val buleano: Boolean = true
    val double = 2.2
    val suma = 1 + entero1
    val texto = "hola que tal "

    val textoConSalto =
      """Hola que tal
        |Yo estoy bien
        |y tu que?
        |""".stripMargin
  }

  //comentario
  /**
   * Comentario con salto
   */


  def concatenar(texto1:String, texto2:String): String = texto1 ++ texto2

  def string(): Unit = {
    val str = "Hola,mundo,soy ruben"
    val longitud = str.length
    val separarPorComa = str.split(",")

    val entero = 10
    val intToString = entero.toString.charAt(0)

    val uperCase = str.toUpperCase()
    val lowerCase = str.toLowerCase()
    println()
  }


  def square(i: Double): Double = i * i

  def suma(i:Double,j:Double): Double = i + j

  def sumaCuadrados(i:Double,j:Double) = suma(square(i), square(j))



  def condiciones(): Unit = {
    val n = 2
    if( n == 2) print("N es 2")
    else print("N no es 2")

    if((n == 1 || n == 2) && n > 0){
      print("N es 2")
    }else{
      if(n>2){
      }
    }

    println(if(n>2) "es mayor" else "no lo es")

    val value = 3

    val res = value match{
      case 1 => "Enero"
      case 2 => "Febrero"
      case _ => "Es otro"
    }
    println(res)

    case class Persona(nombre: String, apellido: String, edad:Int = 33) {
      val fullName = s"$nombre/$apellido"
    }

    val ruben = Persona("Ruben", "Gutierrez")
    ruben.fullName

    ruben match {
      case Persona("Ruben",_,_) => println("si es ruben")
      case _ => println("no es ruben")
    }

    println()

  }
  case class Persona(nombre: String, edad:Int)
  def comprobarPersona(persona:Persona): Boolean = {
    true
  }

  def coleccions(): Unit = {

    val s = Set(1,1,2,2,3,4)

   val p = List(Persona("Ruben", 20), Persona("Carmen", 22), Persona("Maria", 20), Persona("Sofia", 20))

   val p2 = p:::List(Persona("juan", 12))

   val tublas = List((1,2),(2,3), (3,2))

   val l: Seq[Int] = List(1,2,3,4,5,6,1)

   val filtro = l.filter{ x =>
     val condi = x > 3 & x<6 & x !=4
     condi
   }



   val lMap = l.map(x=> x+1)

   l.foreach(x => println(x-1))



   val mapa: Map[Int, (String, String, Int)] = Map(1 -> ("Enero", "frio", 3),  2 -> ("Febrero", "frio", 3))



    case class MesTiempo(mes:String, tiempo: Option[String])
    val mapa2 = Map(1 -> MesTiempo("Enero",  Option("frio")),
                    2 -> MesTiempo("Febrero", None))

   val resMapa = mapa2.filter(_._2.tiempo == Option("frio"))


   val set = Set(1,2,3,1)

   val aumentar = set + 3
   val concatenar = set ++ Set(4,5,6)

   val eliminacion = set - 3
   val diferencia = set -- Set(1,3)

   val union = set union Set(4,5,6)
   val diferencia2 = set diff Set(1,3)
   val interseccion = set & Set(3)


    println()

 }



}
