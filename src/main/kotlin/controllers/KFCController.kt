package controllers

import exceptions.EItemNotFound
import factories.Factory
import models.Ingrediente
import models.Principal
import models.Producto
import models.enums.TipoProducto
import repositories.IngredientesRepository
import repositories.ProductoRepository
import utils.ReadData
import view.KFCView

object KFCController {
    private var ingredientesRepo: IngredientesRepository = IngredientesRepository(0)
    private var productosRepo: ProductoRepository = ProductoRepository(0)


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

        productosRepo.insert(Factory.create(TipoProducto.PRINCIPAL, 1, "Hamburguesa", 5))
        productosRepo.insert(Factory.create(TipoProducto.COMPLEMENTO, 2, "patatas fritas", 2))

        var producto: Principal = productosRepo.findByName("Hamburguesa") as Principal
        ingredientesRepo.findById(1)?.let { producto.addIngrediente(it) }
        ingredientesRepo.findById(2)?.let { producto.addIngrediente(it) }
        productosRepo.update(producto)

    }

    fun pollosHermanos() {
        var opt: Int = 0
        do {
            opt = KFCView.menu()
            when (opt) {
                1 -> addProducto()
                2 -> addIngrediente()
                3 -> updateProducto()
                4 -> updateIngrediente()
                5 -> deleteProducto()
                6 -> deleteIngrediente()

            }
        } while (opt != 7)
    }

    fun deleteProducto(): Producto? {
        println("Introduce nombre de producto:")
        var name: String = ReadData.readString()
        if (productosRepo.findByName(name) != null) {
            return productosRepo.deleteByName(name)
        } else
            println("Producto no $name existe.")
        return null

    }

    fun deleteIngrediente(): Ingrediente? {

        val regexNum = Regex("\\d+")
        var name: String
        do {
            println("Introduce nombre de ingrediente o id:")
            name = ReadData.readString()
        } while (!name.matches(regexNum))

        return if (ingredientesRepo.findById(name.toInt()) != null) {
            ingredientesRepo.deleteById(name.toInt())
        } else {
            println("Ingrediente $name no encontrado")
            null
        }
    }

    fun updateIngrediente(): Ingrediente? {
        val regexNum = Regex("\\d+")
        val id: Int = idPattern(regexNum)
        return if (ingredientesRepo.findById(id) != null) {
            println("Introduce nuevo nombre:")
            val newNombre = ReadData.readString()
            var aux = Ingrediente(id, newNombre)
            ingredientesRepo.update(aux)
            aux
        } else {
            println("Ingrediente con id $id no encontrado")
            null
        }
    }

    private fun idPattern(regexNum: Regex): Int {
        var id1: String
        do {
            println("Introduce id de ingrediente:")
            id1 = ReadData.readString()
        } while (!id1.matches(regexNum))
        return id1.toInt()
    }

    fun updateProducto(): Producto? {
        println("Introduce nombre de producto a modificar:")
        val name = ReadData.readString()
        if (productosRepo.findByName(name) == null)
            return null
        else {
            val aux = productosRepo.findByName(name)
            when (KFCView.menuUpdateProducto()) {
                1 -> {
                    println("Introduce nuevo nombre:")
                    val newName = ReadData.readString()
                    if (aux != null) {
                        aux.changeName(newName)
                        productosRepo.deleteByName(name)
                        productosRepo.insert(aux)
                    }
                }
                2 -> {
                    when (KFCView.menuUpdateIngredientes()) {
                        1 -> addingrediente(aux!!)
                        2 -> removeIngrediente(aux!!)

                    }
                }
            }

        }
        return null
    }

    private fun removeIngrediente(aux: Producto) {
        println("El producto lleva los siguientes ingredientes:")
        aux.showIngredientes()
        println("Elige los ingredientes a eliminar poniendo sus ID separados por espacios:")
        var ids: List<String> = listOf()
        try {
            ids = ReadData.readString("^[1-8]( [1-8])*\$".toRegex()).split(' ')
        } catch (e: EItemNotFound) {
        }
        val ingredientes = listOfIngredientes(ids)
        println("Vas a eliminar los ingredientes:\n $ingredientes")


    }

    private fun addingrediente(aux: Producto) {
        println("Elige los ingredientes a añadir poniendo sus ID separados por espacios:")
        println(ingredientesRepo.findAll())
        var ids: List<String> = listOf()
        try {
            ids = ReadData.readString("^[1-8]( [1-8])*\$".toRegex()).split(' ')
        } catch (e: EItemNotFound) {
        }
        val ingredientes = listOfIngredientes(ids)
        println("Vas a añadir los ingredientes:\n $ingredientes")
        aux.addIngrediente(ingredientes)
    }

    private fun listOfIngredientes(ids: List<String>): List<Ingrediente?>? {
        val ingredientes = ingredientesRepo.findAll(ids)
        return ingredientes
    }


    fun addIngrediente() {
        TODO("Not yet implemented")
    }

    fun addProducto() {
        TODO("Not yet implemented")
    }

    fun getAllProductos(): List<Producto?>? {
        return productosRepo.findAll()

    }

}