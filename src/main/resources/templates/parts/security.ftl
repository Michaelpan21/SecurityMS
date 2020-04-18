<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = user.getUsername()
        name = user.getName()
        degree = user.getDegree()
        isAdmin = user.isAdmin()
    >
<#else>
    <#assign
        username = ""
        name = ""
        degree = ""
        isAdmin = ""
        isProfessor = ""
    >
</#if>