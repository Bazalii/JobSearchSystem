function addHeader() {
    let template = Handlebars.compile(`<nav>
        <a href="index.html">Обо мне</a>
        <a href="WorkReviews.html">Отзывы</a>
        <a href="ProjectsAndAchievements.html">Проекты</a>
        <a href="WorkExperience.html">Опыт работы</a>
    </nav>`);

    let pageHeader = document.getElementsByTagName('header')[0];

    pageHeader.innerHTML = template()
}

function addFooter() {
    let template = Handlebars.compile(`<nav>
              <a href=\"https://github.com/Bazalii\" target=\"_blank\">Github</a>
                <a href=\"https://t.me/IvanBazalii\" target=\"_blank\">Telegram</a>
                <div class=\"loadInformation\" id=\"loadInformation\">Время загрузки страницы: </div>
            </nav>`);

    let pageHeader = document.getElementsByTagName('footer')[0];

    pageHeader.innerHTML = template()
}