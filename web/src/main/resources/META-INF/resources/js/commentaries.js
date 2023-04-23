window.addEventListener("load", event => addPagesRenderingForLinks());

document.addEventListener("DOMContentLoaded", event => {
    let forms = [];

    forms.push(document.getElementsByName("commentaryInformationForm")[0]);
    forms.push(...document.getElementsByName("removeCommentaryForm"));

    removePageReloadingFromForms(forms);
})
document.addEventListener("DOMContentLoaded", event => setAddCommentaryButtonOnClickHandler())
document.addEventListener("DOMContentLoaded", event => setRemoveCommentaryButtonsOnClickHandlers())
document.addEventListener("DOMContentLoaded", event => connectToWebsocket(
    (event) => {
        if (event.data.includes("Commentary")) {
            showInteractiveAlert(event.data);
        }
    }
))

function setAddCommentaryButtonOnClickHandler() {
    let addCommentaryButton = document.getElementById("addCommentaryButton");

    addCommentaryButton.addEventListener("click", async event => await addCommentary());
}

function setRemoveCommentaryButtonsOnClickHandlers() {
    let removeCommentaryButtons = document.getElementsByName("removeCommentaryButton");

    for (let buttonIndex in removeCommentaryButtons) {
        removeCommentaryButtons[buttonIndex].addEventListener("click", event => removeCommentary(event));
    }
}

async function addCommentary() {
    let commentaryTitle = document.getElementById("commentaryTitle").value;
    let commentaryBody = document.getElementById("commentaryBody").value;

    let body = {
        title: commentaryTitle,
        body: commentaryBody,
    };

    await doBackendRequest(
        "http://localhost:8080/commentaries/",
        "POST",
        body
    );

    location.reload();
}

async function removeCommentary(event) {
    let response = await doBackendRequest(
        `http://localhost:8080/commentaries/${event.target.id}`,
        "DELETE",
    );

    location.reload();
}