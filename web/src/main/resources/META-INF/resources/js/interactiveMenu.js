function handleMouseOn(event, background, color) {
    let target = event.target;

    target.style.background = background;
    target.style.color = color;
}

function handleMouseOut(event) {
    let target = event.target;

    if (target.text === document.title) return;

    target.style.background = '';
    target.style.color = '';
}

function setMouseOnHandlers(selectors, background, color) {
    let elements = document.querySelectorAll(selectors);

    elements.forEach(element => element.onmouseover = event => handleMouseOn(event, background, color));
}

function setMouseOutHandlers(selectors) {
    let elements = document.querySelectorAll(selectors);

    elements.forEach(element => element.onmouseout = event => handleMouseOut(event, selectors));
}



