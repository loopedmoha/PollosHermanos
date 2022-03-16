package repositories

import models.Producto
import resources.ICRUDRepository
import java.util.*

class MenuRepository : ICRUDRepository<Producto>, TreeMap<String, Producto>() {
    /**
     * Encuentra todos los elementos del repositorio
     */
    override fun findAll(): List<Producto?>? {
        return ArrayList(this.values)
    }

    /**
     * Inserta un elemento
     */
    override fun insert(entity: Producto?): Producto? {
        if (entity != null) {
            this.put(entity.nombre, entity)
            return entity
        }
        return null
    }

    /**
     * Actualiza un elemento
     */
    override fun update(entity: Producto?): Producto? {
        var prod: Producto? = this.get(entity!!.nombre)
        if (prod != null) {
            put(prod.nombre, prod)
            return prod
        }
        return null
    }

    /**
     * DEPRECATED
     */
    override fun findById(id: Int): Producto? {
        return null
    }

    /**
     * Busca un elemento por su nombre
     */
    fun findByName(nombre: String): Producto? {
        val prod: Producto? = this.get(nombre)

        if (prod != null)
            return prod
        return null;
    }

    /**
     * Elimina un elemento buscando por ID
     */
    override fun deleteById(id: Int): Producto? {
        return null
    }

    fun deleteByName(name: String): Producto? {
        return this.remove(name)
    }

    /**
     * Elimina un elemento
     */
    override fun delete(entity: Producto?): Producto? {

        var prod: Producto? = null
        if (entity != null)
            prod = get(entity.nombre)
        if (prod != null) {
            remove(prod.nombre)
            return prod
        }
        return null
    }

}