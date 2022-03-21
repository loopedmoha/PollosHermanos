package exceptions

class EIncorrectValue(message : String) : Exception(message) {
    fun message(){
        println(message)
    }
}