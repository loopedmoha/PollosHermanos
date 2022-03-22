package repositories

import exceptions.EItemNotFound
import models.Ingrediente
import resources.ICRUDRepository

class IngredientesRepository(override var size: Int) : ICRUDRepository<Ingrediente>, ArrayList<Ingrediente>() {
    override fun findAll(): List<Ingrediente?> {
        return this;
    }

    fun findAll(ids: List<String>): List<Ingrediente?> {
        val lista = mutableListOf<Ingrediente>()
        var aux: Ingrediente?
        for (i in ids) {
            aux = findById(i.toInt())
            if (aux != null) {
                lista.add(aux)
            }
        }
        return lista
    }

    override fun findById(id: Int): Ingrediente? {
        for (ingrediente in this) {
            if (ingrediente.id == id)
                return ingrediente
        }
        return null
    }

    override fun insert(entity: Ingrediente?): Ingrediente? {
        if (entity != null) {
            add(entity)
            size++
        }
        return null
    }

    override fun update(entity: Ingrediente?): Ingrediente? {
        val ingrediente = this.indexOf(entity)
        if (ingrediente >= 0) {
            if (entity != null) {
                this[ingrediente] = entity
                return this[ingrediente]
            }
        }
        return null
    }


    override fun deleteById(id: Int): Ingrediente? {
        return try {
            val ingrediente = findById(id)
            this.remove(ingrediente)
            size--
            ingrediente
        } catch (e: EItemNotFound) {
            println(e.message())
            null
        }


    }

    override fun delete(entity: Ingrediente?): Ingrediente? {
        if (entity != null) {
            remove(entity)
            size--
            return entity;
        }
        return null
    }

}