<nb-card>
    <nb-card-header>
    ${table.clearComment} 详细信息
    </nb-card-header>

    <nb-card-body class="p-0">
        <ul *ngIf="${table.camelCaseName}" class="detail-list">
        <#list columnList as column>
          <li>
            <div class="field" ngxTranslate="${table.camelCaseName}.${column.camelCaseName}">${column.clearComment}</div>
            <div class="value">{{${table.camelCaseName}.${column.camelCaseName}}}</div>
          </li>
        </#list>
        </ul>
    </nb-card-body>
    <nb-card-footer>
        <button class="btn btn-hero-primary" routerLink="/pages${viewPath}">返回</button>
    </nb-card-footer>
</nb-card>