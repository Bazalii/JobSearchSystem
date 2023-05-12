package updatesNotifications.notificators

import jakarta.websocket.Session
import java.util.*

interface IUpdatesNotificator {
    fun notify(clientId: UUID, message: String)
    fun notifyAll(message: String)
    fun addSession(session: Session, clientId: UUID)
    fun removeSession(clientId: UUID)
}