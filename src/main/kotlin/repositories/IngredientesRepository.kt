package repositories

import models.Ingrediente
import resources.ICRUDRepository

class IngredientesRepository(override val size: Int) : ICRUDRepository<Ingrediente>, ArrayList<Ingrediente>() {
    override fun findAll(): List<Ingrediente?>? {
        return this;
    }

    override fun findById(id: Int): Ingrediente? {
        for (ingrediente in this) {
            if (ingrediente.id == id)
                return ingrediente
        }
        return null;
    }

    override fun insert(entity: Ingrediente?): Ingrediente? {
        if (entity != null) {
            add(entity)
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
            return ingrediente
        }
        return null
    }

    override fun delete(entity: Ingrediente?): Ingrediente? {
        if (entity != null) {
            remove(entity)
            return entity;
        }
        return null
    }

}