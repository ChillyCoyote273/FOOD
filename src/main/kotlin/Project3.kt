data class Card(val suit: String, val value: String) {
    private val valueIndex
        get() = values.indexOf(value)

    companion object {
        @JvmStatic
        val suits = arrayOf("♥", "◆", "♣", "♠")
        @JvmStatic
        val values = arrayOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    }

    override fun toString() = suit + value

    fun sameSuit(other: Card) = suit == other.suit
    fun sameValue(other: Card) = value == other.value
    fun greaterThan(other: Card) = valueIndex > other.valueIndex
    fun lesserThan(other: Card) = valueIndex < other.valueIndex
}

open class Deck(val cards: ArrayList<Card> = ArrayList(List(52) {
    Card(Card.suits[it / 13], Card.values[it % 13])
})) {
    override fun toString() = "[${cards.joinToString(", ")}]"
    fun shuffle() {
        cards.shuffle()
    }
    fun length() = cards.size
    fun addCard(card: Card) {
        cards.add(card)
    }
    fun addCards(cards: ArrayList<Card>) {
        this.cards.addAll(cards)
    }
    fun drawCard() = cards.removeFirst()
    fun drawCards(numCards: Int): ArrayList<Card> {
        val drawn: ArrayList<Card> = arrayListOf()
        repeat(numCards) {
            drawn.add(cards.removeFirst())
        }
        return drawn
    }
    fun deal(numHands: Int): Array<Hand> {
        val hands = Array(numHands) {
            Hand("Player ${it + 1}")
        }
        repeat(cards.size) {
            hands[it % hands.size].addCard(drawCard())
        }
        return hands
    }
}

class Hand(val name: String, cards: ArrayList<Card> = arrayListOf()) : Deck(cards) {
    fun take(cards: List<Card>) {
        this.cards.addAll(cards)
    }
}

class War(
    var numTurn: Int = 0,
    val players: Array<Hand> = Deck().apply { shuffle() }.deal(2),
    var winner: String = "") {
    fun turn() {
        val cards = MutableList(2) {
            players[it].drawCard()
        }
        while (true) {
            if (cards[cards.size - 2].greaterThan(cards[cards.size - 1])) {
                players[0].take(cards)
                break
            }
            if (cards[cards.size - 1].greaterThan(cards[cards.size - 2])) {
                players[1].take(cards)
                break
            }
            repeat(2) {
                if (players[it].length() < 2) {
                    winner = players[1 - it].name
                    return
                }
            }
            repeat(2) {
                players.forEach {
                    cards.add(it.drawCard())
                }
            }
        }
        repeat(2) {
            if (players[it].length() == 0) {
                winner = players[1 - it].name
                return
            }
        }
    }

    fun play() {
        val turns = mutableListOf<String>()
        repeat(100_000) {
            numTurn = it
            if (winner != "") {
//                println("$winner won on round $it")
                return
            }
            val deckState = players[0].toString() + players[1].toString()
            if (turns.contains(deckState)) {
//                println("Cycle detected on turn $it")
                winner = "Tie"
                return
            }
            turns.add(deckState)
            turn()
        }
//        println("Game went past 100,000 turns")
        winner = "Unknown"
    }
}