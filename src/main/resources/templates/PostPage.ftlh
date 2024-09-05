<#-- @ftlvariable name="currentPost" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="posts" type="ru.itmo.wp.domain.Post[]" -->
<#import "commons.ftlh" as c>

<@c.page>

    <#if currentPost?has_content>
        <@c.post post=currentPost/>

        <div class="form">
            <div class="body">
                <form method="post" action="">

                    <div class="field">
                        <div class="name">
                            <label for="text">Your comment</label>
                        </div>
                        <div class="value">
                            <input id="text" name="text"/>
                        </div>
                    </div>
                    <@c.error "comment.text"/>

                    <div class="button-field">
                        <input type="submit" value="Send">
                    </div>

                </form>
            </div>
        </div>


        <#list currentPost.commentList?reverse as com>
            <article>
                <a href="/user/${com.user.id}" style="text-decoration: none; color: blue">
                    <#if currentPost.user.id == com.user.id>
                        <div class="user"> &#9733 ${com.user.login}</div>
                    <#else>
                        <div class="user">${com.user.login} </div>
                    </#if>
                </a>
                <div class="text">${com.text}</div>
            </article>
        </#list>

    <#else>
        No such post :(
    </#if>
</@c.page>
