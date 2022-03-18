package factories

import models.*
import models.enums.TipoProducto

object  Factory {

    fun getType(element: Any): String {
        if (element is Bebida)
            return "BEBIDA"
        if (element is Complemento)
            return "COMPLEMENTO"
        if (element is Postre)
            return "POSTRE"
        if (element is Principal)
            return "PRINCIPAL"
        if (element is Ingrediente)
            return "INGREDIENTE"
        return ""
    }


    fun create(
        tipo : TipoProducto,
        id: Int,
        nombre: String,
        precio: Int
    ): Producto? {
        when (tipo) {
            TipoProducto.BEBIDA -> return Bebida(id, nombre, precio)
            TipoProducto.COMPLEMENTO -> return Complemento(id, nombre, precio)
            TipoProducto.POSTRE -> return Postre(id, nombre, precio)
            TipoProducto.PRINCIPAL -> return Principal(id, nombre, precio)

        }
        return null
    }

    fun create(id: Int, nombre: String): Ingrediente {
        return Ingrediente(id, nombre)
    }
}