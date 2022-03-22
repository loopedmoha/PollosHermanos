import controllers.KFCController
import view.KFCView

fun main(args: Array<String>) {
    val controlador: KFCController = KFCController
    var vista: KFCView = KFCView


    controlador.pollosHermanos()
}