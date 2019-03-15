package classic_patterns.singleton

class Singleton
object Singleton {

  //double check locking
  @volatile private var uniqueInstance = new Singleton
  def getInstance(): Singleton = synchronized {return uniqueInstance}

}

class LazySingleton
object LazySingleton {

  private var uniqueInstance: Option[Singleton] = None

  def getInstance(): Singleton = synchronized {
    if (uniqueInstance == None) uniqueInstance = Some(new Singleton)
    return uniqueInstance.get
  }

}

object Main extends App{

  class Person
  val person = new Person
  val newPerson = new Person

  val singleInstance = Singleton.getInstance()
  val secondInstance = Singleton.getInstance()

  if (secondInstance != singleInstance) throw new RuntimeException

  println(singleInstance)
  println(secondInstance)
  println(person)
  println(newPerson)
}