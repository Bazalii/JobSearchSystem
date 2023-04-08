document.addEventListener("DOMContentLoaded", event => setRegistrationButtonOnClickHandlers())

function setRegistrationButtonOnClickHandlers() {
    let registrationButton = document.getElementById("registrationButton");
    let registrationForm = document.getElementById("registrationForm");

    registrationButton.addEventListener("click", event => sendUser());
    registrationForm.addEventListener("submit", event => removeDefaultEventHandler(event));
}

async function sendUser() {
    let login = document.getElementById("login").value
    let email = document.getElementById("email").value
    let password = document.getElementById("password").value
    let role = document.getElementById("role").value

    switch (role) {
        case "Applicant":
            role = "User"
            break
        case "HR":
            role = "HR"
            break
    }

    let user = {
        login: login,
        email: email,
        password: password,
        role: role
    }

    let response = await fetch("http://localhost:8080/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(user)
    })

    if (response.status === 200) {
        await backendLogin(
            {
                uniqueIdentifier: login,
                password: password
            }
        )
    }
}
