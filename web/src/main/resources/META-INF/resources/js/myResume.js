window.addEventListener("load", event => addPagesRenderingForLinks());

document.addEventListener("DOMContentLoaded", event => {
    let forms = [];

    forms.push(document.getElementsByName("resumeInformationForm")[0]);
    forms.push(document.getElementsByName("projectInformationForm")[0]);
    forms.push(document.getElementsByName("workExperienceItemInformationForm")[0]);
    forms.push(...document.getElementsByName("removeProjectForm"));
    forms.push(...document.getElementsByName("removeWorkExperienceItemForm"));

    removePageReloadingFromForms(forms);
});
document.addEventListener("DOMContentLoaded", event => setSaveResumeButtonOnClickHandler());
document.addEventListener("DOMContentLoaded", event => setDeleteResumeButtonOnClickHandler());
document.addEventListener("DOMContentLoaded", event => setRemoveProjectButtonsOnClickHandler());
document.addEventListener("DOMContentLoaded", event => setAddProjectButtonOnClickHandler());
document.addEventListener("DOMContentLoaded", event => setRemoveWorkExperienceItemButtonsOnClickHandler());
document.addEventListener("DOMContentLoaded", event => setAddWorkExperienceItemButtonOnClickHandler());

function setSaveResumeButtonOnClickHandler() {
    let saveResumeButton = document.getElementById("saveResumeButton");

    saveResumeButton.addEventListener("click", event => saveOrUpdateResume());
}

function setDeleteResumeButtonOnClickHandler() {
    let deleteResumeButton = document.getElementById("deleteResumeButton");

    deleteResumeButton.addEventListener("click", event => removeResume());
}

function setRemoveProjectButtonsOnClickHandler() {
    let removeProjectButtons = document.getElementsByName("removeProjectButton");

    for (let buttonIndex in removeProjectButtons) {
        removeProjectButtons[buttonIndex].addEventListener("click", event => removeProject(event));
    }
}

function setAddProjectButtonOnClickHandler() {
    let addProjectButton = document.getElementById("addProjectButton");

    addProjectButton.addEventListener("click", event => addProject());
}

function setAddWorkExperienceItemButtonOnClickHandler() {
    let addProjectButton = document.getElementById("addWorkExperienceItemButton");

    addProjectButton.addEventListener("click", event => addWorkExperienceItem());
}

function setRemoveWorkExperienceItemButtonsOnClickHandler() {
    let removeWorkExperienceItemButtons = document.getElementsByName("removeWorkExperienceItemButton");

    for (let buttonIndex in removeWorkExperienceItemButtons) {
        removeWorkExperienceItemButtons[buttonIndex].addEventListener("click", event => removeWorkExperienceItem(event));
    }
}

async function addProject() {
    let name = document.getElementById("projectName").value;
    let link = document.getElementById("projectLink").value;
    let year = document.getElementById("projectYear").value;
    let resumeId = document.getElementsByName("resumeInformationForm")[0].id;

    let body = {
        name: name,
        link: link,
        year: year,
        resumeId: resumeId
    };

    let response = await doBackendRequest(
        "http://localhost:8080/projects/",
        "POST",
        body
    );

    if (response.status !== 200) {
        return;
    }

    let responseEntity = await response.json();

    let projectsTable = document.getElementById("projectsTable");

    if (projectsTable === null) {
        let projectsTableWrapper = document.getElementById("wrapperForProjectsTable");
        let projectInformationForm = document.getElementsByName("projectInformationForm")[0];

        let projectsTableNode = document.createElement("table");

        projectsTableNode.id = "projectsTable";
        projectsTableNode.style = "grid-template-columns: repeat(4, minmax(100px, 250px));";

        projectsTableWrapper.insertBefore(projectsTableNode, projectInformationForm);

        projectsTable = projectsTableNode;
    }

    let newRow = projectsTable.insertRow(-1);
    let firstCell = newRow.insertCell(0);
    let secondCell = newRow.insertCell(1);
    let thirdCell = newRow.insertCell(2);
    let fourthCell = newRow.insertCell(3);

    let linkNode = document.createElement("a");
    linkNode.href = `${responseEntity.link}`;
    linkNode.classList.add("classicHref");
    linkNode.target = "_blank";
    linkNode.innerHTML = "Source";

    let removeProjectFormNode = document.createElement("form");
    removeProjectFormNode.name = "removeProjectForm";
    removeProjectFormNode.addEventListener("submit", event => removeDefaultEventHandler(event));

    let removeProjectButton = document.createElement("input");
    removeProjectButton.id = `${responseEntity.id}`;
    removeProjectButton.classList.add("deleteButton");
    removeProjectButton.name = "removeProjectButton";
    removeProjectButton.type = "submit";
    removeProjectButton.value = "Remove";
    removeProjectButton.addEventListener("click", event => removeProject(event));

    removeProjectFormNode.appendChild(removeProjectButton);

    firstCell.innerText = name;
    secondCell.appendChild(linkNode);
    thirdCell.innerText = year;
    fourthCell.appendChild(removeProjectFormNode);
}

async function removeProject(event) {
    let response = await doBackendRequest(
        `http://localhost:8080/projects/${event.target.id}`,
        "DELETE",
    );

    if (response.status !== 200) {
        return;
    }

    let projectsTable = document.getElementById("projectsTable");

    if (projectsTable.rows.length === 1) {
        projectsTable.remove();
        return;
    }

    let removeProjectButton = document.getElementById(event.target.id);
    removeProjectButton.parentElement.parentElement.parentElement.remove();
}

async function addWorkExperienceItem() {
    let place = document.getElementById("workExperienceItemPlace").value;
    let position = document.getElementById("workExperienceItemPosition").value;
    let startDate = document.getElementById("workExperienceItemStartDate").value;
    let endDate = document.getElementById("workExperienceItemEndDate").value;
    let resumeId = document.getElementsByName("resumeInformationForm")[0].id;

    let body = {
        place: place,
        position: position,
        startDate: startDate,
        endDate: endDate,
        resumeId: resumeId
    };

    let response = await doBackendRequest(
        "http://localhost:8080/workExperiences/",
        "POST",
        body
    );

    if (response.status !== 200) {
        return;
    }

    let responseEntity = await response.json();

    let workExperienceTable = document.getElementById("workExperienceTable");

    if (workExperienceTable === null) {
        let workExperienceTableWrapper = document.getElementById("wrapperForWorkExperienceTable");
        let workExperienceItemInformationForm = document.getElementsByName("workExperienceItemInformationForm")[0];

        let workExperienceTableNode = document.createElement("table");

        workExperienceTableNode.id = "workExperienceTable";
        workExperienceTableNode.style = "grid-template-columns: repeat(5, minmax(100px, 250px));";

        workExperienceTableWrapper.insertBefore(workExperienceTableNode, workExperienceItemInformationForm);

        workExperienceTable = workExperienceTableNode;
    }

    let newRow = workExperienceTable.insertRow(-1);
    let firstCell = newRow.insertCell(0);
    let secondCell = newRow.insertCell(1);
    let thirdCell = newRow.insertCell(2);
    let fourthCell = newRow.insertCell(3);
    let fifthCell = newRow.insertCell(4);

    let removeWorkExperienceItemFormNode = document.createElement("form");
    removeWorkExperienceItemFormNode.name = "removeWorkExperienceItemForm";
    removeWorkExperienceItemFormNode.addEventListener("submit", event => removeDefaultEventHandler(event));

    let removeWorkExperienceItemButton = document.createElement("input");
    removeWorkExperienceItemButton.id = `${responseEntity.id}`;
    removeWorkExperienceItemButton.classList.add("deleteButton");
    removeWorkExperienceItemButton.name = "removeWorkExperienceItemButton";
    removeWorkExperienceItemButton.type = "submit";
    removeWorkExperienceItemButton.value = "Remove";
    removeWorkExperienceItemButton.addEventListener("click", event => removeWorkExperienceItem(event));

    removeWorkExperienceItemFormNode.appendChild(removeWorkExperienceItemButton);

    firstCell.innerText = place;
    secondCell.innerText = position;
    thirdCell.innerText = startDate;
    fourthCell.innerText = endDate;
    fifthCell.appendChild(removeWorkExperienceItemFormNode);
}

async function removeWorkExperienceItem(event) {
    let response = await doBackendRequest(
        `http://localhost:8080/workExperiences/${event.target.id}`,
        "DELETE",
    );

    if (response.status !== 200) {
        return;
    }

    let workExperienceTable = document.getElementById("workExperienceTable");

    if (workExperienceTable.rows.length === 1) {
        workExperienceTable.remove();
        return;
    }

    let removeWorkExperienceItemButton = document.getElementById(event.target.id);
    removeWorkExperienceItemButton.parentElement.parentElement.parentElement.remove();
}

async function saveOrUpdateResume() {
    let resumeForm = document.getElementsByName("resumeInformationForm")[0];

    let name = document.getElementById("name").value;
    let currentJob = document.getElementById("currentJob").value;
    let quote = document.getElementById("quote").value;
    let otherTechnologies = document.getElementById("otherTechnologies").value;
    let additionalInformation = document.getElementById("additionalInformation").value;

    let languages = document.getElementById("programmingLanguages").options;
    let frameworks = document.getElementById("frameworks").options;
    let databases = document.getElementById("databases").options;

    let selectedLanguages = [];
    let selectedFrameworks = [];
    let selectedDatabases = [];

    for (let language of languages) {
        if (language.selected) {
            selectedLanguages.push(language.value);
        }
    }

    for (let framework of frameworks) {
        if (framework.selected) {
            selectedFrameworks.push(framework.value);
        }
    }

    for (let database of databases) {
        if (database.selected) {
            selectedDatabases.push(database.value);
        }
    }

    let body = {
        name: name,
        currentJob: currentJob,
        quote: quote,
        languages: selectedLanguages,
        frameworks: selectedFrameworks,
        databases: selectedDatabases,
        otherTechnologies: otherTechnologies,
        additionalInformation: additionalInformation
    };

    if (resumeForm.id === "00000000-0000-0000-0000-000000000000") {

        let response = await doBackendRequest(
            `http://localhost:8080/resumes/`,
            "POST",
            body
        );

        let result = await response.json();

        resumeForm.id = result.id;

        return;
    }

    await doBackendRequest(
        `http://localhost:8080/resumes/${resumeForm.id}`,
        "PUT",
        body
    );
}

async function removeResume() {
    let resumeId = document.getElementsByName("resumeInformationForm")[0].id;

    if (resumeId === "00000000-0000-0000-0000-000000000000") {
        alert("Save resume first!");
        return;
    }

    await doBackendRequest(
        `http://localhost:8080/resumes/${resumeId}`,
        "DELETE",
    );
}