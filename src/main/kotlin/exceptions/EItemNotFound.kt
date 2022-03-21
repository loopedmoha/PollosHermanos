package exceptions

class EItemNotFound(message : String): Exception(){
    fun message(){
        println("ELEMENTO NO ENCONTRADO")
    }
}