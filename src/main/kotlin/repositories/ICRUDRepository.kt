package resources

interface ICRUDRepository<T> {
    /**
     * Encuentra todos los elementos del repositorio
     */
    fun findAll(): List<T?>?

    /**
     * Inserta un elemento
     */
    fun insert(entity: T?): T?

    /**
     * Actualiza un elemento
     */
    fun update(entity: T?): T?

    /**
     * Encuentra un elemento buscando por ID
     */
    fun findById(id: Int): T?

    /**
     * Elimina un elemento buscando por ID
     */
    fun deleteById(id: Int): T?

    /**
     * Elimina un elemento
     */
    fun delete(entity: T?): T?

}