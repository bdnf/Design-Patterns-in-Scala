package factories

abstract class Item {
  def prepare() = "preparing"
  def cook() = "cooking"
  def box() = "boxing"
}

abstract class SimpleStore {

  def orderItem(itemType: String): Item = {

    val burger: Item = createItem(itemType)

    burger.prepare()
    burger.cook()
    burger.box()

    return burger
  }

  //factory method to implement
  def createItem(itemType: String): Item

}

//Example usage
abstract class DefaultBurgerStore extends SimpleStore {

  // with anonumous classes for simplicity
  override def createItem(itemType: String): Item = {
                                      //anonymous class
    if (itemType == "cheese") return new Burger("DefaultCheeseburger", "tastySauce", new Bun){
      override def cook(): String = super.cook()
    }
                                        //anonymous class
    else if (itemType == "ham") return new Burger("DefaultHamburger", "simpleSauce", new Bun){
      override def cook(): String = super.cook()
    }
    else return new Bun
  }

  def orderBurger(itemType: String): Item
}

// can implement Item's methods
class Bun extends Item

abstract class Burger(var name:String, var sauce: String, bun: Bun) extends Item {
  override def cook(): String = s"cooking $name"
}


class BigBoyBurgerStore extends DefaultBurgerStore {


    override def createItem(itemType: String):Burger = {
      if (itemType == "cheese") return new BigBoyCheeseburger("BigBoyCheeseburger", "tastySauce", new Bun)
      else if (itemType == "ham") return new BigBoyHamburger("BigBoyHamburger", "simpleSauce", new Bun)
      else return new Burger("simpleBurger", "simpleSauce", new Bun){
        override def box(): String = super.box()
      }
    }
  override def orderBurger(itemType: String):Burger = createItem(itemType)
}
// can implement Item's methods
class BigBoyCheeseburger(name:String, sauce: String, bun: Bun) extends Burger(name:String, sauce: String, bun: Bun) {
  override def cook(): String = s"cooking Big Boy $name"
}
class BigBoyHamburger(name:String, sauce: String, bun: Bun) extends Burger(name:String, sauce: String, bun: Bun) {
  override def cook(): String = s"cooking Big Boy $name"
}

object Main extends App {

  val BigBoyBurgerStore = new BigBoyBurgerStore
  val cheeseburger: Burger = BigBoyBurgerStore.createItem("cheese")
  print(cheeseburger.name)

}

