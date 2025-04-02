""" package sesion3

import org.scalatest.FlatSpec

class sesion32Test extends FlatSpec {{

  package sesion3

  import org.scalatest.FlatSpec
  import org.scalatest.Matchers.convertToAnyShouldWrapper
  import sesion3.Sesion3._


  class Sesion3Test extends FlatSpec{

    "extraeMail" should "cualquier cosa" in {
      val texto =
        """Hola mi nombres es Ruben.
          |Tengo 30 y mi email es
          |este: ruben@gmail.es
          |""".stripMargin
      extraeMail(texto) shouldBe Some("ruben@gmail.es")"""
    }


}
  package sesion3