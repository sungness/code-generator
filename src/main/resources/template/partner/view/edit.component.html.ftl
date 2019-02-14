<form name="editForm" role="form" (ngSubmit)="save()" #editForm="ngForm">
<nb-card>
  <nb-card-header>
    {{ ${table.camelCaseName} && ${table.camelCaseName}.${searchColumnName} != null ? ${table.camelCaseName}.${searchColumnName} : '' }}
    {{ ${table.camelCaseName} && ${table.camelCaseName}.id ? '编辑' : '新增'}}
  </nb-card-header>

  <nb-card-body>
    <div *ngIf="${table.camelCaseName}" class="form-group">

        <ngx-alert></ngx-alert>
        <ngx-alert-error></ngx-alert-error>
        <#list columnList as column>
            <#if column.columnName != "id">
        <div class="row">
          <div class="col-md-2 form-label">
            <#if column.isNullable == "NO">
            <span class="text-danger">*&nbsp;</span>
            </#if>
            <label for="${column.camelCaseName}"
                   ngxTranslate="${table.camelCaseName}.${column.camelCaseName}">${column.clearComment}</label>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <input type="text" class="form-control"
                     placeholder="请输入{{'${table.camelCaseName}.${column.camelCaseName}'| translate}}"
                     [(ngModel)]="${table.camelCaseName}.${column.camelCaseName}"
                     name="${column.camelCaseName}"
                     #${column.camelCaseName}Input="ngModel"
                     [class.form-control-danger]="${column.camelCaseName}Input.invalid && ${column.camelCaseName}Input.touched"
                 <#if column_index == 1>autofocus </#if>required="required"
                     id="${column.camelCaseName}"/>
              <div
                  *ngIf="${column.camelCaseName}Input.touched && ${column.camelCaseName}Input.invalid">
                <small class="form-text error"
                       *ngIf="${column.camelCaseName}Input.errors?.required"
                       ngxTranslate="entity.validation.required">
                    ${column.camelCaseName}不能为空
                </small>
              </div>
            </div>
          </div>
        </div>
            </#if>
        </#list>
    </div>
  </nb-card-body>
  <nb-card-footer>
    <button type="submit" class="btn btn-danger mr-2"
            [disabled]="editForm.form.invalid || submitting"
            ngxTranslate="entity.action.save">保存</button>
    <button class="btn btn-primary"
            ngxTranslate="entity.action.back"
            routerLink="/pages${viewPath}">返回</button>
  </nb-card-footer>
</nb-card>
</form>