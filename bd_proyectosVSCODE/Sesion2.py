#Codigo de la segunda sesion de Main , faltan ejercicios que estan al principio de la sesion 3
''' 
object Sesion2 {

  case class whastsapp(Id:Int,telefono:String,grupo:List[String]=List(),nick: Option[String])
  case class Teleop(Id:Int,telefono:String,fijo:Option[String])

  def f(option :Option[Int]) : Unit = option match {
    case Some(n) if n > 2 => println(s"f:El valor es mayor que 2 y es $n")
    case _ => println("f:El valor es no hay nada")
    case Some(n) if n < 2 => println(s"f:el valor es menor que 2 y es $n")
  }
  def f2(option: Option[Int]): Unit = println((s"f2:El valor es ${option.getOrElse("Nada")}"))

  case class Persona(nombre:String,edad: Option[Int])

  //Filtramos en un listado de personas que son de clase persona con la estructura de arriba
  //La lista viene la inyectamos en el Sesion2Test
  //filtramos la lista de persona , con clase persona, llamando a cada persona y entrando en ella para que recogaja con get el atributo get y me de los mas > 32
 //La variacion en vez de get seria getorelse(0) con lo que nos daria en caso de que no tuviese un valor en ese atributo
  //variacion incluyendo que la primera letra del nombre sea R, hay que modificar el test para que el should sea lo que queremos
  def filtrarPersonas(listaPersonas: List[Persona]): List[Persona] = {
    ListaPersonas.filter{persona =>
      persona.edad.get >32 & persona.nombre.charAt(0) == 'R'}
  }

}

def funcionesDeClase(): Unit = {

  // T es un valor generico para poder incluir un valor que queramos en funcion de un tipo que declaramos despues (int, string, boolean...)
 // Con esto lo que buscamos es reutilizar el codigo, creamos la clase Box¡
  // hay que adaptar lo que viene despues del get (p.E get: Int = value.toString.toInt ) para poder decirle que es un int o un string
// el class es diferente al case class , class no crea instancias por eso hay que hacer los new box , hay algunas funciones que no estan definidas en class
  class Box[T](value: T) { def get: T = value }
  // Ponemos corchetes en el valor generico, tambien podemos poner any en vez de T

  // en este primer caso hacemos un filtro
  def fFilter[T](l: List[T], condi: T =>Boolean): List[T] = l.filter(condi)

  // hacemos pruebas dandole valor con int y con string y funcionan ambos
  //nos vale par poder utilizar la clase Box
  val intBox = new Box [Int] (12)
  val stringBox = new Box[String] ("Hola")

  println((intBox.get)
    println(string.Box.get)
// obtener el ultimo elemento de uns lista de una lista , empleando un generico que es S
def ultimoElemento[S](ls: List[S]): S = ls(ls.length -1)
//alternativo mas optimizado con la funcion last
  def ultimoElemento[S](ls: List[S]): S = ls.last

  //Ejemplo de recursividad
  def sumlist(ls: List[Int]): Int = ls match {
    case Nil => 0
    case head :: tail => head + sumList(tail)

    // Ejercicios de la sesion 2case _: scala.collection.immutable.Nil.type => ???
    case _: scala.collection.immutable.::[_] => ???
  }'
  ''
  '''

#Codigo de Test  .Falta mucho codigo por revisar , sobre todo de los ultimos ejercicios de la clase

''' 
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


'''