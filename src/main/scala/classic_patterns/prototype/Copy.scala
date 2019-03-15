package classic_patterns.prototype

import org.apache.commons.lang3.SerializationUtils

class Address(var streetAddress: String, var city: String, var country: String) {
  def this(other: Address) {
    this(other.streetAddress, other.city, other.country)
  }

  override def toString: String = "Address{" + "streetAddress='" + streetAddress + '\'' + ", city='" + city + '\'' + ", country='" + country + '\'' + '}'
}

class Employee {
  var name: String = null
  var address: Address = null

  def this(name: String, address: Address) {
    this()
    this.name = name
    this.address = address
  }

  //Deep Copy constructor
  //Might be difficult to implement for object with many attributes

  def this(other: Employee) {
    this()
    name = other.name
    address = new Address(other.address)
  }

  override def toString: String = "Employee{" + "name='" + name + '\'' + ", address=" + address + '}'
}

class BetterEmployee extends java.io.Serializable {
  var name: String = null
  var address: Address = null

  def this(name: String, address: Address) {
    this()
    this.name = name
    this.address = address
  }

  override def toString: String = "Employee{" + "name='" + name + '\'' + ", address=" + address + '}'
}

object Main extends App{

  val alice = new Employee("Alice", new Address("Hamburger Strasse 22", "Hamburg", "Germany"))
  val bob = new Employee(alice)
  bob.name = "Bob"
  //Should be different
  println(alice)
  println(bob)

  //Copy via Serialization
  val chris = new BetterEmployee("Chris", new Address("Berliner Strasse 11", "Berlin", "Germany"))
  val dan = SerializationUtils.roundtrip(chris)
  dan.name = "Dan"
  //Should be different
  println(chris)
  println(dan)
}
