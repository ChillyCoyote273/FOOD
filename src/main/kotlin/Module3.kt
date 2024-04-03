import java.lang.Math.hypot
import java.lang.Math.random
import java.lang.String.format

data class Point(var x: Int = 0, var y: Int = 0) {
    val distanceFromOrigin
        get() = getDistance(Point())
    override fun toString() = "($x,$y)"

    fun getDistance(other: Point) = hypot((x - other.x).toDouble(), (y - other.y).toDouble())
}

data class Cuboid @JvmOverloads constructor(val length: Int, val width: Int = length, val height: Int = length) {
    val surfaceArea
        get() = (length * width + width * height + height * length) * 2.0
    val volume
        get() = (length * width * height).toDouble()
    override fun toString() = "Cuboid(l,w,h)=($length,$width,$height)"
}

data class Ghost @JvmOverloads constructor(
    val red:   Int = (random() * 256).toInt(),
    val green: Int = (random() * 256).toInt(),
    val blue:  Int = (random() * 256).toInt(),
) {
    val bgColor = computeBG()
    val RGB
        get() = intArrayOf(red, green, blue)
    val hexCode
        get() = format("#%02x%02x%02x", red, green, blue)

    private fun computeBG() = if (0.2126 * red + 0.7152 * green + 0.0722 * blue > 127.0) { 0 } else { 255 }
    fun getBGColor() = bgColor
    override fun toString() =
        "\u001b[2J\u001b[H\u001b[48;2;$bgColor;$bgColor;${bgColor}m\u001b[38;2;$red;$green;${blue}m$image\u001b[0m"

    companion object {
        @JvmStatic
        val image = """
                            ████████████████
                        ████░░░░░░░░░░░░░░████
                      ██░░░░              ░░░░██
                    ██░░                      ░░██
                  ██░░                          ░░██
                ██░░                              ░░██
                ██░░                              ░░██
              ██░░            ████          ████  ░░░░██
              ██░░            ████          ████    ░░██
              ██░░            ████          ████    ░░██
              ██░░        ░░░░                  ░░░░░░██
              ██░░        ░░░░        ████      ░░░░░░██
              ██░░                                  ░░████
              ██░░                                    ░░░░██
              ██░░                                      ░░██
      ██    ██░░                                    ░░░░░░██
    ██░░████░░                                    ░░████████
    ██░░░░░░                                    ░░██
    ██░░                                        ░░██
    ██░░░░                                    ░░██
      ████░░                                ░░██
          ██░░░░                        ░░░░██
            ████░░░░░░░░░░░░░░░░░░░░░░░░████
              ██████████████████████████
              """.trimIndent() + "\n"
    }
}

class Product(var name: String, var price: Double, var quantity: Int) {
    val totalCost
        get() = price * quantity

    override fun toString() = "Product{name='$name', price=$price, quantity=$quantity}"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (name != other.name) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + price.hashCode()
        return result
    }
//    override fun equals(other: Any?) = other is Product && price == other.price && name == other.name

}