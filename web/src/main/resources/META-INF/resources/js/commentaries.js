document.addEventListener("DOMContentLoaded", event => removePageReloadingFromForms())
document.addEventListener("DOMContentLoaded", event => setAddCommentaryButtonOnClickHandler())
document.addEventListener("DOMContentLoaded", event => setRemoveCommentaryButtonsOnClickHandlers())

function removePageReloadingFromForms() {
    let commentaryInformationForm = document.getElementsByName("commentaryInformationForm")[0];

    commentaryInformationForm.addEventListener("submit", event => removeDefaultEventHandler(event));
}

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
        "",
        body
    );
}

async function removeCommentary(event) {
    let response = await doBackendRequest(
        "http://localhost:8080/commentaries/",
        "DELETE",
        `${event.target.id}`
    );
}