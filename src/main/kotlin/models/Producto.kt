package models

open class Producto(var nombre: String, var precio: Int) {
    var ingredientes: ArrayList<Ingrediente> = ArrayList()

    /**
     * Añade un ingrediente a producto
     * @param ingrediente Ingrediente
     */
    fun addIngrediente(ingrediente: Ingrediente) {
        if (containsIngrediente(ingrediente)) {
            println("INGREDIENTE YA EXISTE EN PRODUCTO")
        } else {
            ingredientes.add(ingrediente)
        }

    }

    /**
     * Añade una lista de ingredientes a producto
     * @param ingColl List<Ingrediente?>?
     */
    fun addIngrediente(ingColl: List<Ingrediente?>?) {
        if (ingColl != null) {
            for (ingrediente in ingColl) {
                if (!containsIngrediente(ingrediente!!))
                    addIngrediente(ingrediente)
            }
        }
    }

    /**
     * Muestra los ingredientes de producto
     */
    fun showIngredientes() {
        println(ingredientes)
    }

    /**
     * Comprueba si producto tiene el ingrediente
     * @param ingrediente Ingrediente
     * @return Boolean true si lo contiene
     */
    fun containsIngrediente(ingrediente: Ingrediente): Boolean {
        return ingredientes.contains(ingrediente)
    }

    /**
     * Cambia el nombre de producto
     * @param newName String
     */
    fun changeName(newName: String) {
        nombre = newName
    }

    /**
     * Elimina un ingrediente de producto
     * @param ingrediente Ingrediente
     * @return ingrediente El ingrediente eliminado. Null si no existe
     */
    fun deleteIngrediente(ingrediente: Ingrediente): Ingrediente? {
        if (ingredientes.contains(ingrediente))
            ingredientes.remove(ingrediente)
        else return null
        return ingrediente
    }

    /**
     * Elimina una lista de ingredientes de producto
     * @param ingList List<Ingrediente?>?
     * @return List<Ingrediente>? null si el producto no contiene algún ingrediente
     */
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