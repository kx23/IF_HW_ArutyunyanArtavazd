<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->
<div><#if data.method??>${data.method}<#else>GET</#if> to <#if data.url??>${data.url}<#else>Unknown</#if></div>

<#if data.body??>
    <h4>Body</h4>
    <div><pre class="preformated-text">
<#assign safeBody = data.body?replace("\"password\"\\s*:\\s*\"[^\"]*\"", "\"password\":\"****\"", "r")>
${safeBody}
    </pre></div>
</#if>

<#if (data.cookies)?has_content>
    <h4>Cookies</h4>
    <div>
        <#list data.cookies as name, value>
            <div>${name}: ${value!"null"}</div>
        </#list>
    </div>
</#if>

<#if (data.headers)?has_content>
    <h4>Headers</h4>
    <div>
        <#list data.headers as name, value>
            <#if name?lower_case == "authorization">
                <div>${name}: ****</div>
            <#else>
                <div>${name}: ${value!"null"}</div>
            </#if>
        </#list>
    </div>
</#if>