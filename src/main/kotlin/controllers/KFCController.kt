package controllers

import factories.Factory
import models.*
import models.enums.TipoProducto
import repositories.IngredientesRepository
import repositories.ProductoRepository
import view.KFCView

object KFCController {
    var ingredientesRepo: IngredientesRepository = IngredientesRepository(0)
    var productosRepo: ProductoRepository = ProductoRepository(0)


    fun getAllIngredientes(): List<Ingrediente?>? {
        return ingredientesRepo.findAll()
    }
    fun init() {

        ingredientesRepo.insert(Factory.create(1, "pollo"))
        ingredientesRepo.insert(Factory.create(2, "pan"))
        ingredientesRepo.insert(Factory.create(3, "lechuga"))
        ingredientesRepo.insert(Factory.create(4, "tomate"))
        ingredientesRepo.insert(Factory.create(5, "patata"))
        ingredientesRepo.insert(Factory.create(6, "leche"))
        ingredientesRepo.insert(Factory.create(7, "chocolate"))

        productosRepo.insert(Factory.create( TipoProducto.PRINCIPAL,1, "Hamburguesa", 5) )
        productosRepo.insert(Factory.create(TipoProducto.COMPLEMENTO, 2, "patatas fritas", 2))

        var producto : Principal = productosRepo.findByName("Hamburguesa") as Principal
        ingredientesRepo.findById(1)?.let { producto.addIngrediente(it) }
        ingredientesRepo.findById(2)?.let { producto.addIngrediente(it) }
        productosRepo.update(producto)

    }

    fun pollosHermanos(){
        var opt : Int = 0
        do{
            opt = KFCView.menu()
            when(opt){
                1 -> addProducto()
                2 -> addIngrediente()
                3 -> updateProducto()
                4 -> updateIngrediente()
                5 -> deleteProducto()
                6 -> deleteIngrediente()

            }
        }while(opt!=7)
    }

    private fun deleteProducto() {
        TODO("Not yet implemented")
    }

    private fun updateIngrediente() {
        TODO("Not yet implemented")
    }

    private fun updateProducto() {
        TODO("Not yet implemented")
    }

    private fun deleteIngrediente() {
        TODO("Not yet implemented")
    }

    private fun addIngrediente() {
        TODO("Not yet implemented")
    }

    private fun addProducto() {
        TODO("Not yet implemented")
    }

    fun getAllProductos(): List<Producto?>? {
        return productosRepo.findAll()

    }

}