package view

import controllers.KFCController
import models.Ingrediente
import utils.ReadData

object KFCView {
    var KFCtrl: KFCController = KFCController


    fun showIngredientes() {
        println(KFCtrl.getAllIngredientes())
    }

    fun showProductos() {
        println(KFCtrl.getAllProductos())
    }

    fun showIngrediente(ingrediente: Ingrediente) {
        println(ingrediente)
    }
    fun menuUpdateProducto() : Int{
        var opt : Int = 0
        do{
            println("1. Cambiar nombre")
            println("2. Cambiar ingredientes")
            opt = ReadData.readNumber()
        }while(opt !in 1..2)
        return opt
    }

    fun menuUpdateIngredientes() : Int{
        var opt : Int = 0
        do{
            println("1. Añadir ingredientes")
            println("2. Eliminar ingredientes")
            opt = ReadData.readNumber()
        }while(opt !in 1..2)
        return opt
    }
    fun menu(): Int {
        var opt: Int = 0
        do {
            println("1. Añadir producto")
            println("2. Añadir Ingrediente")
            println("3. Modificar Producto")
            println("4. Modificar Ingrediente")
            println("5. Eliminar producto")
            println("6. Elminiar ingrediente")
            println("7.Mostrar productos")
            println("8.Mostrar ingredientes")
            println("9. Salir")
            opt = ReadData.readNumber()
        } while (opt !in 0..9)
        return opt
    }
}
