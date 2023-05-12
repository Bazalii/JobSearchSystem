import jakarta.enterprise.context.ApplicationScoped
import jakarta.websocket.*
import jakarta.websocket.server.PathParam
import jakarta.websocket.server.ServerEndpoint
import updatesNotifications.notificators.IUpdatesNotificator
import java.util.*


@ServerEndpoint("/updatesNotificator/{clientId}")
@ApplicationScoped
class UpdatesNotificationsSocket(
    private val _updatesNotificator: IUpdatesNotificator,
) {

    @OnOpen
    fun onOpen(session: Session, @PathParam("clientId") clientId: String) {
        _updatesNotificator.addSession(session, UUID.fromString(clientId))
    }

    @OnClose
    fun onClose(session: Session?, @PathParam("clientId") clientId: String) {
        _updatesNotificator.removeSession(UUID.fromString(clientId))
    }

    @OnError
    fun onError(session: Session?, @PathParam("clientId") clientId: String, throwable: Throwable) {
        _updatesNotificator.removeSession(UUID.fromString(clientId))
    }

    @OnMessage
    fun onMessage(message: String) {
        _updatesNotificator.notifyAll("Message sent: $message")
    }
}
