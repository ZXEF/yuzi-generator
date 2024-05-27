package ${basePackage}.model;
import lombok.Data;

@Data
public class DataModel {
<#list modelConfig.models as modelInfo>
    <#if modelInfo.description??>

    </#if>>
    private ${modelInfo.type}   ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue}</#if>;
</#list>
}
