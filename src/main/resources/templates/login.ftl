<#import "parts/common.ftl" as c>

<@c.page>

<div class="login-box">
     <form method="POST" action="/login">
         <h1>Личный кабинет</h1>
         <div class="div-text">
             <input name="username" type="text">
             <span data-placeholder="Логин"></span>
         </div>
         <div class="div-text">
             <input name="password" type="password">
             <span data-placeholder="Пароль"></span>
         </div>
         <input type="hidden" name="_csrf" value="${_csrf.token}"/>
         <input type="submit" class="lgn-btn" value="Вход">
     </form>

    <script type="text/javascript">
        $(".div-text input").on("focus", function(){
            $(this).addClass("focus");
        });

        $(".div-text input").on("blur", function(){
            if($(this).val() == "")
            $(this).removeClass("focus");
        });
    </script>
 </div>

</@c.page>