package updatesNotifications.notificators.implementations

import jakarta.enterprise.context.ApplicationScoped
import jakarta.websocket.Session
import updatesNotifications.notificators.IUpdatesNotificator
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@ApplicationScoped
class UpdatesNotificator : IUpdatesNotificator {

    private var _sessions: MutableMap<UUID, Session> = ConcurrentHashMap()

    override fun notify(clientId: UUID, message: String) {
        _sessions[clientId]?.asyncRemote?.sendObject(message)
    }

    override fun notifyAll(message: String) {
        _sessions.values.forEach { session ->
            session.asyncRemote.sendObject(message)
        }
    }

    override fun addSession(session: Session, clientId: UUID) {
        _sessions[clientId] = session
    }

    override fun removeSession(clientId: UUID) {
        _sessions.remove(clientId)
    }
}