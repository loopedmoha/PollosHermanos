package controllers

import exceptions.EIncorrectValue
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
import view.KFCView.showIngredientes
import view.KFCView.showProductos

object KFCController {
    private var ingredientesRepo: IngredientesRepository = IngredientesRepository(0)
    private var productosRepo: ProductoRepository = ProductoRepository(0)

    /**
     * Recupera todos los ingredientes del repositorio
     * @return List<Ingrediente?>
     */
    fun getAllIngredientes(): List<Ingrediente?> {
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

        val producto: Principal = productosRepo.findByName("Hamburguesa") as Principal
        ingredientesRepo.findById(1)?.let { producto.addIngrediente(it) }
        ingredientesRepo.findById(2)?.let { producto.addIngrediente(it) }
        productosRepo.update(producto)

    }

    /**
     * Funcion principal del programa
     */
    fun pollosHermanos() {
        var opt = 0
        do {
            opt = KFCView.menu()
            when (opt) {
                1 -> addProducto()
                2 -> addIngrediente()
                3 -> updateProducto()
                4 -> updateIngrediente()
                5 -> deleteProducto()
                6 -> deleteIngrediente()
                7 -> showProductos()
                8 -> showIngredientes()
                else -> opt = 9
            }
        } while (opt != 9)
    }

    /**
     * Elimina un producto del repositorio
     * @return Producto? null si el producto no existe. El producto si se elimina
     */
    fun deleteProducto(): Producto? {
        println("Introduce nombre de producto:")
        val name: String = ReadData.readString()
        if (productosRepo.findByName(name) != null) {
            return productosRepo.deleteByName(name)
        } else
            println("Producto no $name existe.")
        return null

    }

    /**
     * Elimina un ingrediente del repositorio
     * @return Ingrediente? null si no existe, el ingrediente si se elimina
     */
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

    /**
     * Actualiza los datos de un ingrediente
     * @return Ingrediente? null si el ingrediente no existe,
     * el ingrediente si se modifica
     */
    fun updateIngrediente(): Ingrediente? {
        
        val id: Int =ReadData.readNumber()
        return if (ingredientesRepo.findById(id) != null) {
            println("Introduce nuevo nombre:")
            val newNombre = ReadData.readString()
            val aux = Ingrediente(id, newNombre)
            ingredientesRepo.update(aux)
            aux
        } else {
            println("Ingrediente con id $id no encontrado")
            null
        }
    }

    /**
     * DEPRECATED
     * @param regexNum Regex
     * @return Int
     */
    private fun idPattern(regexNum: Regex): Int {
        var id1: String
        do {
            println("Introduce id de ingrediente:")
            id1 = ReadData.readString()
        } while (!id1.matches(regexNum))
        return id1.toInt()
    }

    /**
     * Actualiza un producto
     * @return Producto? null si algo falla, el producto si se modifica
     */
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

    /**
     * elimina un ingrediente
     * @param aux Producto
     */
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
        aux.deleteIngrediente(ingredientes)

    }

    /**
     * A??ade un conjunto de ingredientes al producto aux
     * @param aux Producto
     */
    private fun addingrediente(aux: Producto) {
        println("Elige los ingredientes a a??adir poniendo sus ID separados por espacios:")
        println(ingredientesRepo.findAll())
        var ids: List<String> = listOf()
        try {
            ids = ReadData.readString("^[1-8]( [1-8])*\$".toRegex()).split(' ')
        } catch (e: EItemNotFound) {
        }
        val ingredientes = listOfIngredientes(ids)
        println("Vas a a??adir los ingredientes:\n $ingredientes")
        aux.addIngrediente(ingredientes)
    }

    private fun listOfIngredientes(ids: List<String>): List<Ingrediente?> {
        return ingredientesRepo.findAll(ids)
    }


    fun addIngrediente() {
        var id: String
        println("Ingredientes actuales: \n ${ingredientesRepo.findAll()}")
        println("Introduce el nombre del nuevo ingrediente:")
        val name: String = ReadData.readString()
        do {
            println("Introduce id (numero entero) del nuevo ingrediente:")
            try {
                id = ReadData.readString("\\d+".toRegex())
            } catch (e: EIncorrectValue) {
                id = ""
                println(e.message())
            }
        } while (id == "")
        if (ingredientesRepo.findById(id.toInt()) != null) {
            println("YA EXISTE UN INGREDIENTE CON ESE ID")
        } else {
            val aux = Ingrediente(id.toInt(), name)
            ingredientesRepo.insert(aux)
        }


    }

    fun addProducto(): Producto? {
        println("Introduce el nombre del nuevo producto:")
        val name = ReadData.readString().uppercase()
        val aux: Producto?

        if (productosRepo.findByName(name) != null) {
            println("Producto con el nombre $name ya existe.")
            return null
        } else {
            println("Introduce un precio para el producto:")
            var precio: Int
            do {
                precio = ReadData.readNumber()
            } while (precio == -1)

            aux = Producto(name, precio)
            addingrediente(aux)
            return productosRepo.insert(aux)

        }
    }

    fun getAllProductos(): List<Producto?>? {
        return productosRepo.findAll()

    }

}