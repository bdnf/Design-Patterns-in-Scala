package classic_pattern_scala_way

//classes and case classes support default parameters
case class PersonBuilder( var streetAddress: String = null,
                          var postcode: String = null,
                          var city: String = null,
                          // employment
                          var companyName: String = null,
                          var position: String = null,
                          var annualIncome: Int = 0
                          )

case class BetterPersonBuilder( var streetAddress: Option[String] = None,
                                var postcode: Option[String] = None,
                                var city: Option[String] = None,
                                // employment
                                var companyName: Option[String] = None,
                                var position: Option[String] = None,
                                var annualIncome: Int = 0
                              )

object Main extends App {

  var pb = new PersonBuilder(
    streetAddress = "Berliner Strasse 22",
    city = "Hamburg",
    companyName = "HamburgerDev",
    position = "Engineer",
    annualIncome = 123000
  )
  println(pb)

  var pbBetter = new BetterPersonBuilder(
    streetAddress = Some("Berliner Strasse 22"),
    city = Some("Hamburg"),
    companyName = Some("HamburgerDev"),
    position = Some("Engineer"),
    annualIncome = 123000
  )
  println(pbBetter)

}
