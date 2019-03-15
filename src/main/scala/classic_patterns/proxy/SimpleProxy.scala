package classic_patterns.proxy

// Goal: changing object behaviour, but using existing interface

class UnresponsiblePerson(var age: Int) {

  def drink: String = "drinking"

  def drive: String = "driving"

  def drinkAndDrive: String = "driving while drunk"
}

class ResponsiblePerson(var person: UnresponsiblePerson) {
  def setAge(value: Int): Unit = {
    person.age = value
  }

  def drink: String = if (person.age >= 18) person.drink
  else "too young"

  def drive: String = if (person.age >= 16) person.drive
  else "too young"

  def drinkAndDrive = "dead"
}
