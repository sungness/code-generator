import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ServerDataSource} from '../../../@theme/ng2-smart-table';
import {ActivatedRoute, Router} from '@angular/router';
import {NgbDatepickerI18n} from '@ng-bootstrap/ng-bootstrap';
import {CustomDatepickerI18n} from '../../../@theme/components/datepicker/datepicker-i18n';
import {Principal} from '../../../@core/auth/principal.service';
import {StateStorageService} from '../../../@core/auth/state-storage.service';
import {${table.upperCaseName}Service} from './${table.lowerCaseSubName}.service';
import {AbstractManageComponent} from '../../abstract-manage.component';


@Component({
    selector: 'ngx-${table.lowerCaseSubName}',
    templateUrl: './${table.lowerCaseSubName}.component.html',
    styleUrls: ['./${table.lowerCaseSubName}.component.scss'],
//    providers: [{provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n}]
})
export class ${table.upperCaseName}Component extends AbstractManageComponent {

    manageConfig = {
        endPoint : '/api${viewPath}',
        columns : {
        <#list columnList as column>
            ${column.camelCaseName}: {
                title: '${column.clearComment()}',
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
                protected stateStorageService: StateStorageService) {
        super(route, router, stateStorageService);
        this.settings.columns = this.manageConfig.columns;
        this.sourceConf.endPoint = this.manageConfig.endPoint;
        principal.hasPermissionForCURD(this.sourceConf.endPoint, this.settings);
        this.source = new ServerDataSource(http, this.sourceConf);
        this.initPageConf();
    }


    delete(ids) {
        this.${table.camelCaseName}Service.delete(ids).subscribe((res) => {
            this.source.refresh();
        });
    }

    getSearchField(){
        return '${searchColumnName}';
    }
}
