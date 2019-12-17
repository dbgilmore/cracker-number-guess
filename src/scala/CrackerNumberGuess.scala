package scala

import scala.io.StdIn.{readInt, readLine}

object CrackerNumberGuess {
  def main(args: Array[String]): Unit = {
    val cards = initialiseCards

    println("Please pick a number between 1 and 6 inclusive and enter it: ")
    val initialCard = readInt()
    println(s"Thanks!  You have selected card $initialCard.  Here it is.")
    printCard(cards(initialCard - 1))
    println(s"Please pick a number from card $initialCard")

    for(i <- 5 to 1 by -1) {
      print(s"$i... ")
      Thread.sleep(1*1000)
    }
    println()

    println("Please tell me which of the following cards your number also appears on. (1 - 5 inclusive, comma seperated)")
    val pickedCardRemoved = cards.patch(initialCard - 1, Nil, 1)
    pickedCardRemoved.foreach(printCard)

    val alsoAppearsOn = readLine.replace(" ", "").split(",").map(x => x.toInt).toSeq

    val remainingCards = alsoAppearsOn.map(card => pickedCardRemoved(card - 1))

    val guess = remainingCards.map(card => card.head.head).sum + cards(initialCard - 1).head.head
    println(s"Your number is $guess")

  }

  def printCard(card: Seq[Seq[Int]]): Unit = {
    card.foreach(row => {
      row.foreach(value => {
        val str = if (value < 10) s" $value" else value
        print(s" $str ")
      })
      println()
    })
    println
  }

  def initialiseCards: Seq[Seq[Seq[Int]]] = {
    // card1 initialisation
    val card1 = Seq(
      Seq( 1,  3,  5,  7,  9, 11, 13, 15),
      Seq(17, 19, 21, 23, 25, 27, 29, 31),
      Seq(33, 35, 37, 39, 41, 43, 45, 47),
      Seq(49, 51, 53, 55, 57, 59, 61, 63)
    )
    // card2 initialisation
    val card2 = Seq(
      Seq( 2,  3,  6,  7, 10, 11, 14, 15),
      Seq(18, 19, 22, 23, 26, 27, 30, 31),
      Seq(34, 35, 38, 39, 42, 43, 46, 47),
      Seq(50, 51, 54, 55, 58, 59, 62, 63)
    )

    // card3 initialisation
    val card3 = Seq(
      Seq( 4,  5,  6,  7, 12, 13, 14, 15),
      Seq(20, 21, 22, 23, 28, 29, 30, 31),
      Seq(36, 37, 38, 39, 44, 45, 46, 47),
      Seq(52, 53, 54, 55, 60, 61, 62, 63)
    )

    // card4 initialisation
    val card4 = Seq(
      Seq( 8,  9, 10, 11, 12, 13, 14, 15),
      Seq(24, 25, 26, 27, 28, 29, 30, 31),
      Seq(40, 41, 42, 43, 44, 45, 46, 47),
      Seq(56, 57, 58, 59, 60, 61, 62, 63)
    )

    // card5 initialisation
    val card5 = Seq(
      Seq(16, 17, 18, 19, 20, 21, 22, 23),
      Seq(24, 25, 26, 27, 28, 29, 30, 31),
      Seq(48, 49, 50, 51, 52, 53, 54, 55),
      Seq(56, 57, 58, 59, 60, 61, 62, 63)
    )

    // card6 initialisation
    val card6 = Seq(
      Seq(32, 33, 34, 35, 36, 37, 38, 39),
      Seq(40, 41, 42, 43, 44, 45, 46, 47),
      Seq(48, 49, 50, 51, 52, 53, 54, 55),
      Seq(56, 57, 58, 59, 60, 61, 62, 63)
    )

    card1 :: card2 :: card3 :: card4 :: card5 :: card6 :: Nil
  }
}
