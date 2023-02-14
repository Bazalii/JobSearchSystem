function setOnSubmitHandlers() {
    let tableSizingForm = document.getElementById('tableSizing');
    let tableInputForm = document.getElementById('tableInput');

    tableSizingForm.addEventListener('submit', event => removeDefaultEventHandler(event));
    tableInputForm.addEventListener('submit', event => removeDefaultEventHandler(event));
}

function removeDefaultEventHandler(event) {
    event.preventDefault();
}

function setTableFormsOnClickHandlers() {
    let tableSizingButton = document.getElementById('tableSizingButton');
    let tableInputButton = document.getElementById('tableInputButton');
    let clearLocalStorageButton = document.getElementById('clearLocalStorage');

    tableSizingButton.addEventListener('click', event =>
        createTable(
            document.getElementById('customTable'),
            document.getElementById('columns').value,
            document.getElementById('rows').value
        ));

    tableInputButton.addEventListener('click', event => {
            addContentToCell(
                document.getElementsByTagName('table')[0],
                document.getElementById('column').value - 1,
                document.getElementById('row').value - 1,
                document.getElementById('tableCellContent').value
            );

            saveTable();
        }
    );

    clearLocalStorageButton.addEventListener('click', event => clearLocalStorage());
}

function createTable(parent, columnsNumber, rowsNumber) {
    if (columnsNumber < 1) {
        throw new Error('Columns number cannot be less than 1!');
    }

    if (rowsNumber < 1) {
        throw new Error('Rows number cannot be less than 1!');
    }

    let table = document.createElement('table');

    for (let i = 0; i < rowsNumber; i++) {
        let tr = document.createElement('tr');

        for (let j = 0; j < columnsNumber; j++) {
            let td = document.createElement('td');

            tr.appendChild(td);
        }

        table.appendChild(tr);
    }

    let gridTemplateColumns = `repeat(${columnsNumber}, minmax(100px, 250px))`;

    table.style.setProperty('grid-template-columns', gridTemplateColumns);

    parent.appendChild(table);

    return table;
}

function addContentToCell(table, columnNumber, rowNumber, content) {
    let cell = table.rows[rowNumber].cells[columnNumber];

    cell.innerHTML = content;
}

function loadTable(parent, columnsNumber, rowsNumber) {
    let tableInfo = JSON.parse(localStorage.getItem('tableInfo'));

    if (columnsNumber === 0 || rowsNumber === 0) return;

    let table = createTable(parent, columnsNumber, rowsNumber);

    tableInfo.forEach(cell => addContentToCell(table, cell.column, cell.row, cell.content));
}

function saveTable() {
    let table = document.getElementsByTagName('table')[0];
    let tableInfo = [];

    for (let rowNumber = 0; rowNumber < table.rows.length; rowNumber++) {
        for (let columnNumber = 0; columnNumber < table.rows[rowNumber].cells.length; columnNumber++) {
            let tableCellContent = table.rows[rowNumber].cells[columnNumber].innerHTML;

            if (tableCellContent.length !== 0) {
                let cellInformation = {
                    column: columnNumber,
                    row: rowNumber,
                    content: tableCellContent
                };

                tableInfo.push(cellInformation);
            }
        }
    }

    localStorage.setItem('tableInfo', JSON.stringify(tableInfo));
    localStorage.setItem('tableColumnsNumber', table.rows[0].cells.length.toString());
    localStorage.setItem('tableRowsNumber', table.rows.length.toString());
}

function clearLocalStorage() {
    localStorage.clear();
}
