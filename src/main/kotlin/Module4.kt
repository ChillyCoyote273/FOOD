import kotlin.math.PI

open class Prism(val height: Double, val baseArea: Double) {
    val volume
        get() = height * baseArea
}

class RectangularPrism(val length: Double, val width: Double, height: Double) : Prism(height, length * width) {
    val surfaceArea
        get() = 2 * baseArea + basePerimeter * height
    val basePerimeter
        get() = 2 * (length + width)
}

class TriangularPrism(val base: Double, val side1: Double, val side2: Double, height: Double, val triHeight: Double)
    : Prism(height, 0.5 * base * triHeight) {
    val surfaceArea
        get() = 2 * baseArea + basePerimeter * height
    val basePerimeter
        get() = side1 + side2 + base
}

class Cylinder(val radius: Double, height: Double) : Prism(height, PI * radius * radius) {
    val surfaceArea
        get() = 2 * baseArea + basePerimeter * height
    val basePerimeter
        get() = PI * 2 * radius
}

open class LibraryItem(val title: String, val author: String, val year: Int) {
    var isAvailable = true
        private set
    fun checkOut() {
        isAvailable = false
    }
    fun checkIn() {
        isAvailable = true
    }
    open fun displayInfo() {
        println("Title: $title\nAuthor: $author\nYear: $year\nAvailable: ${if (isAvailable) "Yes" else "No"}")
    }
    fun printAvailabilityStatus() {
        println("$title is ${if (isAvailable) "available" else "not available"}")
    }
}

class Book(title: String, author: String, year: Int, val pageCount: Int) : LibraryItem(title, author, year) {
    fun bookmark(page: Int) {
        println("Adding bookmark to page $page.")
    }
    override fun displayInfo() {
        super.displayInfo()
        println("Type: Book\nPage Count: $pageCount")
    }
}

class Audiobook(title: String, author: String, year: Int, val duration: Double) : LibraryItem(title, author, year) {
    fun bookmark(hour: Double) {
        println("Adding bookmark at hour $hour.")
    }
    override fun displayInfo() {
        super.displayInfo()
        println("Type: Audiobook\nDuration (hours): $duration")
    }
}

class Ebook(title: String, author: String, year: Int, val fileSize: Double) : LibraryItem(title, author, year) {
    fun highlight() {
        println("Highlighting passage of text.")
    }
    override fun displayInfo() {
        super.displayInfo()
        println("Type: Ebook\nFile Size (MB): $fileSize")
    }
}