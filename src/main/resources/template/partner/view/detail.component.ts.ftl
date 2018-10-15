import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {${table.upperCaseName}Service} from './${table.lowerCaseSubName}.service';
import {AbstractManageDetailComponent} from '../../../shared/manage/abstract-manage-detail.component';

@Component({
    selector: 'ngx-${table.lowerCaseSubName}-detail',
    templateUrl: './${table.lowerCaseSubName}-detail.component.html'
})
export class ${table.upperCaseName}DetailComponent extends AbstractManageDetailComponent {

    ${table.camelCaseName}: any;

    constructor(private ${table.camelCaseName}Service: ${table.upperCaseName}Service,
                protected route: ActivatedRoute) {
        super(${table.camelCaseName}Service, route);
    }

    setData(data) {
        this.${table.camelCaseName} = data;
    }

}
