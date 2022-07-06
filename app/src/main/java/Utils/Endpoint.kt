package Utils

enum class Endpoint(service : String) {
    JOURNALS("/journals.php"),
    ISSUES("/issues.php"),
    PUBS("/pubs.php");

    private val baseUrl = "https://revistas.uteq.edu.ec/ws"
    val path : String = baseUrl + service
}