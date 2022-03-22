package models

open class Producto(
    var nombre: String,
    var precio: Int
) {
    var ingredientes: ArrayList<Ingrediente> = ArrayList()

    fun addIngrediente(ingrediente: Ingrediente) {
        if (containsIngrediente(ingrediente)) {
            println("INGREDIENTE YA EXISTE EN PRODUCTO")
        } else {
            ingredientes.add(ingrediente)
        }

    }

    fun addIngrediente(ingColl: List<Ingrediente?>?) {
        if (ingColl != null) {
            for (ingrediente in ingColl) {
                if (!containsIngrediente(ingrediente!!))
                    addIngrediente(ingrediente)
            }
        }
    }

    fun showIngredientes() {
        println(ingredientes)
    }

    fun containsIngrediente(ingrediente: Ingrediente): Boolean {
        return ingredientes.contains(ingrediente)
    }

    fun changeName(newName: String) {
        nombre = newName
    }

    fun deleteIngrediente(ingrediente: Ingrediente) {
        ingredientes.remove(ingrediente)
    }

    fun deleteIngrediente(ingList: List<Ingrediente?>?): List<Ingrediente>? {
        if (ingList != null) {
            for (ingrediente in ingList) {
                if (this.containsIngrediente(ingrediente!!)) {
                    ingredientes.remove(ingrediente)
                } else {
                    println("EL PRODUCTO NO CONTIENE $ingrediente")
                    return null
                }
            }
        } else
            return null
        return ingredientes
    }

    override fun toString(): String {
        return "Producto(nombre='$nombre', precio=$precio, ingredientes=$ingredientes)"
    }

}