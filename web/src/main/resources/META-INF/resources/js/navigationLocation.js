document.addEventListener("DOMContentLoaded", event => handleLocationChange(document.location.href, "#1CC5FA", "#000000"));

function handleLocationChange(location, background, color) {
    let menu = document.querySelectorAll("body header nav a");

    for (let i = 0; i < menu.length; i++) {
        if (location.includes(menu[i].id)) {
            menu[i].style.background = background;
            menu[i].style.color = color;
        }
    }
}
