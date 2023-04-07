function removeDefaultEventHandler(event) {
    event.preventDefault();
}

async function doBackendRequest(path, method, queryParams, body) {
    let sessionToken = localStorage.getItem("sessionToken");

    if (sessionToken === null) {
        sessionToken = await getSessionToken()
    }

    if (sessionToken === "") {
        await renderApplicationPage("login");
        return;
    }

    let response = await doRequest(
        path,
        method,
        queryParams,
        body,
        sessionToken
    );

    if (response.status === 401) {
        let sessionToken = await getSessionToken();

        if (sessionToken === "") {
            await renderApplicationPage("login");
            return;
        }

        response = await doRequest(
            path,
            method,
            queryParams,
            body
        );
    }

    if (response.status >= 400) {
        let exception = await response.text();
        alert(exception);
    }

    return response
}

async function doRequest(path, method, queryParams, body, sessionToken) {
    if (body === undefined) {
        return await fetch(`${path}${queryParams}`, {
            method: `${method}`,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${sessionToken}`
            }
        });
    }

    return await fetch(`${path}${queryParams}`, {
        method: `${method}`,
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${sessionToken}`
        },
        body: JSON.stringify(body)
    });
}

async function renderApplicationPage(page) {
    let path = `http://localhost:8080/pages/${page}`;

    let pageRequest = await doBackendRequest(
        path,
        "GET",
        ""
    );

    window.history.replaceState("", "", path);

    let htmlPage = await pageRequest.text();

    document.open();
    document.write(htmlPage)
    document.close();
}
