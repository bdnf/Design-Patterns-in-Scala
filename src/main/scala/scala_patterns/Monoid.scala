package scala_patterns

trait Monoid[T] {
  def op(l: T, r: T): T
  def zero: T
}

object MonoidOperations {
  def foldL[T](list: List[T], m: Monoid[T]): T = list.foldLeft(m.zero)(m.op)
  def foldR[T](list: List[T], m: Monoid[T]): T = list.foldRight(m.zero)(m.op)

  //for Example 2
  def mapMerge[K, V](a: Monoid[V]): Monoid[Map[K, V]] =
    new Monoid[Map[K, V]] {
      override def zero: Map[K, V] = Map()

      override def op(l: Map[K, V], r: Map[K, V]): Map[K, V] =
        (l.keySet ++ r.keySet).foldLeft(zero) {
          case (res, key) => res.updated(key, a.op(l.getOrElse(key,
            a.zero), r.getOrElse(key, a.zero)))
        }
    }

  def balancedFold[T, Y](list: IndexedSeq[T], m: Monoid[Y])(f: T => Y): Y =
    if (list.length == 0) {
      m.zero
    } else if (list.length == 1) {
      f(list(0))
    } else {
      val (left, right) = list.splitAt(list.length / 2)
      m.op(balancedFold(left, m)(f), balancedFold(right, m)(f))
    }
}

object Monoid extends App {

  val intAddition = new Monoid[Int] {
    val zero = 0
    override def op(l: Int, r: Int): Int = l+r
  }

  val intMultiplication = new Monoid[Int] {
    val zero = 1
    override def op(l: Int, r: Int): Int = l*r
  }

  val stringConcatenation: Monoid[String] = new Monoid[String] {
    val zero: String = ""
    override def op(l: String, r: String): String = l + r
  }

  assert(intMultiplication.op(1,2) == 2)
  assert(intAddition.op(1,2) == 3)

  val strings = List("This is ", "a list of ", "strings!")
  val numbers = List(1, 2, 3, 4, 5, 6)
  val foldL = strings.foldLeft(stringConcatenation.zero)(stringConcatenation.op)
  val foldR = strings.foldRight(stringConcatenation.zero)(stringConcatenation.op)
  val numbersFactorial = numbers.foldLeft(intMultiplication.zero)(intMultiplication.op)

  assert(foldL == "This is a list of strings!")
  assert(foldR == "This is a list of strings!")
  assert(numbersFactorial == 720)

  assert(MonoidOperations.foldL(strings, stringConcatenation) == "This is a list of strings!")
  assert(MonoidOperations.foldR(strings, stringConcatenation) == "This is a list of strings!")
  assert(MonoidOperations.foldL(numbers, intMultiplication) == 720)

  //Example 2
  val features = Array("Example", "of", "non-monoidic", "implementation", "of", "aggregation")

  val wordCounter1 = features.foldLeft(Map[String, Int]()) {
    case (res, feature) => res.updated(feature,
      res.getOrElse(feature, 0) + 1)
  }

  val featuresM = Array("Example", "of", "monoidic", "implementation", "of", "aggregation")
  val counterMonoid: Monoid[Map[String, Int]] = MonoidOperations.mapMerge(intAddition)
  val wordCounter2 = MonoidOperations.balancedFold(features, counterMonoid)(i => Map(i -> 1))

  assert(wordCounter1 == wordCounter2)
}
