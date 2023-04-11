async function getRefreshToken(userCredentials) {
    let response = await fetch("http://localhost:8080/refreshTokens/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(userCredentials)
    });

    if (response.status === 404) {
        return ""
    }

    let refreshToken = await response.text();

    localStorage.setItem("refreshToken", refreshToken);

    return refreshToken;
}

async function getSessionToken() {
    let refreshToken = localStorage.getItem("refreshToken");

    if (refreshToken === null) {
        return "";
    }

    let response = await fetch("http://localhost:8080/sessionTokens/refresh", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${refreshToken}`
        }
    });

    if (response.status != 200) {
        return "";
    }

    let sessionToken = await response.text();

    localStorage.setItem("sessionToken", sessionToken);

    return sessionToken;
}

async function getTokenPair(userCredentials) {
    let refreshToken = await getRefreshToken(userCredentials)

    if (refreshToken === "") {
        alert("Invalid credentials!");
        return false;
    }

    await getSessionToken()

    return true;
}

async function backendLogin(credentials) {
    let tokenGettingSuccess = await getTokenPair(
        {
            uniqueIdentifier: credentials.uniqueIdentifier,
            password: credentials.password
        }
    )

    if (!tokenGettingSuccess) {
        return;
    }

    await renderApplicationPage("resumes")
}
