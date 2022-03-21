package repositories

import exceptions.EItemNotFound
import models.Ingrediente
import resources.ICRUDRepository

class IngredientesRepository(override var size: Int) : ICRUDRepository<Ingrediente>, ArrayList<Ingrediente>() {
    override fun findAll(): List<Ingrediente?>? {
        return this;
    }

    fun findAll(ids : List<String>): List<Ingrediente?>? {
        val lista = mutableListOf<Ingrediente>()
        var aux : Ingrediente?
        for(i in ids){
            if(findById(i.toInt()) != null){
                aux = findById(i.toInt())
                lista.add(aux!!)
            }else{
                return null
            }
        }
        return lista
    }

    override fun findById(id: Int): Ingrediente? {
        for (ingrediente in this) {
            if (ingrediente.id == id)
                return ingrediente
        }
        throw  EItemNotFound("ELEMENTO NO ENCONTRADO")
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
                this.set(ingrediente, entity)
                return this.get(ingrediente)
            }
        }
        return null
    }


    override fun deleteById(id: Int): Ingrediente? {
        val ingrediente = findById(id)
        if (ingrediente != null) {

            this.remove(ingrediente)
            size--
            return ingrediente
        }
        return null
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