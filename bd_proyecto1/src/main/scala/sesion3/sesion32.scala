package sesion3

object sesion32 {
  def extraeMail(texto: String): List[String] = {
    val regex = "@([0-9A-Za-z_.-]+)@([a-z]\\.(com|es))".r

    val result= regex.findFirstIn(texto).toList
    result
  }





}
