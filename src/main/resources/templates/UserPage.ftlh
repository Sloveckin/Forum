<#import "commons.ftlh" as c>


<@c.page>

    <#if selectUser??>
        <p>ID: ${selectUser.id}</p>
        <p>Login: ${selectUser.login}</p>
        <p>CreationTime: ${selectUser.creationTime}</p>
    <#else>
        <p>No such user :(</p>
    </#if>
</@c.page>