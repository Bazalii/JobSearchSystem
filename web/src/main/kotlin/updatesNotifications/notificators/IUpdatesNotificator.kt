package updatesNotifications.notificators

import java.util.*
import javax.websocket.Session

interface IUpdatesNotificator {
    fun notify(clientId: UUID, message: String)
    fun notifyAll(message: String)
    fun addSession(session: Session, clientId: UUID)
    fun removeSession(clientId: UUID)
}