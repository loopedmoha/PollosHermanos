import controllers.KFCController
import java.lang.Character.toChars
import javax.xml.stream.events.Characters
import models.*
import view.KFCView

fun main(args: Array<String>) {
   var controlador : KFCController = KFCController
var vista : KFCView = KFCView

   controlador.init()
   controlador.pollosHermanos()
}