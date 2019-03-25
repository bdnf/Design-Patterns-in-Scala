package scala_patterns

case class Address(street: String, city: String)
case class Person(name: String, addr: Address)

trait toJSON {
  def json(level: Int = 0): String
  val INDENTATION = "  "
  def indentation(level: Int = 0): (String, String) =
    (INDENTATION*level, INDENTATION*(level+1))
}

object JsonImplicits {

  implicit class AddressToJSON(address: Address) extends toJSON {
    def json(level: Int = 0): String = {
      val (out, in) = indentation(level)
      s"""{
         |${in}"street": "${address.street}",
         |${in}"city": "${address.city}"
         |$out}""".stripMargin
    }
  }

  implicit class PersonToJSON(person: Person) extends toJSON {
    def json(level: Int = 0): String = {
      val (out, in) = indentation(level)
      s"""{
         |${in}"name": "${person.name}",
         |${in}"address": "${person.addr.json(level+1)}"
         |$out}""".stripMargin
    }
  }

}

object TypeClassPattern extends App {

  import JsonImplicits._

  val a  = Address("1 Hacker Way", "Menlo Park")
  val p = Person("Mark Zueckerberg", a)

  println(a.json())
  println(p.json())

  import scala.sys.process._

  print("docker images".!)
}




