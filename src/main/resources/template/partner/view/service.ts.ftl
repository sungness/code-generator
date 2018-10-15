import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AbstractManageService} from '../../../shared/manage/abstract-manage.service';

@Injectable()
export class ${table.upperCaseName}Service extends AbstractManageService {

    constructor(protected http: HttpClient) {
        super(http);
        this.CREATE_URL = '/api${viewPath}/new';
        this.UPDATE_URL = '/api${viewPath}/edit';
        this.DETAIL_URL = '/api${viewPath}/detail';
        this.DELETE_URL = '/api${viewPath}/delete';
    }

}
