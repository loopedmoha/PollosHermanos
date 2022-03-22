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
        return when (tipo) {
            TipoProducto.BEBIDA -> Bebida(nombre, precio)
            TipoProducto.COMPLEMENTO -> Complemento(nombre, precio)
            TipoProducto.POSTRE -> Postre(nombre, precio)
            TipoProducto.PRINCIPAL -> Principal( nombre, precio)

        }
        return null
    }

    fun create(id: Int, nombre: String): Ingrediente {
        return Ingrediente(id, nombre)
    }
}