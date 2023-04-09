document.addEventListener("DOMContentLoaded", event => setRemoveResumeButtonsOnClickHandlers())

function setRemoveResumeButtonsOnClickHandlers() {
    let removeResumeButtons = document.getElementsByName("removeResumeButton");

    for (let buttonIndex in removeResumeButtons) {
        removeResumeButtons[buttonIndex].addEventListener("click", event => removeResume(event));
    }
}

async function removeResume(event) {
    let response = await doBackendRequest(
        "http://localhost:8080/resumes/",
        "DELETE",
        `${event.target.id}`
    );
}