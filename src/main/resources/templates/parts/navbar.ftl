<#include "security.ftl">

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="http://new.guap.ru/"><img class="navbar-img" src="/img/guap.png"></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto navbar-fs">
            <li class="nav-item">
                <a class="nav-link" href="/">Аудитории</a>
            </li>
        </ul>

        <#if user??>
            <form class="nav-r" action="/logout" method="post">
                <input class="input" type="hidden" name="_csrf" value="${_csrf.token}"/>
                <button class="lg-out-btn" type="submit"><span class="pe-7s-upload lg-out"></span></button>
            </form>
        </#if>
    </div>
</nav>