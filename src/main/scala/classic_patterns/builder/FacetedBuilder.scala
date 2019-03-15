package classic_patterns.builder


class Person { // address
  var streetAddress: String = null
  var postcode: String = null
  var city: String = null
  // employment
  var companyName: String = null
  var position: String = null
  var annualIncome = 0

  override def toString: String = "Person{" + "streetAddress='" + streetAddress + '\'' + ", postcode='" + postcode + '\'' + ", city='" + city + '\'' + ", companyName='" + companyName + '\'' + ", position='" + position + '\'' + ", annualIncome=" + annualIncome + '}'
}

// builder facade
// provides common interface for builders
class PersonBuilder { // the object we're going to build
  protected val person = new Person // reference!

  def works = new PersonJobBuilder(person)

  def lives = new PersonAddressBuilder(person)

  def build: Person = person
}

class PersonAddressBuilder(override val person: Person) extends PersonBuilder {

  def at(streetAddress: String): PersonAddressBuilder = {
    person.streetAddress = streetAddress
    this
  }

  def withPostcode(postcode: String): PersonAddressBuilder = {
    person.postcode = postcode
    this
  }

  def in(city: String): PersonAddressBuilder = {
    person.city = city
    this
  }
}

class PersonJobBuilder(override val person: Person) extends PersonBuilder {

  def at(companyName: String): PersonJobBuilder = {
    person.companyName = companyName
    this
  }

  def asA(position: String): PersonJobBuilder = {
    person.position = position
    this
  }

  def earning(annualIncome: Int): PersonJobBuilder = {
    person.annualIncome = annualIncome
    this
  }
}

object BuilderFacetsDemo {
  def main(args: Array[String]): Unit = {
    var pb = new PersonBuilder
    val person = pb.lives
      .at("Berliner Strasse 22")
      .in("hamburg").withPostcode("03409")
      .works.at("HamburgerDev")
      .asA("Engineer")
      .earning(123000)
      .build
    System.out.println(person)
  }
}