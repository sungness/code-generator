<nb-card>
    <nb-card-header>
    ${table.clearComment} 详细信息
    </nb-card-header>

    <nb-card-body>
        <!--<div class="items">
            <div *ngFor="let entry of partnerUser | keys" class="item">
                <div>
                    <h4 class="text-heading">{{entry.key}}</h4>
                    <span class="detail">{{entry.value}}</span>
                </div>
            </div>
        </div>-->
        <div *ngIf="${table.camelCaseName}" class="items">
        <#list columnList as column>
            <div class="item">
                <div>
                    <h4 class="text-heading">${column.clearComment}</h4>
                    <span class="detail">{{${table.camelCaseName}.${column.camelCaseName}}</span>
                </div>
            </div>
        </#list>
        </div>
    </nb-card-body>
    <nb-card-footer>
        <button class="btn btn-hero-primary" routerLink="/pages/${viewPath}">返回</button>
    </nb-card-footer>
</nb-card>