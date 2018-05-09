<nb-card>
    <nb-card-header>
    ${table.clearComment} 详细信息
    </nb-card-header>

    <nb-card-body>
        <div *ngIf="${table.camelCaseName}" class="items">
        <#list columnList as column>
            <div class="item">
                <div>
                    <h4 class="text-heading">{{'${table.camelCaseName}.${column.camelCaseName}' | translate}}</h4>
                    <span class="detail">{{${table.camelCaseName}.${column.camelCaseName}}}</span>
                </div>
            </div>
        </#list>
        </div>
    </nb-card-body>
    <nb-card-footer>
        <button class="btn btn-hero-primary" routerLink="/pages${viewPath}">返回</button>
    </nb-card-footer>
</nb-card>