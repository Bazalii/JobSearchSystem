window.addEventListener("load", event => addPagesRenderingForLinks());

document.addEventListener("DOMContentLoaded", event => {
    let forms = []

    forms.push(document.getElementsByName("hardSkillInformationForm")[0]);
    forms.push(document.getElementsByName("changeRoleForm")[0]);

    removePageReloadingFromForms(forms);
})
document.addEventListener("DOMContentLoaded", event => setAddHardSkillButtonOnClickHandler())
document.addEventListener("DOMContentLoaded", event => setRemoveHardSkillButtonsOnClickHandlers())
document.addEventListener("DOMContentLoaded", event => setChangeRoleButtonOnClickHandler())

function setChangeRoleButtonOnClickHandler() {
    let button = document.getElementById("changeRoleButton");

    button.addEventListener("click", event => updateUserRole());
}

function setAddHardSkillButtonOnClickHandler() {
    let saveResumeButton = document.getElementById("addHardSkillButton");

    saveResumeButton.addEventListener("click", event => sendHardSkill());
}

function setRemoveHardSkillButtonsOnClickHandlers() {
    let removeProgrammingLanguagesButton = document.getElementById("removeProgrammingLanguagesButton");
    let removeFrameworksButton = document.getElementById("removeFrameworksButton");
    let removeDatabasesButton = document.getElementById("removeDatabasesButton");

    removeProgrammingLanguagesButton.addEventListener("click", event =>
        removeHardSkills(
            document.getElementById("programmingLanguages").options,
            "programmingLanguages"
        ));
    removeFrameworksButton.addEventListener("click", event =>
        removeHardSkills(
            document.getElementById("frameworks").options,
            "frameworks"
    ));
    removeDatabasesButton.addEventListener("click", event =>
        removeHardSkills(
            document.getElementById("databases").options,
            "databases"
    ));
}

function removeHardSkills(hardSkillsList, controllerPath) {
    for (let hardSkill of hardSkillsList) {
        if (hardSkill.selected) {
            doBackendRequest(
                `/${controllerPath}/${hardSkill.value}`,
                "DELETE",
            )
        }
    }
}

async function sendHardSkill() {
    let name = document.getElementById("hardSkillName").value
    let type = document.getElementById("hardSkillType").value

    let body =
        {
            name: name
        }

    switch (type) {
        case "Programming language":
            doBackendRequest(
                "/programmingLanguages",
                "POST",
                body
            )
            break
        case "Framework":
            doBackendRequest(
                "/frameworks",
                "POST",
                body
            )
            break
        case "Database":
            doBackendRequest(
                "/databases",
                "POST",
                body
            )
            break
    }
}

function updateUserRole() {
    let login = document.getElementById("login").value
    let role = document.getElementById("role").value

    doBackendRequest(
        `/users/updateRole?login=${login}&role=${role}`,
        "PUT",
    );
}