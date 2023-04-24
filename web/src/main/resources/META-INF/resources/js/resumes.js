window.addEventListener("load", event => addPagesRenderingForLinks());

document.addEventListener("DOMContentLoaded", event => {
    let forms = [];

    forms.push(...document.getElementsByName("removeResumeForm"));

    removePageReloadingFromForms(forms);
});
document.addEventListener("DOMContentLoaded", event => setRemoveResumeButtonsOnClickHandlers());
document.addEventListener("DOMContentLoaded", event => connectToWebsocket(
    (event) => {
        if (event.data.includes("Resume")) {
            showInteractiveAlert(event.data);
        }
    }
));

function setRemoveResumeButtonsOnClickHandlers() {
    let removeResumeButtons = document.getElementsByName("removeResumeButton");

    for (let buttonIndex in removeResumeButtons) {
        removeResumeButtons[buttonIndex].addEventListener("click", event => removeResume(event));
    }
}

async function removeResume(event) {
    let response = await doBackendRequest(
        `http://localhost:8080/resumes/${event.target.id}`,
        "DELETE",
    );

    location.reload();
}