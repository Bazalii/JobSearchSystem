window.addEventListener("load", event => addPagesRenderingForLinks());

document.addEventListener("DOMContentLoaded", event => {
    let forms = [];

    forms.push(document.getElementsByName("hardSkillInformationForm")[0]);
    forms.push(document.getElementsByName("changeRoleForm")[0]);

    removePageReloadingFromForms(forms);
});
document.addEventListener("DOMContentLoaded", event => setAddHardSkillButtonOnClickHandler());
document.addEventListener("DOMContentLoaded", event => setRemoveHardSkillButtonsOnClickHandlers());
document.addEventListener("DOMContentLoaded", event => setChangeRoleButtonOnClickHandler());

function setChangeRoleButtonOnClickHandler() {
    let button = document.getElementById("changeRoleButton");

    button.addEventListener("click", async event => await updateUserRole());
}

function setAddHardSkillButtonOnClickHandler() {
    let saveResumeButton = document.getElementById("addHardSkillButton");

    saveResumeButton.addEventListener("click", async event => await addHardSkill());
}

function setRemoveHardSkillButtonsOnClickHandlers() {
    let removeProgrammingLanguagesButton = document.getElementById("removeProgrammingLanguagesButton");
    let removeFrameworksButton = document.getElementById("removeFrameworksButton");
    let removeDatabasesButton = document.getElementById("removeDatabasesButton");

    removeProgrammingLanguagesButton.addEventListener("click", async event =>
        await removeHardSkills(
            document.getElementById("programmingLanguages").options,
            "programmingLanguages"
        ));
    removeFrameworksButton.addEventListener("click", async event =>
        await removeHardSkills(
            document.getElementById("frameworks").options,
            "frameworks"
        ));
    removeDatabasesButton.addEventListener("click", async event =>
        await removeHardSkills(
            document.getElementById("databases").options,
            "databases"
        ));
}

async function removeHardSkills(hardSkillsList, controllerPath) {
    for (let hardSkill of hardSkillsList) {
        if (hardSkill.selected) {
            await doBackendRequest(
                `/${controllerPath}/${hardSkill.value}`,
                "DELETE",
            );
        }
    }
}

async function addHardSkill() {
    let name = document.getElementById("hardSkillName").value;
    let type = document.getElementById("hardSkillType").value;

    let body =
        {
            name: name
        };

    switch (type) {
        case "Programming language":
            await doBackendRequest(
                "/programmingLanguages",
                "POST",
                body
            );
            break;
        case "Framework":
            await doBackendRequest(
                "/frameworks",
                "POST",
                body
            );
            break;
        case "Database":
            await doBackendRequest(
                "/databases",
                "POST",
                body
            );
            break;
    }

    location.reload()
}

async function updateUserRole() {
    let login = document.getElementById("login").value;
    let role = document.getElementById("role").value;

    await doBackendRequest(
        `/users/updateRole?login=${login}&role=${role}`,
        "PUT",
    );
}