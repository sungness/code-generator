import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Principal} from '../../../@core/auth/principal.service';
import {StateStorageService} from '../../../@core/auth/state-storage.service';
import {${table.upperCaseName}Service} from './${table.lowerCaseSubName}.service';
import {AbstractManageListComponent} from '../../../shared/manage/abstract-manage-list.component';
import {TranslateService} from '@ngx-translate/core';

@Component({
    selector: 'ngx-${table.lowerCaseSubName}',
    templateUrl: './${table.lowerCaseSubName}.component.html',
    styleUrls: ['./${table.lowerCaseSubName}.component.scss']
})
export class ${table.upperCaseName}Component extends AbstractManageListComponent {

    manageConfig = {
        endPoint : '/api${viewPath}',
        columns : {
        <#list columnList as column>
            ${column.camelCaseName}: {
                title: this.translateService.instant('${table.camelCaseName}.${column.camelCaseName}'),
                type: 'string'
            },
        </#list>
        }
    };


    constructor(http: HttpClient,
                principal: Principal,
                protected route: ActivatedRoute,
                protected router: Router,
                private ${table.camelCaseName}Service: ${table.upperCaseName}Service,
                protected stateStorageService: StateStorageService,
                private translateService: TranslateService) {
        super(http, principal, ${table.camelCaseName}Service, route, router, stateStorageService);
        this.init();
    }

    setQueryFilters() {
        this.queryFilter['${searchColumnName}'] = null;
    }

}
