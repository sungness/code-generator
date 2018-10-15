import {Component} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {${table.upperCaseName}Service} from './${table.lowerCaseSubName}.service';
import {AbstractManageEditComponent} from '../../../shared/manage/abstract-manage-edit.component';

@Component({
    selector: 'ngx-${table.lowerCaseSubName}-edit',
    templateUrl: './${table.lowerCaseSubName}-edit.component.html',
})
export class ${table.upperCaseName}EditComponent extends AbstractManageEditComponent {

    ${table.camelCaseName}: any;

    constructor(private ${table.camelCaseName}Service: ${table.upperCaseName}Service,
                protected router: Router,
                protected route: ActivatedRoute) {
        super(${table.camelCaseName}Service, router, route);
    }

    setData(data) {
        this.${table.camelCaseName} = data;
    }

    getData() {
        return this.${table.camelCaseName};
    }

}
