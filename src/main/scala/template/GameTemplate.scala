package template


class Creature(var attack: Int, var health: Int)


// Template
abstract class GameTemplate
          (creatures: Array[Creature]) {

  def combat(creature1: Int, creature2: Int): Int

  protected def hit(attacker: Creature, other: Creature): Unit

  def start

  def gameOver = "Game is over. Exiting"

}

class CardDamageGame(var creatures: Array[Creature]) extends GameTemplate(creatures) {

  override def combat(creature1: Int, creature2: Int): Int = {
    val first = creatures(creature1)
    val second = creatures(creature2)
    hit(first, second)
    hit(second, first)
    val firstAlive = first.health > 0
    val secondAlive = second.health > 0
    if (firstAlive == secondAlive) return -1
    if (firstAlive) creature1
    else creature2
  }


  override protected def hit(attacker: Creature, other: Creature): Unit = {
    val oldHealth = other.health
    other.health -= attacker.attack
    if (other.health > 0) other.health = oldHealth
  }

  override def start = "Game started. Player put into the new Brave World"
}

class UnfairGame(var creatures: Array[Creature]) extends GameTemplate(creatures) {

  override def combat(creature1: Int, creature2: Int): Int = {
    return -1
  }

  override protected def hit(attacker: Creature, other: Creature): Unit = {
    other.health -= attacker.attack
  }

  override def start = "Game started. Player put into the another world"

}


object Main extends App {

  val c1 = new Creature(1, 2)
  val c2 = new Creature(1, 2)
  val game = new CardDamageGame(Array[Creature](c1, c2))
  game.combat(0, 1)
  game.combat(0, 1)

}