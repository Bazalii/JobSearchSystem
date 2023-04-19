import updatesNotifications.notificators.IUpdatesNotificator
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.websocket.*
import javax.websocket.server.PathParam
import javax.websocket.server.ServerEndpoint


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
