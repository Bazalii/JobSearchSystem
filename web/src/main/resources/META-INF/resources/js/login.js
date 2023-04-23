document.addEventListener("DOMContentLoaded", event => setLoginButtonOnClickHandler())
document.addEventListener("DOMContentLoaded", event => showChoiceAlert())
window.addEventListener("load", event => redirectUserToAvailablePage());

function setLoginButtonOnClickHandler() {
    let loginButton = document.getElementById("loginButton");
    let loginForm = document.getElementById("loginForm");

    loginButton.addEventListener("click", event => login());
    loginForm.addEventListener("submit", event => removeDefaultEventHandler(event));
}

async function login() {
    let uniqueIdentifier = document.getElementById("uniqueIdentifier").value
    let password = document.getElementById("password").value

    await backendLogin(
        {
            uniqueIdentifier: uniqueIdentifier,
            password: password
        }
    )
}

function showChoiceAlert() {
    if (!checkIfRefreshTokenExists()) {
        return;
    }

    showInteractiveAlert("Do you want to continue with account that you are already logged in?");
}

async function redirectUserToAvailablePage() {
    let sessionToken = await getSessionToken()

    if (sessionToken !== "") {
        let sharpIndex = window.location.href.indexOf("#")

        if (sharpIndex !== -1) {
            let actualPath = window.location.href.substring(sharpIndex + 1)

            await renderApplicationPage(actualPath)
        }
    }
}
