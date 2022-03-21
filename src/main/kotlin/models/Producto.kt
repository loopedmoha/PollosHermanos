package models

open class Producto(
    var id: Int,
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

    fun deleteIngrediente(ingredientes: List<Ingrediente>) {
TODO()
    }

    override fun toString(): String {
        return "Producto(id=$id, nombre='$nombre', precio=$precio, ingredientes=$ingredientes)"
    }

}