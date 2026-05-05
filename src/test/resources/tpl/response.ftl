<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpResponseAttachment" -->
<div>Status code <#if data.responseCode??>${data.responseCode}<#else>Unknown</#if></div>

<#if data.body??>
    <h4>Body</h4>
    <div><pre class="preformated-text">
<#assign safeBody = data.body?replace("\"password\"\\s*:\\s*\"[^\"]*\"", "\"password\":\"****\"", "r")>
${safeBody}
    </pre></div>
</#if>

<#if (data.headers)?has_content>
    <h4>Headers</h4>
    <div>
        <#list data.headers as name, value>
            <div>${name}: ${value!"null"}</div>
        </#list>
    </div>
</#if>