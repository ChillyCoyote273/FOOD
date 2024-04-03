

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val cards = arrayListOf(
        Card("#", "3"),
        Card("%", "2"),
        Card("@", "5"),
    )
    val deck = Deck()
    deck.addCards(cards)
    println(deck)
    deck.addCard(Card("&", "0"))
    println(deck)
//    var wins = 0
//    var losses = 0
//    var ties = 0
//    var unknown = 0
//    repeat(1000) {
//        val war = War()
//        war.play()
//        if (war.winner == "Player 1")
//            wins++
//        else if (war.winner == "Player 2")
//            losses++
//        else if (war.winner == "Tie")
//            ties++
//        else
//            unknown++
//    }
//    println("Wins: $wins\nLosses: $losses\nTies: $ties\nUnknown: $unknown")
}