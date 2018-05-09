<nb-card>
  <nb-card-header>
    {{ ${table.camelCaseName} && ${table.camelCaseName}.${searchColumnName} != null ? ${table.camelCaseName}.${searchColumnName} : '' }}
    {{ ${table.camelCaseName} && ${table.camelCaseName}.id ? '编辑' : '新增'}}
  </nb-card-header>

  <nb-card-body>
    <div *ngIf="${table.camelCaseName}" class="form-group">
      <form name="editForm" role="form" (ngSubmit)="save()" #editForm="ngForm">

        <ngx-alert></ngx-alert>
        <ngx-alert-error></ngx-alert-error>
        <#list columnList as column>
            <#if column.columnName != "id">
        <div class="form-group">
          <label for="${column.camelCaseName}">${column.camelCaseName}</label>
          <input type="text" class="form-control" placeholder="${column.camelCaseName}"
                 [(ngModel)]="${table.camelCaseName}.${column.camelCaseName}"
                 name="${column.camelCaseName}"
                 #${column.camelCaseName}Input="ngModel"
                 [class.form-control-danger]="${column.camelCaseName}Input.invalid && ${column.camelCaseName}Input.touched"
                 autofocus required="required"
                 id="${column.camelCaseName}"/>
          <div *ngIf="${column.camelCaseName}Input.touched && ${column.camelCaseName}Input.invalid">
            <small class="form-text error"
                   *ngIf="${column.camelCaseName}Input.errors?.required"
                   ngxTranslate="entity.validation.required">
                ${column.camelCaseName}不能为空
            </small>
          </div>
        </div>
            </#if>
        </#list>
        <button type="submit" class="btn btn-danger" [disabled]="editForm.form.invalid || submitting"
                ngxTranslate="entity.action.save"></button>
        <button class="btn btn-hero-primary"
                ngxTranslate="entity.action.back"
                routerLink="/pages${viewPath}">返回
        </button>
      </form>

    </div>
  </nb-card-body>
  <nb-card-footer>

  </nb-card-footer>
</nb-card>
