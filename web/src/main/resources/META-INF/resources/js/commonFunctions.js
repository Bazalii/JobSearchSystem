function removeDefaultEventHandler(event) {
    event.preventDefault();
}

async function doBackendRequest(path, method, body) {
    let sessionToken = localStorage.getItem("sessionToken");

    if (sessionToken === null) {
        sessionToken = await getSessionToken()
    }

    if (sessionToken === "") {
        await renderApplicationPage("/pages/login");
        return;
    }

    let response = await doRequest(
        path,
        method,
        body,
        sessionToken
    );

    if (response.status === 401) {
        let sessionToken = await getSessionToken();

        if (sessionToken === "") {
            await renderApplicationPage("/pages/login");
            return;
        }

        response = await doRequest(
            path,
            method,
            body
        );
    }

    if (response.status >= 400) {
        let exception = await response.text();
        alert(exception);
    }

    return response
}

async function doRequest(path, method, body, sessionToken) {
    if (body === undefined) {
        return await fetch(path, {
            method: method,
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${sessionToken}`
            }
        });
    }

    return await fetch(path, {
        method: method,
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${sessionToken}`
        },
        body: JSON.stringify(body)
    });
}

async function renderApplicationPage(page = "/pages/resumes?pageIndex=0&pageSize=5") {
    let pageRequest = await doBackendRequest(
        page,
        "GET",
    );

    window.history.replaceState("", "", page);

    let htmlPage = await pageRequest.text();

    document.open();
    document.write(htmlPage)
    document.close();
}

function connectToWebsocket(socketOnMessageFunction) {
    let id = crypto.randomUUID()

    let websocket = new WebSocket(`ws://${location.host}/updatesNotificator/${id}`);

    websocket.onmessage = socketOnMessageFunction;
}

function addPagesRenderingForLinks() {
    let links = document.links;

    if (links) {
        for (let i = 0; i < links.length; ++i) {
            let href = links[i].href;

            if (href.includes("/pages")) {
                links[i].onclick = async (event) => {
                    removeDefaultEventHandler(event)

                    await renderApplicationPage(
                        href
                    )
                };
            }
        }
    }
}

function removePageReloadingFromForms(forms) {
    for (let formIndex in forms) {
        forms[formIndex].addEventListener("submit", event => removeDefaultEventHandler(event));
    }
}
