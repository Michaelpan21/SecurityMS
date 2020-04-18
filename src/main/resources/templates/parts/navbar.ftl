<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/"><img class="navbar-img" src="/img/guap.png"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto navbar-fs">
            <li class="nav-item">
                <a class="nav-link" href="/">Аудитории</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile">Профиль</a>
            </li>

        </ul>

        <div class="navbar-text">${name}</div>
        <div>
            <form action="/logout" method="post">
                <input class="input" type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="btn btn-primary ml-2" type="submit">Выйти</button>
            </form>
        </div>
    </div>
</nav>