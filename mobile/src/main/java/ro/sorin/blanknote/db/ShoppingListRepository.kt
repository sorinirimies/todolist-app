package ro.sorin.blanknote.db

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import ro.sorin.blanknote.model.ShoppingItem
import ro.sorin.blanknote.model.ShoppingList

class ShoppingListRepository constructor(private val shoppingListDao: ShoppingListDao) {

    suspend fun createShoppingList(shoppingList: ShoppingList) {
        withContext(IO) {
            shoppingListDao.insertShoppingList(shoppingList)
        }
    }

    fun getShoppingLists() = shoppingListDao.getAllShoppingLists()

    fun getShoppingItems(shoppingListId: Long) = shoppingListDao.getShoppingItemsFromList(shoppingListId)

    suspend fun removeShoppingList(shoppingList: ShoppingList) = withContext(IO) {
        shoppingListDao
                .deleteShoppingList(shoppingList)
    }

    suspend fun removeShoppingItem(shoppingItem: ShoppingItem) = withContext(IO) {
        shoppingListDao.deleteShoppingItem(shoppingItem)
    }
}

