package models

open class Producto(
    var id: Int,
    var nombre: String,
    var precio: Int
){
    var ingredientes : ArrayList<Ingrediente> = ArrayList()

    fun addIngrediente(ingrediente : Ingrediente){
        ingredientes.add(ingrediente)
    }

    fun addIngredientes(ingColl : Collection<Ingrediente>){
        ingredientes.addAll(ingColl)
    }
    override fun toString(): String {
        return "Producto(id=$id, nombre='$nombre', precio=$precio, ingredientes=$ingredientes)"
    }

}