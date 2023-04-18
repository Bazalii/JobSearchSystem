function showInteractiveAlert(message){
    let alert = document.getElementById("interactiveAlert");

    let messageNode = document.createElement("h2");
    messageNode.innerHTML = message;

    alert.insertBefore(messageNode, alert.firstChild);
    alert.classList.add('open-popup');
}

function closePopup() {
    let alert = document.getElementById("interactiveAlert");

    alert.removeChild(alert.firstChild);

    alert.classList.remove('open-popup');
}